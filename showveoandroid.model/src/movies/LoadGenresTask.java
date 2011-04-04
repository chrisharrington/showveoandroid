package movies;

import android.os.AsyncTask;
import dataaccess.genre.IGenreRepository;
import domain.GenreCollection;
import service.event.IParameterizedEventHandler;

public class LoadGenresTask extends AsyncTask<IParameterizedEventHandler<GenreCollection>, Void, GenreCollection> {

	//------------------------------------------------------------------------------------------------------------------
	// Data Members

	//	A container for genre information.
	private final IGenreRepository _genreRepository;

	//	The callback function to execute.
	private IParameterizedEventHandler<GenreCollection> _callback;

	//	Any errors that may have occurred.
	private Throwable _exception;

	//------------------------------------------------------------------------------------------------------------------
	// Constructors

	/**
	 * The default constructor.
	 * @param genreRepository A container for genre information.
	 */
	public LoadGenresTask(IGenreRepository genreRepository) {
		if (genreRepository == null)
			throw new IllegalArgumentException("genreRepository");

		_genreRepository = genreRepository;
	}

	//------------------------------------------------------------------------------------------------------------------
	// Protected Methods

	/**
	 * The task to perform in a background thread.
	 * @param callback The task's parameters.
	 * @return The result of the task.
	 */
	@Override
	protected GenreCollection doInBackground(IParameterizedEventHandler<GenreCollection>... callback) {
		if (callback == null || callback.length == 0)
			throw new IllegalArgumentException("callback");

		_callback = callback[0];

//		try {
//			return new GenreCollection(_genreRepository.getAll());
//		} catch (DataException e) {
//			_exception = e;
//			return null;
//		}

		return null;
	}

	/**
	 * The operation to perform after the task has completed.
	 * @param result The result of the task.
	 */
	@Override
	protected void onPostExecute(GenreCollection result) {
		_callback.run(result, _exception);
	}

}
