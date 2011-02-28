package serialization;

import com.google.gson.*;
import domain.User;

import java.lang.reflect.Type;

/**
 * A custom deserializer for the User class.
 */
public class UserDeserializer implements JsonDeserializer<User> {

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Public Methods

	/**
	 * Deserializes the given element.
	 * @param element The JSON element to deserialize.
	 * @param type The type to deserialize into.
	 * @param context The context.
	 * @return The deserialized object.
	 * @throws JsonParseException
	 */
	public User deserialize(JsonElement element, Type type, JsonDeserializationContext context) throws JsonParseException {
		JsonObject object = element.getAsJsonObject();

		User user = new User();

		element = object.get("firstName");
		user.setFirsTName(element == null ? "" : element.getAsString());

		element = object.get("lastName");
		user.setLastName(element == null ? "" : element.getAsString());

		element = object.get("identity");
		user.setIdentity(element == null ? "" : element.getAsString());

		element = object.get("emailAddress");
		user.setEmailAddress(element == null ? "" : element.getAsString());

		return user;
	}
}
