import base.Service;
import serialization.Serializer;
import user.UserRepository;
import dataaccess.IService;
import dataaccess.user.IUserRepository;

/*
 * A class used to load the dependencies for the application.
 */
public class Loader {

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Data Members

    //  The location of the remote service.
	private final static String _remoteServiceLocation = "http://localhost:3000/";
	
	//----------------------------------------------------------------------------------------------------------------------------------
	//	Public Methods
	
	/*
	 * Loads all of the required dependencies.
	 */
	public static void load() {
		loadDataAccess();
	}

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Private Methods
	
	/*
	 * Loads the data access components for the application.
	 */
	private static void loadDataAccess() {
		IService service = new Service(_remoteServiceLocation, new Serializer(), true);
		DR.register(IService.class, service);
		
		DR.register(IUserRepository.class, new UserRepository(service));
	}
}