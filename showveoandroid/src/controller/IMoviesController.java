package controller;

import view.movies.IMoviesView;

/**
 * Defines a controller for the movie list view.
 */
public interface IMoviesController {

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Public Methods

	/**
	 * Registers the view with this controller.
	 * @param view The view to register.
	 */
	void registerView(IMoviesView view);

}
