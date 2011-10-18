package genre;

import base.GetQuery;
import dataaccess.IQuery;
import domain.Genre;

/**
 * A container for genre-related queries.
 */
public class GenreQueries {

	//------------------------------------------------------------------------------------------------------------------
	//	Public Methods

	/**
	 * Generates a query describing the retrieval of all genres.
	 * @return The query describing the retrieval of all genres.
	 */
	public static IQuery<Genre> getAll() {
		return new GetQuery<Genre>("movies/genres", Genre.class);
	}
}
