package session;

import domain.User;
import service.session.ISessionManager;

/**
 * Manages user session state.
 */
public class SessionManager implements ISessionManager {

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Data Members

	//	The currently logged in user.
	private User _user;

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Public Methods

	/**
	 * Register a user as the currently logged in user.
	 * @param user The user to register.
	 */
	public void register(User user) {
		if (user == null)
			throw new IllegalArgumentException("user");

		_user = user;
	}

	/**
	 * Retrieves the currently logged in user.
	 * @return The currently logged in user.
	 */
	public User get() {
		return _user;
	}
}
