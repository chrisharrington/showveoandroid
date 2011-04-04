package dataaccess.genre;

import dataaccess.DataException;
import domain.Genre;

import java.util.List;

/**
 * Defines a container for genre information.
 */
public interface IGenreRepository {

	//------------------------------------------------------------------------------------------------------------------
	//	Public Methods

	/**
	 * Retrieves a list of all genres.
	 * @throws DataException Represents an error that occurs during normal data access operations.
	 * @return The list of all genres.
	 */
	List<Genre> getAll() throws DataException;

}
