package com.showveo.android.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.showveo.android.R;
import service.event.IEventHandler;
import view.main.IMainMenuItem;

import java.util.List;

public class MainMenuItemArrayAdapter extends ArrayAdapter<IMainMenuItem> {

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Data Members

	//	The collection of objects associated with the adapter.
	private final List<IMainMenuItem> _objects;

	//	Inflates layout.
	private final LayoutInflater _inflater;

	//	Fired after the user has selected a menu item.
	private final IEventHandler _onMenuItemSelected;

	//	The resource ID.
	private final int _resource;

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Constructors

	/**
	 * The default constructor.
	 * @param context The context in which the array adapter operates.
	 * @param resource The array adapter's resource.
	 * @param objects The list of objects to bind to the adapter.
	 * @param inflater Inflates layout.
	 * @param onMenuItemSelected Fired after the user has selected a menu item.
	 */
	public MainMenuItemArrayAdapter(Context context, int resource, List<IMainMenuItem> objects, LayoutInflater inflater, IEventHandler onMenuItemSelected) {
		super(context, resource, objects);

		_objects = objects;
		_resource = resource;
		_inflater = inflater;
		_onMenuItemSelected = onMenuItemSelected;
	}

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Public Methods

	/**
	 * The overridden getView method.
	 * @param position The position.
	 * @param view The view.
	 * @param parent The parent.
	 * @return The view.
	 */
	@Override
	public View getView(int position, View view, ViewGroup parent) {
		if (view == null)
			view = _inflater.inflate(_resource, null);

		final IMainMenuItem item = _objects.get(position);
		if (item == null)
			return view;

		view.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				if (_onMenuItemSelected != null)
					_onMenuItemSelected.run(item.getType());
			}
		});

		TextView title = (TextView) view.findViewById(R.id.tvTitle);
		if (title != null)
			title.setText(item.getTitle());

		TextView description = (TextView) view.findViewById(R.id.tvDescription);
		if(description != null)
			description.setText(item.getDescription());

		return view;
	}
}
