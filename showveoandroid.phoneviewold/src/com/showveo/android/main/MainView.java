package com.showveo.android.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.showveo.android.BaseView;
import com.showveo.android.R;
import container.DR;
import controller.IMainController;
import service.event.IParameterizedEventHandler;
import view.main.IMainMenuItem;
import view.main.IMainView;
import view.main.MainMenuType;

import java.util.List;

public class MainView extends BaseView implements IMainView
{

	//------------------------------------------------------------------------------------------------------------------
	//	Data Members

	//	The controller used to handle view events.
	private final IMainController _controller;

	//	The menu item selected handler.  Fired when the user clicks on a menu item.
	private IParameterizedEventHandler<MainMenuType> _onMenuItemSelected;

	//------------------------------------------------------------------------------------------------------------------
	//	Constructors

	/**
	 * The default constructor.
	 * @param controller The controller used to handle view events.
	 */
	public MainView(IMainController controller) {
		if (controller == null)
			throw new IllegalArgumentException("controller");

		_controller = controller;
	}

	/**
	 * The empty constructor.  Pulls the required components from the dependency resolver.
	 */
	public MainView() {
		this(DR.get(IMainController.class));
	}

	//------------------------------------------------------------------------------------------------------------------
	//	Public Methods

    /**
     * Called during the onCreate method of the activity, before the onLoad handler is fired.
     * @param arg Any arguments passed from the calling activity.
     */
    @Override
    protected void run(Object arg) {
        _controller.registerView(this);

        setContentView(R.layout.main);
    }

	/**
	 * Sets the list of menu entries.
	 * @param items The list of menu entries.
	 */
	public void setMenuEntries(final List<IMainMenuItem> items) {
		ListView list = (ListView) findViewById(R.id.lvMain);
		list.setAdapter(new MainMenuItemArrayAdapter(this, R.layout.mainlistview, items, (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE)));
		list.setOnItemClickListener(new ListView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
				if (_onMenuItemSelected != null)
					_onMenuItemSelected.run(items.get(position).getType());
			}
		});
	}

	//------------------------------------------------------------------------------------------------------------------
	//	Event Handlers

	/**
	 * Fired after the user has selected a menu item.
	 * @param handler The event handler.
	 */
	public void onMenuItemSelectedHandler(IParameterizedEventHandler<MainMenuType> handler) {
		if (handler == null)
			throw new IllegalArgumentException("handler");

		_onMenuItemSelected = handler;
	}

}
