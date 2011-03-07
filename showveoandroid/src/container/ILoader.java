package container;

/**
 * Defines the application loader.
 */
public interface ILoader {

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Public Methods

	/**
	 * Loads the application.
	 * @param store The main data store.
	 */
	void load(IDataStore store);

}
