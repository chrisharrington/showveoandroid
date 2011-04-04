package view.main;

import service.event.IEmptyEventHandler;
import service.event.IParameterizedEventHandler;
import view.IBaseView;

import java.util.List;

/**
 * Defines a view for the main page.
 */
public interface IMainView extends IBaseView {

	//------------------------------------------------------------------------------------------------------------------
	//	Public Methods

	/**
	 * Sets the list of menu entries.
	 * @param items The list of menu entries.
	 */
	void setMenuEntries(List<IMainMenuItem> items);

	//------------------------------------------------------------------------------------------------------------------
	//	Event Handlers

	/**
	 * Fired after the view has loaded.
	 * @param handler The event handler.
	 */
	void onLoadHandler(IEmptyEventHandler handler);

	/**
	 * Fired after the user has selected a menu item.
	 * @param handler The event handler.
	 */
	void onMenuItemSelectedHandler(IParameterizedEventHandler<MainMenuType> handler);

}
