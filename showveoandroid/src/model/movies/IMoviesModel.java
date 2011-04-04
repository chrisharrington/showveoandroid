package model.movies;

import domain.Movie;
import model.IBaseModel;
import view.movies.IMoviesView;

/**
 * Defines a container for the movie list model.
 */
public interface IMoviesModel extends IBaseModel<IMoviesView> {

	//------------------------------------------------------------------------------------------------------------------
	//	Public Methods

	/**
	 * Registers the view with the model.
	 * @param view THe view to register.
	 */
	void registerView(IMoviesView view);

	/**
	 * Loads all of the movies.
	 */
	void loadMovies();

	/**
	 * Loads genre movies with the given genre filter.
	 * @param genre The genre filter.
	 */
	void loadMoviesForGenre(String genre);

	/**
	 * Loads a movie view.
	 * @param movie The movie to watch.
	 */
	void loadMovieView(Movie movie);
}
