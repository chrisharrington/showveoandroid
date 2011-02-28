package usermovie;

import base.GetQuery;
import dataaccess.IQuery;
import domain.User;
import domain.UserMovie;

/**
 * A container for user-movie related queries.
 */
public class UserMovieQueries {

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Public Methods

	/**
	 * Generates a query describing the retrieval of all user-movie objects by user.
	 * @param user The user of the retrieved user-movie objects.
	 * @return The retrieved user-movie objects.
	 */
	public static IQuery<UserMovie> getAllByUser(User user) {
		if (user == null)
			throw new IllegalArgumentException("user");

		IQuery<UserMovie> query = new GetQuery<UserMovie>("movies/recent", UserMovie.class);
		query.addData("identity", user.getIdentity());
		return query;
	}

}
