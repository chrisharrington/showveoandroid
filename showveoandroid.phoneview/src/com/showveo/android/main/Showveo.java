package com.showveo.android.main;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ListView;
import com.showveo.android.R;
import container.DR;
import controller.IMainController;
import service.event.IEventHandler;
import view.IMainMenuItem;
import view.IMainView;

import java.util.List;

public class Showveo extends Activity implements IMainView
{

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Data Members

	//	The controller used to handle view events.
	private final IMainController _controller;

	//	The load event handler.  Fired when the event loads.
	private IEventHandler _onLoad;

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Constructors

	/**
	 * The default constructor.
	 * @param controller The controller used to handle view events.
	 */
	public Showveo(IMainController controller) {
		if (controller == null)
			throw new IllegalArgumentException("controller");

		_controller = controller;
	}

	/**
	 * The empty constructor.  Pulls the required components from the dependency resolver.
	 */
	public Showveo() {
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
			_onLoad.run();
    }

	/**
	 * Sets the list of menu entries.
	 *
	 * @param items The list of menu entries.
	 */
	public void setMenuEntries(List<IMainMenuItem> items) {
		ListView list = (ListView) findViewById(R.id.lvMain);
		list.setAdapter(new MainMenuItemArrayAdapter(this, R.layout.mainlistview, items, (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE)));
	}

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Events

	/**
	 * Fired after the view has loaded.
	 * @param handler The event handler.
	 */
	public void onLoad(IEventHandler handler) {
		if (handler == null)
			throw new IllegalArgumentException("handler");

		_onLoad = handler;
	}
}
