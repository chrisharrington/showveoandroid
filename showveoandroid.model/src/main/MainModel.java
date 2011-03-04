package main;

import model.IMainModel;
import view.main.IMainMenuItem;
import view.main.IMainView;
import view.main.MainMenuType;

import java.util.ArrayList;
import java.util.List;

/**
 * The model for the main page.
 */
public class MainModel implements IMainModel {

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Data Members

	//	The view for the main page.
	private IMainView _view;

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Public Methods

	/**
	 * Registers the view with the model.
	 *
	 * @param view THe view to register.
	 */
	public void registerView(IMainView view) {
		if (view == null)
			throw new IllegalArgumentException("view");

		_view = view;
	}

	/**
	 * Retrieves the main menu entries and loads them into the view.
	 */
	public void loadMenuEntries() {
		if (_view == null)
			throw new NullPointerException("The view has not yet been set.");

		List<IMainMenuItem> items = new ArrayList<IMainMenuItem>();
		items.add(new MainMenuItem("Movies", "Displays a list of movies available for viewing.", MainMenuType.Movies));
		items.add(new MainMenuItem("TV", "Shows a list of available TV shows to watch.", MainMenuType.TV));
		_view.setMenuEntries(items);
	}
}
