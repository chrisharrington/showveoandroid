package model;

import view.main.IMainView;

/**
 * Defines a model for the main page.
 */
public interface IMainModel {

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Public Methods

	/**
	 * Registers the view with the model.
	 * @param view THe view to register.
	 */
	void registerView(IMainView view);

	/**
	 * Retrieves the main menu entries and loads them into the view.
	 */
	void loadMenuEntries();
}
