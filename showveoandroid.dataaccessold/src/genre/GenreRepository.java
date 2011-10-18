package genre;

import base.BaseAsyncTask;
import dataaccess.IService;
import dataaccess.genre.IGenreRepository;
import domain.GenreCollection;
import service.event.IParameterizedEventHandler;

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
	 * Asynchronously retrieves a list of all genres.
	 * @param callback The callback function executed once the genres have been retrieved.
	 */
	public void getAll(final IParameterizedEventHandler<GenreCollection> callback) {
		new BaseAsyncTask<Void, Void, GenreCollection>(callback) {
			@Override
			protected GenreCollection run(Void... params) {
				return new GenreCollection(_service.executeList(GenreQueries.getAll()));
			}
		}.execute();
	}
}
