package base;

import android.os.AsyncTask;
import service.event.IParameterizedEventHandler;

/**
 * A base wrapper for performing asynchronous tasks.
 * @param <TParams> Parameters for the operation of the asynchronous task.
 * @param <TProgress> Progress parameters.
 * @param <TResult> Return types for the asynchronous task.
 */
public abstract class BaseAsyncTask<TParams, TProgress, TResult> extends AsyncTask<TParams, TProgress, TResult> {

	//------------------------------------------------------------------------------------------------------------------
	//	Data Members

	//	The callback function to execute once the run method has completed.
	private final IParameterizedEventHandler<TResult> _callback;

	//	Any exception that may have been thrown as a result of the run method.
	private Throwable _exception;

	//------------------------------------------------------------------------------------------------------------------
	//	Public Methods

	/**
	 * The default constructor.
	 * @param callback The callback function to execute once the run method has completed.
	 */
	protected BaseAsyncTask(IParameterizedEventHandler<TResult> callback) {
		if (callback == null)
			throw new IllegalArgumentException("callback");

		_callback = callback;
	}

	//------------------------------------------------------------------------------------------------------------------
	//	Protected Methods

	/**
	 * The method that wraps functionality that needs to run in the background.  Executes the abstract run method to
	 * perform task-specific commands.
	 * @param params Any parameters required by the task.
	 * @return The result of the task.
	 */
	@Override
	protected TResult doInBackground(TParams... params) {
		try {
			return run(params);
		} catch (Exception e) {
			_exception = e;
			return null;
		}
	}

	/**
	 * Call after the doInBackground function has fully completed.  Handles executing the callback method with the
	 * result of the run method, be it the requested data or an error.
	 * @param result The result of the doInBackground method.
	 */
	@Override
	protected void onPostExecute(TResult result) {
		if (_exception != null)
			_exception.printStackTrace();
		_callback.run(result, _exception);
	}

	protected abstract TResult run(TParams... params);
}
