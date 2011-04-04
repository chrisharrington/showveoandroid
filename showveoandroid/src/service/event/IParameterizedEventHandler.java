package service.event;

/**
 * Defines a parameterized event handler.
 */
public interface IParameterizedEventHandler<T> {

	//------------------------------------------------------------------------------------------------------------------
	//	Public Methods

	/**
	 * Runs the event handler.
	 * @param data Any event data.
	 * @param error An optional error.
	 */
	void run(T data, Throwable... error);

}