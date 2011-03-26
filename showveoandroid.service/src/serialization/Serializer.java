package serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import domain.Genre;
import domain.Movie;
import domain.User;
import domain.UserMovie;
import service.serialization.IDateParser;
import service.serialization.ISerializer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Stack;

/**
 * Serializes and deserializes to and from JSON representations.
 */
public class Serializer implements ISerializer {

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Data Members

	//	The underlying JSON serializer.
	Gson _serializer;

	//	Handles the parsing of incoming date strings.
	private IDateParser _dateParser;

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Constructors

	/**
	 * The default constructor.
	 * @param dateParser Handles the parsing of incoming date strings.
	 */
	public Serializer(IDateParser dateParser) {
		_dateParser = dateParser;

		GsonBuilder builder = new GsonBuilder();
		builder.registerTypeAdapter(User.class, new UserDeserializer());
		builder.registerTypeAdapter(Genre.class, new GenreDeserializer());
		builder.registerTypeAdapter(UserMovie.class, new UserMovieDeserializer());
		builder.registerTypeAdapter(Movie.class, new MovieDeserializer());
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
		if (type == Date.class)
			return type.cast(_dateParser.parseRemote(string));
		return type.cast(_serializer.fromJson(string, type));
	}

	/**
	 * Deserializes a string into a list of objects.
	 *
	 * @param string The string to deserialize.
	 * @param type   The type of the object.
	 * @param <T>    The type of the object the string should be deserialized into.
	 * @return The generated list.
	 */
	public <T> List<T> deserializeList(String string, Class<T> type) {
		string = string.replaceAll("\t", "").replaceAll("\n", "");
		ArrayList<String> sections = getCollectionSections(string);

		List<T> list = new ArrayList<T>();
		for (String section : sections)
			list.add(deserialize(section, type));

		return list;
	}

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Private Methods

	/**
	 * Parses the result of a collection query and returns the individual JSON string sections.
	 * @param string THe original JSON string.
	 * @return The split up single JSON object representations.
	 */
	private ArrayList<String> getCollectionSections(String string) {
		if (string.charAt(0) != '[')
			return null;

		if (string.charAt(string.length()-1) != ']')
			return null;

		ArrayList<String> sections = new ArrayList<String>();

		Stack<Integer> stack = new Stack<Integer>();
		for (int i = 0; i < string.length(); i++) {
			char ch = string.charAt(i);
			if (ch == '{')
				stack.push(i);
			else if (ch == '}') {
				int index = stack.pop();
				if (stack.toArray().length == 0)
					sections.add(string.substring(index, i+1));
			}
		}

		return sections;
	}
}
