package service.threading;

import service.event.IEventHandler;

/**
 * Defines a runnable thread instance.
 */
public interface IThread extends Runnable {

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Public Methods

	/**
	 * Sets the handler for after the thread has completed.
	 * @param handler The handler.
	 */
	void onCompleteHandler(IThread.onComplete handler);
	static interface onComplete extends IEventHandler {}

}
