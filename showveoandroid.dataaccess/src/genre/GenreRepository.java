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

//		new AsyncTask<Void, Void, GenreCollection>() {
//			private Throwable _exception;
//
//			@Override
//			protected GenreCollection doInBackground(Void... voids) {
//				try {
//					return new GenreCollection(_service.executeList(GenreQueries.getAll()));
//				} catch (Exception e) {
//					_exception = e;
//					return null;
//				}
//			}
//
//			@Override
//			protected void onPostExecute(GenreCollection result) {
//				if (_exception != null)
//					_exception.printStackTrace();
//				callback.run(result, _exception);
//			}
//		}.execute();
	}
}
