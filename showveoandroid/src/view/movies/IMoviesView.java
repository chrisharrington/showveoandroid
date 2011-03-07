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
	 * Sets a collection of movies by name.
	 * @param name The name of the collection.
	 * @param label The label to show for the collection.
	 * @param movies The movie collection.
	 */
	void setMovieCollectionByName(String name, String label, List<UserMovie> movies);

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Event Handlers

	/**
	 * Fired after the view has loaded.
	 * @param handler The event handler.
	 */
	void onLoadHandler(onLoad handler);
	static interface onLoad extends IEventHandler {}

}
