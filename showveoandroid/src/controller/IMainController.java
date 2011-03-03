package controller;

import view.IMainView;

/**
 * Defines a controller for the main page.
 */
public interface IMainController {

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Public Methods

	/**
	 * Registers the view with this controller.
	 * @param view The view to register.
	 */
	void registerView(IMainView view);

}
