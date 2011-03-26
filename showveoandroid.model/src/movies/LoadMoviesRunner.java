package movies;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import container.IContainer;
import container.IDataStore;
import dataaccess.genre.IGenreRepository;
import dataaccess.usermovie.IUserMovieRepository;
import domain.Genre;
import domain.GenreCollection;
import domain.UserMovieCollection;
import model.movies.ILoadMoviesRunner;
import service.event.IParameterizedEventHandler;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Loads movies for the movies model.
 */
public class LoadMoviesRunner implements ILoadMoviesRunner {

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Data Members

	 //	The data store used to set and retrieve information.
	private final IDataStore _store;

	//	A container for user-movie information.
	private final IUserMovieRepository _userMovieRepository;

	//	A container for genre information.
	private final IGenreRepository _genreRepository;

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Constructors

	/**
	 * The default constructor.
	 * @param store The data store used to set and retrieve information.
	 * @param userMovieRepository A container for user-movie information.
	 * @param genreRepository A container for genre information.
	 */
	public LoadMoviesRunner(IDataStore store, IUserMovieRepository userMovieRepository, IGenreRepository genreRepository) {
		if (store == null)
			throw new IllegalArgumentException("store");
		if (userMovieRepository == null)
			throw new IllegalArgumentException("userMovieRepository");
		if (genreRepository == null)
			throw new IllegalArgumentException("genreRepository");

		_store = store;
		_userMovieRepository = userMovieRepository;
		_genreRepository = genreRepository;
	}

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Public Methods

	/**
	 * Loads the movies.
	 * @param callback The callback function to execute once everything has been retrieved.
	 */
	public void run(IParameterizedEventHandler<IContainer> callback) {
		final Handler handler = createHandler(callback);
		beginLoadMoviesThread(handler);
		beginLoadGenresThread(handler);
	}

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Private Methods

	/**
	 * Creates the message handler for loading all movies.
	 * @param callback The callback function to execute once everything has been retrieved.
	 * @return handler The created handler.
	 */
	private android.os.Handler createHandler(final IParameterizedEventHandler<IContainer> callback) {
		return new android.os.Handler() {
			private Map<String,Boolean> _status;
			private UserMovieCollection _movies;
			private GenreCollection _genres;

			@Override
			public void handleMessage(Message msg) {
				Bundle bundle = msg.getData();
				if (bundle.containsKey("moviecollectionid"))
					_movies = _store.getData(UUID.fromString(bundle.getString("moviecollectionid")), UserMovieCollection.class);
				if (bundle.containsKey("genresid"))
					_genres = _store.getData(UUID.fromString(bundle.getString("genresid")), GenreCollection.class);

				if (_movies == null || _genres == null)
					return;

				if (callback != null) {
					callback.run(new IContainer() {
						public <T> T get(Class<T> type) {
							if (type == UserMovieCollection.class)
								return type.cast(_movies);
							else if (type == GenreCollection.class)
								return type.cast(_genres);
							return null;
						}
					});
				}
			}
		};
	}

	/**
	 * Begins the thread used to load the movies from the remote source.
	 * @param handler The handler used to notify the calling thread on completion.
	 */
	private void beginLoadMoviesThread(final Handler handler) {
		new Thread(new Runnable() {
			public void run() {
				UserMovieCollection movies = new UserMovieCollection(_userMovieRepository.getAll());
				UUID id = _store.addData(movies);

				Bundle bundle = new Bundle();
				bundle.putString("moviecollectionid", id.toString());

				Message message = new Message();
				message.setData(bundle);

				handler.sendMessage(message);
			}
		}).start();
	}

	/**
	 * Begins the thread used to load the movies from the remote source.
	 * @param handler The handler use to notify the calling thread on completion.
	 */
	private void beginLoadGenresThread(final Handler handler) {
		new Thread(new Runnable() {
			public void run() {
				List<Genre> genres = _genreRepository.getAll();
				UUID id = _store.addData(new GenreCollection(genres));

				Bundle bundle = new Bundle();
				bundle.putString("genresid", id.toString());

				Message message = new Message();
				message.setData(bundle);

				handler.sendMessage(message);
			}
		}).start();
	}
}
