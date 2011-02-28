package model;

/**
 * Defines a container for the movie list model.
 */
public interface IMovieListModel {

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Public Methods

	/**
	 * Loads the recent movies for the logged in user.
	 */
	void loadRecentMovies();

}
