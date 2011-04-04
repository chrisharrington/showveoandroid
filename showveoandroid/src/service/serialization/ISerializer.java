package service.serialization;

import java.util.List;

/**
 * Defines a JSON serializer.
 */
public interface ISerializer {

	//------------------------------------------------------------------------------------------------------------------
	//	Public Methods

	/**
	 * Serializes the given object into a JSON string.
	 * @param obj The object to serialize.
	 * @return The JSON representation of the given object.
	 */
	String Serialize(Object obj);

	/**
	 * Deserializes a string into an object.
	 * @param string The string to deserialize.
	 * @param type The type of the object.
	 * @param <T> The type of the object the string should be deserialized into.
	 * @return The generated object.
	 */
	<T> T deserialize(String string, Class<T> type);

	/**
	 * Deserializes a string into a list of objects.
	 * @param string The string to deserialize.
	 * @param type The type of the object.
	 * @param <T> The type of the object the string should be deserialized into.
	 * @return The generated list.
	 */
	<T> List<T> deserializeList(String string, Class<T> type);
}
