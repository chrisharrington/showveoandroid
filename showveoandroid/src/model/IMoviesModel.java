package model;

import view.movies.IMoviesView;

/**
 * Defines a container for the movie list model.
 */
public interface IMoviesModel extends IBaseModel<IMoviesView> {

	//----------------------------------------------------------------------------------------------------------------------------------
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

}
