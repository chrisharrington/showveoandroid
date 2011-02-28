package view;

import domain.UserMovie;
import service.event.IEventHandler;

import java.util.List;

/**
 * Defines a view for the main movie list.
 */
public interface IMovieListView {

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Public Methods

	/**
	 * Sets the recent movies list.
	 * @param list The list of recent movies.
	 */
	void setRecentMovies(List<UserMovie> list);

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Event Handlers

	/**
	 * Fired after the view has loaded.
	 * @param handler The event handler.
	 */
	void onLoad(IEventHandler handler);

}
