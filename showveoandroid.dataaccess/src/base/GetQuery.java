package base;

import dataaccess.IQuery;

import java.util.HashMap;
import java.util.Set;

/**
 * A class used to represent a remote query.
 */
public class GetQuery<T> implements IQuery<T> {

	//------------------------------------------------------------------------------------------------------------------
	//	Data Members

	//	The query string.
	private String _query;

	//	The collection of data pairs.
	private HashMap<String,String> _data;

	//	The type of the returned data.
	private Class<T> _returnType;

	//------------------------------------------------------------------------------------------------------------------
	//	Properties

	/**
	 * Retrieves the query string.
	 * @return The string portion of the query.
	 */
	public String getQueryString() {
		StringBuilder builder = new StringBuilder();
		builder.append(_query);

		Set<String> set = _data.keySet();
		Object[] keys = set.toArray();

		for (int i = 0; i < keys.length; i++)
			builder.append(i == 0 ? "?" : "&").append(keys[i]).append("=").append(_data.get(keys[i]));
		return builder.toString();
	}

	/**
	 * Retrieves the HTTP method of data transmission.
	 * @return The HTTP method of data transmission.
	 */
	public String getMethod() {
		return "GET";
	}

	/**
	 * Gets the type of the returned information.
	 * @return THe type of the returned information.
	 */
	public Class<T> getType() {
		return _returnType;
	}

	/**
	 * Adds data to the query.
	 * @param key   The key of the data pair.
	 * @param value The value of the data pair.
	 */
	public void addData(String key, String value) {
		if (_data.containsKey(key))
			_data.remove(key);
		_data.put(key, value);
	}

	//------------------------------------------------------------------------------------------------------------------
	//	Constructors

	/**
	 * The default constructor.
	 * @param query The query string.
	 * @param returnType The type of the returned information.
	 */
	public GetQuery(String query, Class<T> returnType) {
		if (query == null || query.equals(""))
			throw new IllegalArgumentException("query");
		if (!query.contains(".data"))
			query += ".data";
		if (returnType == null)
			throw new IllegalArgumentException("returnType");

		_query = query;
		_returnType = returnType;
		_data = new HashMap<String,String>();
	}
}
