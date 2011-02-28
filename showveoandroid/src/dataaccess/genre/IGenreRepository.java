package dataaccess.genre;

import domain.Genre;

import java.util.List;

/**
 * Defines a container for genre information.
 */
public interface IGenreRepository {

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Public Methods

	/**
	 * Retrieves a list of all genres.
	 * @return The list of all genres.
	 */
	List<Genre> getAll();

}
