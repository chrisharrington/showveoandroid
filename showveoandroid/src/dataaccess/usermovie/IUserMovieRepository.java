package dataaccess.usermovie;

import dataaccess.DataException;
import domain.UserMovie;

import java.util.List;

/**
 * Defines a container for user-movie information.
 */
public interface IUserMovieRepository {

	//------------------------------------------------------------------------------------------------------------------
	//	Public Methods

	/**
	 * Retrieves a collection of user-movie information objects by user.
	 * @throws DataException Occurs when the operation cannot be completed.
	 * @return	The retrieved collection of user-movie information objects.
	 */
	List<UserMovie> getAll() throws DataException;

}
