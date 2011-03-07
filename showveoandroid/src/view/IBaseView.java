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
	void switchActivity(ActivityType type);

	/**
	 * Shows a losding message.
	 * @param message The loading message to show.
	 */
	void showLoading(String message);

	/**
	 * Hides any loading message.
	 */
	void hideLoading();

}
