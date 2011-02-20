package serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import domain.User;
import service.serialization.ISerializer;

/**
 * Serializes and deserializes to and from JSON representations.
 */
public class Serializer implements ISerializer {

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Data Members

	//	The underlying JSON serializer.
	Gson _serializer;

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Constructors

	/**
	 * The default constructor.
	 */
	public Serializer() {
		GsonBuilder builder = new GsonBuilder();
		builder.registerTypeAdapter(User.class, new UserDeserializer());
		_serializer = builder.create();
	}

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Public Methods

	/**
	 * Serializes the given object into a JSON string.
	 * @param obj The object to serialize.
	 * @return The JSON representation of the given object.
	 */
	public String Serialize(Object obj) {
		return _serializer.toJson(obj);
	}

	/**
	 * Deserializes a string into an object.
	 * @param string The string to deserialize.
	 * @param type The type of the object.
	 * @param <T> The type of the object the string should be deserialized into.
	 * @return The generated object.
	 */
	public <T> T deserialize(String string, Class<T> type) {
		return type.cast(_serializer.fromJson(string, type));
	}
}
