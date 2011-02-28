package service.session;

import domain.User;

/**
 * Tracks logged in users and their session.
 */
public interface ISessionManager {

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Public Methods

	/**
	 * Register a user as the currently logged in user.
	 * @param user The user to register.
	 */
	void register(User user);

	/**
	 * Retrieves the currently logged in user.
	 * @return The currently logged in user.
	 */
	User get();

}
