package container;

public interface IContainer {

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Public Methods

	/**
	 * Retrieves an object of the given type.
	 * @param type The type parameter.
	 * @param <T> The type of the object to get.
	 * @return The retrieved object or null.
	 */
	<T> T get(Class<T> type);

}
