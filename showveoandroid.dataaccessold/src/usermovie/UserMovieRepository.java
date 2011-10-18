package usermovie;

import base.BaseAsyncTask;
import dataaccess.IService;
import dataaccess.usermovie.IUserMovieRepository;
import domain.UserMovieCollection;
import service.event.IParameterizedEventHandler;

/**
 * A container for user-movie information.
 */
public class UserMovieRepository implements IUserMovieRepository {

	//------------------------------------------------------------------------------------------------------------------
	//	Data Members

	//	A container for remote data.
	private IService _service;

	//------------------------------------------------------------------------------------------------------------------
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

	//------------------------------------------------------------------------------------------------------------------
	//	Public Methods

    /**
	 * Retrieves a collection of user-movie information objects by user.
     * @param callback The callback function to execute with the retrieved user-movie information.
	 */
    public void getAll(IParameterizedEventHandler<UserMovieCollection> callback) {
        new BaseAsyncTask<Void, Void, UserMovieCollection>(callback) {
            @Override
            protected UserMovieCollection run(Void... params) {
                return new UserMovieCollection(_service.executeList(UserMovieQueries.getAllByUser()));
            }
        }.execute();
    }
}
