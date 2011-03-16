package com.showveo.android.movies;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ListView;
import com.showveo.android.BaseView;
import com.showveo.android.R;
import container.DR;
import controller.IMoviesController;
import domain.UserMovie;
import service.event.IEventHandler;
import view.movies.IMoviesView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The view for the movie list page.
 */
public class MoviesView extends BaseView implements IMoviesView {

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Data Members

	//	The load event handler.  Fired after the view loads.
	private IEventHandler _onLoad;

	//	The controller used to control this view.
	private final IMoviesController _controller;

	//	The dictionary of map items, referenced by name.
	private final Map<String, Tab> _tabs;

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Constructors

	/**
	 * The default constructor.
	 * @param controller The controller used to control this view.
	 */
	public MoviesView(IMoviesController controller) {
		if (controller == null)
			throw new IllegalArgumentException("controller");

		_controller = controller;
		_tabs = new HashMap<String, Tab>();
	}

	/**
	 * The empty constructor.
	 */
	public MoviesView() {
		this(DR.get(IMoviesController.class));
	}

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Overridden Methods

	/**
	 * Called when this ActivityType is first created.
	 * @param savedInstanceState Any saved instance state information.
	 */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
		_controller.registerView(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.movies);
		setTitle("Movies");

		if (_onLoad != null)
			_onLoad.run(null);
    }

	/**
	 * Called when the user has opened the menu for the first time.  Creates the menu from the tab dictionary.
	 * @param featureId The feature ID.
	 * @param menu The menu to which tab buttons should be added.
	 * @return A flag of some sort...
	 */
	@Override
	public boolean onCreatePanelMenu(int featureId, Menu menu) {
		for (Tab tab : _tabs.values())
			menu.add(tab.getLabel());
		return super.onCreatePanelMenu(featureId, menu);	//To change body of overridden methods use File | Settings | File Templates.
	}

	/**
	 * Called whenever the menu is opened.  Hides and shows the appropriate menu items.
	 * @param menu The menu.
	 * @return A flag of some sort...
	 */
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		for (int i = 0; i < menu.size(); i++) {
			MenuItem item = menu.getItem(i);
			String label = item.getTitle().toString();
			item.setVisible(_tabs.get(label).getList().getVisibility() == View.INVISIBLE);
		}
		return super.onPrepareOptionsMenu(menu);
	}

	/**
	 * Called when the user clicks on a menu item.  Shows the appropriate movie list.
	 * @param featureId The feature ID.
	 * @param item The selected menu item.
	 * @return A flag of some sort...
	 */
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		for (Tab tab : _tabs.values()) {
			if (tab.getLabel().equals(item.getTitle().toString())) {
				tab.getList().setVisibility(View.VISIBLE);
				setTitle("Movies - " + tab.getLabel());
			}
			else
				tab.getList().setVisibility(View.INVISIBLE);
		}
		return super.onMenuItemSelected(featureId, item);
	}

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Public Methods

	/**
	 * Sets a collection of movies by name.
	 * @param name The name of the collection.
	 * @param label The name to show for the collection.
	 * @param movies The movie collection.
	 */
	public void setMovieCollectionByName(String name, String label, List<UserMovie> movies) {
		FrameLayout layout = (FrameLayout) findViewById(R.id.framelayout);

		boolean isFirst = _tabs.size() == 0;

		ListView list = new ListView(this);
		list.setVisibility(isFirst ? View.VISIBLE : View.INVISIBLE);
		list.setAdapter(new MoviesArrayAdapter(this, R.layout.movielistview, movies, (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE)));
		layout.addView(list);

		if (isFirst)
			this.setTitle("Movies - " + label);

		_tabs.put(label, new Tab(name, label, movies, list));
	}

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Event Handlers

	/**
	 * Fired after the view has loaded.
	 * @param handler The event handler.
	 */
	public void onLoadHandler(onLoad handler) {
		if (handler == null)
			throw new IllegalArgumentException("handler");

		_onLoad = handler;
	}

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Tab Class

	/**
	 * A class used to keep track of tab information.
	 */
	private class Tab {
		private String _name;
		private String _label;
		private List<UserMovie> _movies;
		private ListView _list;

		public String getName() { return _name; }
		public String getLabel() { return _label; }
		public List<UserMovie> getMovies() { return _movies; }
		public ListView getList() { return _list; }

		public Tab(String name, String label, List<UserMovie> movies, ListView list) {
			_name = name;
			_label = label;
			_movies = movies;
			_list = list;
		}
	}
}
