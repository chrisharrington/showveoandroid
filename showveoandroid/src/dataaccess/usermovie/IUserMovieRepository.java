package dataaccess.usermovie;

import domain.UserMovieCollection;
import service.event.IParameterizedEventHandler;

/**
 * Defines a container for user-movie information.
 */
public interface IUserMovieRepository {

	//------------------------------------------------------------------------------------------------------------------
	//	Public Methods

	/**
	 * Retrieves a collection of user-movie information objects by user.
     * @param callback The callback function to execute with the retrieved user-movie information.
	 */
	void getAll(IParameterizedEventHandler<UserMovieCollection> callback);

}
