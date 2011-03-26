package service.event;

/**
 * Defines a parameterized event handler.
 */
public interface IParameterizedEventHandler<T> {

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Public Methods

	/**
	 * Runs the event handler.
	 * @param data Any event data.
	 */
	void run(T data);

}