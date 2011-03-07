package movies;

import android.os.Bundle;
import android.os.Message;
import base.BaseModel;
import container.IDataStore;
import dataaccess.usermovie.IUserMovieRepository;
import domain.UserMovie;
import domain.UserMovieCollection;
import model.IMoviesModel;
import view.movies.IMoviesView;

import java.util.Collections;
import java.util.Comparator;
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
				Collections.sort(collection, new NameSorter());

				_view.hideLoading();
				_view.setMovieCollectionByName("recent", "Recent", deriveRecent(collection));
				_view.setMovieCollectionByName("favorites", "Favorites", deriveFavorites(collection));
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

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Private Methods

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

