package usermovie;

import base.GetQuery;
import dataaccess.IQuery;
import domain.UserMovie;

/**
 * A container for user-movie related queries.
 */
public class UserMovieQueries {

	//------------------------------------------------------------------------------------------------------------------
	//	Public Methods

	/**
	 * Generates a query describing the retrieval of all user-movie objects by user.
	 * @return The retrieved user-movie objects.
	 */
	public static IQuery<UserMovie> getAllByUser() {
		return new GetQuery<UserMovie>("movies/all", UserMovie.class);
	}

}
