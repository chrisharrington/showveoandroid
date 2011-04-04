package movies;

import base.BaseModel;
import dataaccess.genre.IGenreRepository;
import domain.*;
import model.movies.IMoviesModel;
import service.event.IParameterizedEventHandler;
import view.ActivityType;
import view.movies.IMoviesView;

import java.util.Collections;
import java.util.Comparator;

public class MoviesModel extends BaseModel<IMoviesView> implements IMoviesModel {

	//------------------------------------------------------------------------------------------------------------------
	//	Data Members

	//	The retrieved user-movie information collection.
	private UserMovieCollection _movies;

	//	The retrieved genre information collection.
	private GenreCollection _genres;

	//	The base movie location.
	private final String _baseMovieLocation;

	//	The task used to load movies asynchronously.
	private final LoadMoviesTask _loadMoviesTask;

	//	A container for genre information.
	private final IGenreRepository _genreRepository;

	//------------------------------------------------------------------------------------------------------------------
	//	Constructors

	/**
	 * The default constructor.
	 * @param loadMoviesTask The task used to load movies asynchronously.
	 * @param genreRepository A container for genre information.
	 * @param baseMovieLocation The base movie location.
	 */
	public MoviesModel(LoadMoviesTask loadMoviesTask, IGenreRepository genreRepository, String baseMovieLocation) {
		if (loadMoviesTask == null)
			throw new IllegalArgumentException("loadMoviesTask");
		if (genreRepository == null)
			throw new IllegalArgumentException("genreRepository");
		if (baseMovieLocation == null || baseMovieLocation.equals(""))
			throw new IllegalArgumentException("baseMovieLocation");

		_genreRepository = genreRepository;
		_loadMoviesTask = loadMoviesTask;
		_baseMovieLocation = baseMovieLocation;
	}

	//------------------------------------------------------------------------------------------------------------------
	//	Public Methods

	/**
	 * Loads all of the movies.
	 */
	public void loadMovies() {
		_view.showLoading("Loading movies...");

		_loadMoviesTask.execute(new IParameterizedEventHandler<UserMovieCollection>() {
			public void run(UserMovieCollection movies, Throwable... error) {
				_movies = movies;
				if (_genres != null)
					onMoviesAndGenresLoaded(_movies, _genres);
			}
		});

		_genreRepository.getAll(new IParameterizedEventHandler<GenreCollection>() {
			public void run(GenreCollection genres, Throwable... error) {
				if (error.length > 0 && error[0] != null) {
					_view.showErrorMessage("An error has occurred while retrieving the genre list.");
					_view.hideLoading();
					_view.loadActivity(ActivityType.Main);
				}
				else {
					_genres = genres;
					if (_movies != null)
						onMoviesAndGenresLoaded(_movies, _genres);
				}
			}
		});
	}

	/**
	 * Loads genre movies with the given genre filter.
	 * @param genreName The genre filter.
	 */
	public void loadMoviesForGenre(String genreName) {
		if (genreName == null || genreName.equals(""))
			throw new IllegalArgumentException("genreName");

		_view.updateGenreMovies(deriveGenres(_movies, _genres.getByName(genreName)));
	}

	/**
	 * Loads a movie view.
	 * @param movie The movie to watch.
	 */
	public void loadMovieView(Movie movie) {
		if (movie == null)
			throw new IllegalArgumentException("movie");

		_view.loadMovieActivity(_baseMovieLocation + movie.getUrl());
	}

	//------------------------------------------------------------------------------------------------------------------
	//	Event Handlers

	/**
	 * Fired after both the movie and genre collections have been loaded.  Stores the data, then sends it back to the
	 * view.
	 * @param movies The retrieved movie collection.
	 * @param genres The retrieve genre collection.
	 */
	private void onMoviesAndGenresLoaded(UserMovieCollection movies, GenreCollection genres) {
		Collections.sort(movies, new NameSorter());

		_view.setMovieCollectionByName("recent", "Recent", deriveRecent(movies));
		_view.setMovieCollectionByName("favorites", "Favorites", deriveFavorites(movies));
		_view.setGenreMovies(_genres, deriveGenres(movies, genres.get(0)));
		_view.setMovieCollectionByName("all", "All", movies);
		_view.hideLoading();

		_movies = movies;
		_genres = genres;
	}

	//------------------------------------------------------------------------------------------------------------------
	//	Private Methods

	/**
	 * Derives a list of movies by genre.
	 * @param movies The collection of movies from which the derived movies should be retrieved.
	 * @param genre The genre to filter by.
	 * @return The derived genres movie list.
	 */
	private UserMovieCollection deriveGenres(UserMovieCollection movies, Genre genre) {
		if (genre == null)
			return movies;

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

	//------------------------------------------------------------------------------------------------------------------
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

