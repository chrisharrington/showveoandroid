package view;

/**
 * Defines a base view.
 */
public interface IBaseView {

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Public Methods

	/**
	 * Switches to another activity.
	 * @param type The type of activity.
	 */
	void loadActivity(ActivityType type);

	/**
	 * Loads the OS-defined movie activity.
	 * @param url The url of the movie to show.
	 */
	void loadMovieActivity(String url);

	/**
	 * Shows a loading message.
	 * @param message The loading message to show.
	 */
	void showLoading(String message);

	/**
	 * Hides any loading message.
	 */
	void hideLoading();

}
