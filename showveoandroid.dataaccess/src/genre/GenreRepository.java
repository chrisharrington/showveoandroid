package genre;

import dataaccess.DataException;
import dataaccess.IService;
import dataaccess.genre.IGenreRepository;
import domain.Genre;

import java.util.List;

/**
 * A container for genre information.
 */
public class GenreRepository implements IGenreRepository {

	//------------------------------------------------------------------------------------------------------------------
	//	Data Members

	//	The remote container for information.
	IService _service;

	//------------------------------------------------------------------------------------------------------------------
	//	Constructors

	/**
	 * The default constructor.
	 * @param service The remote container for information.
	 */
	public GenreRepository(IService service) {
		if (service == null)
			throw new IllegalArgumentException("service");

		_service = service;
	}

	//------------------------------------------------------------------------------------------------------------------
	//	Public Methods

	/**
	 * Retrieves a list of all genres.
	 * @throws dataaccess.DataException Represents an error that occurs during normal data access operations.
	 * @return The list of all genres.
	 */
	public List<Genre> getAll() throws DataException {
		return _service.executeList(GenreQueries.getAll());
	}
}
