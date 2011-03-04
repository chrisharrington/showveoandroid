package com.showveo.android.main;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ListView;
import com.showveo.android.BaseView;
import com.showveo.android.R;
import container.DR;
import controller.IMainController;
import service.event.IEventHandler;
import view.main.IMainMenuItem;
import view.main.IMainView;

import java.util.List;

public class MainView extends BaseView implements IMainView
{

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Data Members

	//	The controller used to handle view events.
	private final IMainController _controller;

	//	The load event handler.  Fired when the activity loads.
	private IEventHandler _onLoad;

	//	The menu item selected handler.  Fired when the user clicks on a menu item.
	private IEventHandler _onMenuItemSelected;

	//----------------------------------------------------------------------------------------------------------------------------------
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

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Public Methods

    /**
	 * Called when the activity is first created.
	 */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
		_controller.registerView(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

		if (_onLoad != null)
			_onLoad.run(null);
    }

	/**
	 * Sets the list of menu entries.
	 * @param items The list of menu entries.
	 */
	public void setMenuEntries(List<IMainMenuItem> items) {
		ListView list = (ListView) findViewById(R.id.lvMain);
		list.setAdapter(new MainMenuItemArrayAdapter(this, R.layout.mainlistview, items, (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE), _onMenuItemSelected));
	}

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Events

	/**
	 * Fired after the view has loaded.
	 * @param handler The event handler.
	 */
	public void onLoadHandler(onLoad handler) {
		if (handler == null)
			throw new IllegalArgumentException("handler");

		_onLoad = handler;
	}

	/**
	 * Fired after the user has selected a menu item.
	 * @param handler The event handler.
	 */
	public void onMenuItemSelectedHandler(IEventHandler handler) {
		if (handler == null)
			throw new IllegalArgumentException("handler");

		_onMenuItemSelected = handler;
	}

}
