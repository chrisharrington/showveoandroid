package com.showveo.android.movies;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import com.showveo.android.BaseView;
import com.showveo.android.R;
import container.DR;
import controller.IMoviesController;
import domain.Genre;
import domain.GenreCollection;
import domain.UserMovie;
import domain.UserMovieCollection;
import service.event.IEventHandler;
import view.movies.IMoviesView;

import java.util.ArrayList;
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
		return super.onCreatePanelMenu(featureId, menu);
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
			item.setVisible(_tabs.get(label).getView().getVisibility() == View.INVISIBLE);
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
				tab.getView().setVisibility(View.VISIBLE);
				setTitle("Movies - " + tab.getLabel());
			}
			else
				tab.getView().setVisibility(View.INVISIBLE);
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
		RelativeLayout layout = (RelativeLayout) findViewById(R.id.layout);

		boolean isFirst = _tabs.size() == 0;

		ListView list = new ListView(this);
		list.setVisibility(isFirst ? View.VISIBLE : View.INVISIBLE);
		list.setAdapter(new MoviesArrayAdapter(this, R.layout.movielistview, movies, (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE)));
		layout.addView(list);

		if (isFirst)
			this.setTitle("Movies - " + label);

		_tabs.put(label, new Tab(name, label, movies, list));
	}

	/**
	 * Sets a collection of movies for a genre.
	 * @param genres The genre collection.
	 * @param movies The list of movies.
	 */
	public void setGenreMovies(GenreCollection genres, UserMovieCollection movies) {
		String label = "Genres";
		RelativeLayout layout = (RelativeLayout) findViewById(R.id.layout);

		boolean isFirst = _tabs.size() == 0;

		RelativeLayout relative = new RelativeLayout(this);
		relative.setVisibility(isFirst ? View.VISIBLE : View.INVISIBLE);

		Spinner spinner = new Spinner(this);
		spinner.setId(1);
		List<String> genreStrings = new ArrayList<String>();
		for (Genre genre : genres)
			genreStrings.add(genre.getName());
		spinner.setAdapter(new ArrayAdapter<String>(this, R.layout.moviesspinneritem, genreStrings));
		relative.addView(spinner, new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT));

	  	ListView list = new ListView(this);
		list.setId(2);
		list.setAdapter(new MoviesArrayAdapter(this, R.layout.movielistview, movies, (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE)));

		RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		lp.addRule(RelativeLayout.BELOW, spinner.getId());

		relative.addView(list, lp);

		layout.addView(relative, new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT));

		if (isFirst)
			this.setTitle("Movies - " + label);

		_tabs.put(label, new Tab("genres", label, movies, relative));
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
		private View _view;

		public String getName() { return _name; }
		public String getLabel() { return _label; }
		public List<UserMovie> getMovies() { return _movies; }
		public View getView() { return _view; }

		public Tab(String name, String label, List<UserMovie> movies, View view) {
			_name = name;
			_label = label;
			_movies = movies;
			_view = view;
		}
	}
}
