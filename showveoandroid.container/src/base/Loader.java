package base;

import container.DR;
import container.ILoader;
import controller.IMainController;
import dataaccess.IService;
import dataaccess.genre.IGenreRepository;
import dataaccess.user.IUserRepository;
import dataaccess.usermovie.IUserMovieRepository;
import genre.GenreRepository;
import main.MainController;
import main.MainModel;
import model.IMainModel;
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
	private final static String _remoteServiceLocation = "http://localhost:3000/";
	
	//----------------------------------------------------------------------------------------------------------------------------------
	//	Public Methods
	
	/*
	 * Loads all of the required dependencies.
	 */
	public void load() {
		loadService();
		loadDataAccess();
		loadUI();
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
		IService service = new Service(_remoteServiceLocation, DR.get(ISerializer.class), DR.get(ISessionManager.class), false);
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
	}

}