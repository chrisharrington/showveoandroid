package movies;

import android.os.Bundle;
import android.os.Message;
import base.BaseModel;
import container.IDataStore;
import dataaccess.genre.IGenreRepository;
import dataaccess.usermovie.IUserMovieRepository;
import domain.Genre;
import domain.GenreCollection;
import domain.UserMovie;
import domain.UserMovieCollection;
import model.IMoviesModel;
import view.movies.IMoviesView;

import java.util.*;

public class MoviesModel extends BaseModel<IMoviesView> implements IMoviesModel {

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Data Members

	//	A container for user-movie information.
	private final IUserMovieRepository _userMovieRepository;

	//	A container for genre information.
	private final IGenreRepository _genreRepository;

	//	A process-wide data store used for holding query results across threads.
	private final IDataStore _store;

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Constructors

	/**
	 * The default constructor.
	 * @param userMovieRepository A container for user-movie information.
	 * @param genreRepository A container for genre information.
	 * @param store The persistent data store.
	 */
	public MoviesModel(IUserMovieRepository userMovieRepository, IGenreRepository genreRepository, IDataStore store) {
		if (userMovieRepository == null)
			throw new IllegalArgumentException("userMovieRepository");
		if (genreRepository == null)
			throw new IllegalArgumentException("genreRepository");
		if (store == null)
			throw new IllegalArgumentException("store");

		_userMovieRepository = userMovieRepository;
		_genreRepository = genreRepository;
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

				Collections.sort(_movies, new NameSorter());

				_view.hideLoading();
				_view.setMovieCollectionByName("recent", "Recent", deriveRecent(_movies));
				_view.setMovieCollectionByName("favorites", "Favorites", deriveFavorites(_movies));
				_view.setGenreMovies(_genres, deriveGenres(_movies, _genres.get(0)));
				_view.setMovieCollectionByName("all", "All", _movies);

				_movies = null;
				_genres = null;
			}
		};

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

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Private Methods

	/**
	 * Derives a list of movies by genre.
	 * @param movies The collection of movies from which the derived movies should be retrieved.
	 * @param genre The genre to filter by.
	 */
	private UserMovieCollection deriveGenres(UserMovieCollection movies, Genre genre) {
		UserMovieCollection list = new UserMovieCollection();
		for (UserMovie info : movies)
			if (info.getMovie().getGenres().contains(genre))
				list.add(info);
		return list;
	}

	/**
	 * Derives a collection of only the most recent movies.
	 * @param collection The list of all movies.
	 * @return The list of recent movies, ordered by added date descending.
	 */
	private UserMovieCollection deriveRecent(UserMovieCollection collection) {
		UserMovieCollection recent = new UserMovieCollection();
		int count = 0;
		for (UserMovie info : collection) {
			if (count > 2)
				break;

			recent.add(info);
			count++;
		}
		Collections.sort(recent, new DateSorter());
		return recent;
	}

	/**
	 * Derives a collection of only the favorite movies.
	 * @param collection The list of all movies.
	 * @return The list of favorite movies, sorted by title.
	 */
	private UserMovieCollection deriveFavorites(UserMovieCollection collection) {
		UserMovieCollection favorites = new UserMovieCollection();
		for (UserMovie info : collection)
			if (info.isFavorite())
				favorites.add(info);
		return favorites;
	}

	//----------------------------------------------------------------------------------------------------------------------------------
	//	DateSorter Class

	/**
	 * A class used to sort a collection of user-movie information by movie added date, descending.
	 */
	private class DateSorter implements Comparator<UserMovie> {
		public int compare(UserMovie first, UserMovie second) {
			int comparison = first.getMovie().getAddDate().compareTo(second.getMovie().getAddDate());
			if (comparison > 0)
				return -1;
			else if (comparison == 0)
				return 0;
			else
				return 1;
		}
	}

	/**
	 * A class used to sort a collection of user-movie information by name, ascending.
	 */
	private class NameSorter implements Comparator<UserMovie> {
		public int compare(UserMovie first, UserMovie second) {
			return first.getMovie().getName().compareTo(second.getMovie().getName());
		}
	}
}

