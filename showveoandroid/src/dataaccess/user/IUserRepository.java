package dataaccess.user;

import domain.User;

//
//	Defines a container for user information.
//
public interface IUserRepository {

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Public Methods
	
	//
	//	Retrieves a user by email address and password.
	//	emailAddress:					The user's email address.
	//	password:						The user's encrypted password.
	//
	User getByEmailAddressAndPassword(String emailAddress, String password);
}
