package base;

import container.DR;
import container.IDataStore;
import container.ILoader;
import controller.IMainController;
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
import model.movies.ILoadMoviesRunner;
import model.movies.IMoviesModel;
import movies.LoadMoviesRunner;
import movies.MoviesController;
import movies.MoviesModel;
import security.Cryptographer;
import serialization.DateParser;
import serialization.Serializer;
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
public class Loader implements ILoader {

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Data Members

    //  The location of the remote service.
	private final static String _remoteLocation = "http://68.147.201.165:3000/";
	
	//----------------------------------------------------------------------------------------------------------------------------------
	//	Public Methods
	
	/*
	 * Loads all of the required dependencies.
	 * @param store The main data store.
	 */
	public void load(IDataStore store) {
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
	}

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Private Methods

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

		DR.register(ILoadMoviesRunner.class, new LoadMoviesRunner(DR.get(IDataStore.class), DR.get(IUserMovieRepository.class), DR.get(IGenreRepository.class)));
		DR.register(IMoviesModel.class, new MoviesModel(DR.get(ILoadMoviesRunner.class), _remoteLocation));
		DR.register(IMoviesController.class, new MoviesController(DR.get(IMoviesModel.class)));
	}

}