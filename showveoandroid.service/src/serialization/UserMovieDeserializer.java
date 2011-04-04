package serialization;

import com.google.gson.*;
import domain.Movie;
import domain.User;
import domain.UserMovie;

import java.lang.reflect.Type;

/**
 * A custom deserializer for the UserMovie class.
 */
public class UserMovieDeserializer implements JsonDeserializer<UserMovie> {

	//------------------------------------------------------------------------------------------------------------------
	//	Public Methods

	/**
	 * Deserializes the given element.
	 * @param element The JSON element to deserialize.
	 * @param type The type to deserialize into.
	 * @param context The context.
	 * @return The deserialized object.
	 * @throws JsonParseException
	 */
	public UserMovie deserialize(JsonElement element, Type type, JsonDeserializationContext context) throws JsonParseException {
		JsonObject object = element.getAsJsonObject();

		UserMovie userMovie = new UserMovie();

		element = object.get("user");
		userMovie.setUser(element == null ? new User() : context.<User>deserialize(element, User.class));

		element = object.get("movie");
		userMovie.setMovie(element == null ? new Movie() : context.<Movie>deserialize(element, Movie.class));

		element = object.get("isFavorite");
		userMovie.isFavorite(element != null && element.getAsBoolean());

		return userMovie;
	}
}
