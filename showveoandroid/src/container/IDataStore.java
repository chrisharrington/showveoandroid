package container;

import java.util.UUID;

/**
 * Defines a thread-safe data store.
 */
public interface IDataStore {

	//------------------------------------------------------------------------------------------------------------------
	//	Public Methods

	/**
	 * Adds data to the activity data store.
	 * @param data The data to add.
	 * @param <T> The type of data to add.
	 * @return The key for retrieving the data.
	 */
	<T> UUID addData(T data);

	/**
	 * Retreives the data stored with the given ID.  The data is removed from the store after retrieval.
	 * @param id The data key.
	 * @param type The type of data to retrieve.
	 * @param <T> The type of data to retrieve.
	 * @return The retrieved data.
	 */
	<T> T getData(UUID id, Class<T> type);

}
