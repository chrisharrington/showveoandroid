package user;
import dataaccess.IService;
import dataaccess.user.IUserRepository;
import domain.User;

public class UserRepository implements IUserRepository {

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Data Members
	
	//	The remote container for information.
	IService _service;
	
	//----------------------------------------------------------------------------------------------------------------------------------
	//	Constructors
	
	//
	//	The default constructor.
	//	service:						The remote container for information.
	//
	public UserRepository(IService service) {
		if (service == null)
			throw new IllegalArgumentException("service");
		
		_service = service;
	}
	
	//----------------------------------------------------------------------------------------------------------------------------------
	//	Public Methods
	
	/**
	 * Retrieves a user by email address and password.
	 * @param emailAddress The user's email address.
	 * @param password The user's password.
	 * @return The retrieved user object.
	 */
	public User getByEmailAddressAndPassword(String emailAddress, String password) {
		return _service.execute(UserQueries.getByEmailAddressAndPassword(emailAddress, password));
	}

}
