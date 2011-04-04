package base;

import android.os.Handler;
import android.os.Message;
import container.DR;
import container.IDataStore;
import controller.IMainController;
import controller.IMovieDetailsController;
import controller.IMoviesController;
import dataaccess.IService;
import dataaccess.genre.IGenreRepository;
import dataaccess.user.IUserRepository;
import dataaccess.usermovie.IUserMovieRepository;
import domain.User;
import genre.GenreRepository;
import main.MainController;
import main.MainModel;
import model.IMainModel;
import model.movies.IMovieDetailsModel;
import model.movies.IMoviesModel;
import moviedetails.MovieDetailsModel;
import movies.*;
import security.Cryptographer;
import serialization.DateParser;
import serialization.Serializer;
import service.event.IParameterizedEventHandler;
import service.security.ICryptographer;
import service.serialization.IDateParser;
import service.serialization.ISerializer;
import service.session.ISessionManager;
import session.SessionManager;
import user.UserRepository;
import usermovie.UserMovieRepository;

/*
 * A class used to load the dependencies for the application.
 */
public class Loader {

	//------------------------------------------------------------------------------------------------------------------
	//	Data Members

    //  The location of the remote service.
	private final static String _remoteLocation = "http://68.147.201.165:3000/";

	//	A flag indicating whether or not the applicatoin is loaded.
	private static boolean _isLoaded = false;
	
	//------------------------------------------------------------------------------------------------------------------
	//	Public Methods
	
	/*
	 * Loads all of the required dependencies.
	 * @param store The main data store.
	 * @param callback The callback function to execute once the loader has completed loading.
	 */
	public static void load(final IDataStore store, final IParameterizedEventHandler<Throwable> callback) {
		final Handler handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {

			}
		};

		new Thread(new Runnable() {
			public void run() {
				try {
					load(store);
					handler.sendEmptyMessage(0);
					callback.run(null);
				} catch (Exception e) {
					callback.run(e);
				}
			}
		}).start();
	}

	//------------------------------------------------------------------------------------------------------------------
	//	Private Methods

	/**
	 * Performs the load operation.
	 * @param store The main data store.
	 */
	public static void load(IDataStore store) {
		if (_isLoaded)
			return;

		DR.register(IDataStore.class, store);

		loadService();
		loadDataAccess();
		loadUI();

		ICryptographer cryptographer = DR.get(ICryptographer.class);
		String password = cryptographer.sha256hash("test");

		IUserRepository userRepository = DR.get(IUserRepository.class);
		User user = userRepository.getByEmailAddressAndPassword("chrisharrington99@gmail.com", password);

		ISessionManager sessionManager = DR.get(ISessionManager.class);
		sessionManager.register(user);

		_isLoaded = true;
	}

	/**
	 * Loads the service components for the application.
	 */
	private static void loadService() {
		DR.register(IDateParser.class, new DateParser());
		DR.register(ISerializer.class, new Serializer(DR.get(IDateParser.class)));

		DR.register(ICryptographer.class, new Cryptographer());

		DR.register(ISessionManager.class, new SessionManager());
	}

	/*
	 * Loads the data access components for the application.
	 */
	private static void loadDataAccess() {
		IService service = new Service(_remoteLocation, DR.get(ISerializer.class), DR.get(ISessionManager.class), false);
		DR.register(IService.class, service);
		
		DR.register(IUserRepository.class, new UserRepository(service));
		DR.register(IGenreRepository.class, new GenreRepository(service));
		DR.register(IUserMovieRepository.class, new UserMovieRepository(service));
	}

	/**
	 * Loads the models, views and controllers required for the UI to operate.
	 */
	private static void loadUI() {
		DR.register(IMainModel.class, new MainModel());
		DR.register(IMainController.class, new MainController(DR.get(IMainModel.class)));

		LoadMoviesTask loadMoviesTask = new LoadMoviesTask(DR.get(IUserMovieRepository.class));
		LoadGenresTask loadGenresTask = new LoadGenresTask(DR.get(IGenreRepository.class));
		DR.register(IMoviesModel.class, new MoviesModel(loadMoviesTask, DR.get(IGenreRepository.class), _remoteLocation));
		DR.register(IMoviesController.class, new MoviesController(DR.get(IMoviesModel.class)));

		DR.register(IMovieDetailsModel.class, new MovieDetailsModel());
		DR.register(IMovieDetailsController.class, new MovieDetailsController(DR.get(IMovieDetailsModel.class)));
	}

}