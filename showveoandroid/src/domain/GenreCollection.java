package domain;

import java.util.ArrayList;
import java.util.Collection;

public class GenreCollection extends ArrayList<Genre> {

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Constructors

	/**
	 * The default constructor.
	 */
	public GenreCollection() {}

	/**
	 * The collection constructor.
	 * @param collection The genre collection to wrap.
	 */
	public GenreCollection(Collection<Genre> collection) {
		super(collection);
	}

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Public Methods

	/**
	 * Retrieves a genre in the collection by genre name.
	 * @param genreName The name of the genre to retrieve.
	 * @return The retrieved genre or null.
	 */
	public Genre getByName(String genreName) {
		if (genreName == null || genreName.equals(""))
			return null;

		genreName = genreName.toLowerCase();
		for (Genre genre : this)
			if (genre.getName().toLowerCase().equals(genreName))
				return genre;
		return null;
	}
}
