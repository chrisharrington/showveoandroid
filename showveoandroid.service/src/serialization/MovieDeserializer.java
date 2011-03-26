package serialization;

import com.google.gson.*;
import container.DR;
import domain.Genre;
import domain.Movie;
import domain.User;
import service.serialization.ISerializer;

import java.lang.reflect.Type;
import java.util.Date;

/**
 * A custom deserializer for the Movie class.
 */
public class MovieDeserializer implements JsonDeserializer<Movie> {

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Properties

	private ISerializer getSerializer() {
		return DR.get(ISerializer.class);
	}

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
	public Movie deserialize(JsonElement element, Type type, JsonDeserializationContext context) throws JsonParseException {
		JsonObject object = element.getAsJsonObject();

		ISerializer serializer = getSerializer();

		Movie movie = new Movie();

		element = object.get("id");
		movie.setID(element == null ? "" : element.getAsString());

		element = object.get("name");
		movie.setName(element == null ? "" : element.getAsString());

		element = object.get("year");
		movie.setYear(element == null ? 0 : element.getAsInt());

		element = object.get("synopsis");
		movie.setSynopsis(element == null ? "" : element.getAsString());

		element = object.get("genres");
		for (JsonElement current : element.getAsJsonArray())
			movie.getGenres().add(context.<Genre>deserialize(current, Genre.class));

		element = object.get("addDate");
		movie.setAddDate(element == null ? new Date(1970, 1, 1) : serializer.deserialize(element.getAsString(), Date.class));

		element = object.get("lastWatched");
		movie.setLastWatched(element == null ? new User() : context.<User>deserialize(element, User.class));

		element = object.get("lastWatchedDate");
		movie.setLastWatchedDate(new Date(1970, 1, 1));
		if (!element.isJsonNull())
			movie.setLastWatchedDate(serializer.deserialize(element.getAsString(), Date.class));

		element = object.get("poster");
		movie.setPoster(element == null ? "" : element.getAsString());

		element = object.get("director");
		movie.setDirector(element == null ? "" : element.getAsString());

		element = object.get("actors");
		for (JsonElement current : element.getAsJsonArray())
			movie.getActors().add(current.getAsString());

		element = object.get("file");
		movie.setFile(element == null ? "" : element.getAsString());

		element = object.get("encoded");
		movie.isEncoded(element != null && element.getAsBoolean());

		return movie;
	}
}
