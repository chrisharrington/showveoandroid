package usermovie;

import dataaccess.IService;
import dataaccess.usermovie.IUserMovieRepository;
import domain.UserMovie;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

/**
 * A container for user-movie information.
 */
public class UserMovieRepository implements IUserMovieRepository {

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Data Members

	//	A container for remote data.
	private IService _service;

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Constructors

	/**
	 * The default constructor.
	 * @param service A container for remote data.
	 */
	public UserMovieRepository(IService service) {

		if (service == null)
			throw new IllegalArgumentException("service");

		_service = service;
	}

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Public Methods

	/**
	 * Retrieves a collection of user-movie information objects by user.
	 * @return The retrieved collection of user-movie information objects.
	 */
	public List<UserMovie> getRecentByUser() {
		throw new NotImplementedException();
	}

	/**
	 * Retrieves a collection of user-movie information objects by user.
	 *
	 * @return The retrieved collection of user-movie information objects.
	 */
	public List<UserMovie> getAll() {
		return _service.executeList(UserMovieQueries.getAllByUser());
	}

}
