package service.event;

/**
 * Defines an event handler.
 */
public interface IEventHandler {

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Public Methods

	/**
	 * Runs the event handler.
	 * @param data Any event data.
	 */
	<T>void run(T data);

}