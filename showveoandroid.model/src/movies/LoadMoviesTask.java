package movies;

import android.os.AsyncTask;
import dataaccess.DataException;
import dataaccess.usermovie.IUserMovieRepository;
import domain.UserMovieCollection;
import service.event.IParameterizedEventHandler;

/**
 * An asynchronous task used to retrieve user-movie information.
 */
public class LoadMoviesTask extends AsyncTask<IParameterizedEventHandler<UserMovieCollection>, Void, UserMovieCollection> {

	//------------------------------------------------------------------------------------------------------------------
	//	Data Members

	//	Any exception thrown by the data operation.
	private DataException _exception;

	//	A container for user-movie information.
	private final IUserMovieRepository _userMovieRepository;

	//	The callback for the function.
	private IParameterizedEventHandler<UserMovieCollection> _callback;

	//------------------------------------------------------------------------------------------------------------------
	// Constructors

	/**
	 * The default constructor.
	 * @param userMovieRepository A container for user-movie information.
	 */
	public LoadMoviesTask(IUserMovieRepository userMovieRepository) {
		if (userMovieRepository == null)
			throw new IllegalArgumentException("userMovieRepository");

		_userMovieRepository = userMovieRepository;
	}

	//------------------------------------------------------------------------------------------------------------------
	// Protected Methods

	/**
	 * The task to perform in a background thread.
	 * @param callback The task's parameters.
	 * @return The result of the task.
	 */
	@Override
	protected UserMovieCollection doInBackground(IParameterizedEventHandler<UserMovieCollection>... callback) {
		if (callback == null || callback.length == 0)
			throw new IllegalArgumentException("callback");

		_callback = callback[0];

		try {
			return new UserMovieCollection(_userMovieRepository.getAll());
		} catch (DataException e) {
			_exception = e;
			return null;
		}
	}

	/**
	 * The operation to perform after the task has completed.
	 * @param result The result of the task.
	 */
	@Override
	protected void onPostExecute(UserMovieCollection result) {
		_callback.run(result, _exception);
	}

}
