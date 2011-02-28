package serialization;

import com.google.gson.*;
import domain.Genre;

import java.lang.reflect.Type;

/**
 * A custom deserializer for the Genre class.
 */
public class GenreDeserializer implements JsonDeserializer<Genre> {

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
	public Genre deserialize(JsonElement element, Type type, JsonDeserializationContext context) throws JsonParseException {
		JsonObject object = element.getAsJsonObject();

		Genre genre = new Genre();

		element = object.get("id");
		genre.setID(element == null ? "" : element.getAsString());

		element = object.get("name");
		genre.setName(element == null ? "" : element.getAsString());

		return genre;
	}
}
