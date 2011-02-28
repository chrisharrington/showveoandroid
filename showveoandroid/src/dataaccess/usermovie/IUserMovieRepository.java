package dataaccess.usermovie;

import domain.User;
import domain.UserMovie;

import java.util.List;

/**
 * Defines a container for user-movie information.
 */
public interface IUserMovieRepository {

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Public Methods

	/**
	 * Retrieves a collection of user-movie information objects by user.
	 * @param user The user associated with the user-movie information objects.
	 * @return The retrieved collection of user-movie information objects.
	 */
	List<UserMovie> getRecentByUser(User user);

}
