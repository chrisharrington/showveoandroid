package dataaccess;

//
//	Defines a query used to describe the retrieval of data.
//
public interface IQuery<T> {

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Properties

	/**
	 * Retrieves the query string.
	 * @return The string portion of the query.
	 */
	String getQueryString();

	/**
	 * Retrieves the HTTP method of data transmission.
	 * @return The HTTP method of data transmission.
	 */
	String getMethod();

	/**
	 * Gets the type of the returned information.
	 * @return THe type of the returned information.
	 */
	Class<T> getType();

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Public Methods

	/**
	 * Adds data to the query.
	 * @param key The key of the data pair.
	 * @param value The value of the data pair.
	 */
	void addData(String key, String value);
}
