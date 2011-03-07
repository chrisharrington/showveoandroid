package threading;

import service.threading.IThread;

/**
 * A thread that can be monitored for termination.
 */
public class MonitoredThread extends Thread implements IThread {

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Data Members

	//	Fired after the thread has completed.
	private onComplete _onComplete;

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Public Methods

	/**
	 * Runs the thread operations.
	 */
	public void run() {
		if (_onComplete != null)
			_onComplete.run(null);
	}

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Event Handlers

	/**
	 * Sets the handler for after the thread has completed.
	 * @param handler The handler.
	 */
	public void onCompleteHandler(onComplete handler) {

	}
}
