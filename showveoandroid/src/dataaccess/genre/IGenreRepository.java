package dataaccess.genre;

import domain.GenreCollection;
import service.event.IParameterizedEventHandler;

/**
 * Defines a container for genre information.
 */
public interface IGenreRepository {

	//------------------------------------------------------------------------------------------------------------------
	//	Public Methods

	/**
	 * Asynchronously retrieves a list of all genres.
	 * @param callback The callback function executed once the genres have been retrieved.
	 */
	void getAll(IParameterizedEventHandler<GenreCollection> callback);
}
