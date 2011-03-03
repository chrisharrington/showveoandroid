package view;

import service.event.IEventHandler;

import java.util.List;

/**
 * Defines a view for the main page.
 */
public interface IMainView {

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Public Methods

	/**
	 * Sets the list of menu entries.
	 * @param items The list of menu entries.
	 */
	void setMenuEntries(List<IMainMenuItem> items);

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Event Handlers

	/**
	 * Fired after the view has loaded.
	 * @param handler The event handler.
	 */
	void onLoad(IEventHandler handler);

}
