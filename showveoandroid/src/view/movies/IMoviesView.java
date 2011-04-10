package view.movies;

import domain.GenreCollection;
import domain.UserMovie;
import domain.UserMovieCollection;
import service.event.IParameterizedEventHandler;
import view.IBaseView;

import java.util.List;

/**
 * Defines a view for the main movie list.
 */
public interface IMoviesView extends IBaseView {

	//------------------------------------------------------------------------------------------------------------------
	//	Public Methods

	/**
	 * Sets a collection of movies by name.
	 * @param name The name of the collection.
	 * @param label The label to show for the collection.
	 * @param movies The movie collection.
	 */
	void setMovieCollectionByName(String name, String label, List<UserMovie> movies);

	/**
	 * Sets a collection of movies for a genre.
	 * @param genres The genre collection.
	 * @param movies The list of movies.
	 */
	void setGenreMovies(GenreCollection genres, UserMovieCollection movies);

	/**
	 * Updates the viewable list of genre movies.
	 * @param movies The movies collection filtered by genre.
	 */
	void updateGenreMovies(UserMovieCollection movies);

	//------------------------------------------------------------------------------------------------------------------
	//	Event Handlers

	/**
	 * Fired after the user changes the genre.
	 * @param handler The event handler.
	 */
	void onGenreChangedHandler(IParameterizedEventHandler<String> handler);

	/**
	 * Fired after the user has selected a movie.
	 * @param handler The event handler.
	 */
	void onMovieSelected(IParameterizedEventHandler<UserMovie> handler);
}
