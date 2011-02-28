package dataaccess.user;

import domain.User;

/**
 * Defines a container for user information.
 */
public interface IUserRepository {

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Public Methods

	/**
	 * Retrieves a user by email address and password.
	 * @param emailAddress The user's email address.
	 * @param password The user's password.
	 * @return The retrieved user object.
	 */
	User getByEmailAddressAndPassword(String emailAddress, String password);

}
