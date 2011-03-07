package movies;

import android.os.Bundle;
import android.os.Message;
import base.BaseModel;
import container.IDataStore;
import dataaccess.usermovie.IUserMovieRepository;
import domain.UserMovieCollection;
import model.IMoviesModel;
import view.movies.IMoviesView;

import java.util.UUID;

public class MoviesModel extends BaseModel<IMoviesView> implements IMoviesModel {

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Data Members

	//	A container for user-movie information.
	private final IUserMovieRepository _userMovieRepository;

	//	A process-wide data store used for holding query results across threads.
	private final IDataStore _store;

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Constructors

	public MoviesModel(IUserMovieRepository userMovieRepository, IDataStore store) {
		if (userMovieRepository == null)
			throw new IllegalArgumentException("userMovieRepository");
		if (store == null)
			throw new IllegalArgumentException("store");

		_userMovieRepository = userMovieRepository;
		_store = store;
	}

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Public Methods

	/**
	 * Loads all of the movies.
	 */
	public void loadMovies() {
		_view.showLoading("Loading movies...");

		final android.os.Handler handler = new android.os.Handler() {
			@Override
			public void handleMessage(Message msg) {
				Bundle bundle = msg.getData();
				UUID id = UUID.fromString(bundle.getString("moviecollectionid"));

				UserMovieCollection collection = _store.getData(id, UserMovieCollection.class);

				_view.hideLoading();
				_view.setMovieCollectionByName("recent", "Recent", collection);
				_view.setMovieCollectionByName("favorites", "Favorites", collection);
				_view.setMovieCollectionByName("genres", "Genres", collection);
				_view.setMovieCollectionByName("all", "All", collection);
			}
		};

		new Thread(new Runnable() {
			public void run() {
				UserMovieCollection list = new UserMovieCollection(_userMovieRepository.getAll());
				UUID id = _store.addData(list);

				Bundle bundle = new Bundle();
				bundle.putString("moviecollectionid", id.toString());

				Message message = new Message();
				message.setData(bundle);

				handler.sendMessage(message);
			}
		}).start();
	}
}

