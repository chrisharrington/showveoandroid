package view.movies;

import domain.UserMovie;
import service.event.IEventHandler;
import view.IBaseView;

import java.util.List;

/**
 * Defines a view for the main movie list.
 */
public interface IMoviesView extends IBaseView {

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Public Methods

	/**
	 * Sets the list of all movies.
	 * @param movies The list of movies.
	 */
	void setMovies(List<UserMovie> movies);

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Event Handlers

	/**
	 * Fired after the view has loaded.
	 * @param handler The event handler.
	 */
	void onLoadHandler(onLoad handler);
	static interface onLoad extends IEventHandler {}

}
