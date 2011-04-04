package com.showveo.android.movies;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import com.showveo.android.BaseView;
import com.showveo.android.R;
import container.DR;
import controller.IMoviesController;
import domain.*;
import service.event.IParameterizedEventHandler;
import view.movies.IMoviesView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The view for the movie list page.
 */
public class MoviesView extends BaseView implements IMoviesView {

	//------------------------------------------------------------------------------------------------------------------
	//	Data Members

	//	The genre changed event handler.  Fired after the user changes the genre.
	private IParameterizedEventHandler<String> _onGenreChanged;

	//	The movie selected handler.  Fired after the user has selected a movie.
	private IParameterizedEventHandler<Movie> _onMovieSelected;

	//	The controller used to control this view.
	private final IMoviesController _controller;

	//	The dictionary of map items, referenced by name.
	private final Map<String, Tab> _tabs;

	//------------------------------------------------------------------------------------------------------------------
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
	 * The empty constructor.  Pulls required elements from the dependency resolver.
	 */
	public MoviesView() {
		this(DR.get(IMoviesController.class));
	}

	//------------------------------------------------------------------------------------------------------------------
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

	//------------------------------------------------------------------------------------------------------------------
	//	Public Methods

	/**
	 * Sets a collection of movies by name.
	 * @param name The name of the collection.
	 * @param label The name to show for the collection.
	 * @param movies The movie collection.
	 */
	public void setMovieCollectionByName(String name, String label, final List<UserMovie> movies) {
		RelativeLayout layout = (RelativeLayout) findViewById(R.id.layout);

		boolean isFirst = _tabs.size() == 0;

		ListView list = new ListView(this);
		list.setVisibility(isFirst ? View.VISIBLE : View.INVISIBLE);
		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
				_onMovieSelected.run(movies.get(i).getMovie());
			}
		});
		list.setAdapter(new MoviesArrayAdapter(this, R.layout.movielistview, movies, (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE)));
		layout.addView(list);

		if (isFirst)
			this.setTitle("Movies - " + label);

		_tabs.put(label, new Tab(label, list));
	}

	/**
	 * Sets a collection of movies for a genre.
	 * @param genres The genre collection.
	 * @param movies The list of movies.
	 */
	public void setGenreMovies(GenreCollection genres, final UserMovieCollection movies) {
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
		spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
				String genre = (String) ((TextView) view).getText();
				if (_onGenreChanged != null)
					_onGenreChanged.run(genre);
			}

			public void onNothingSelected(AdapterView<?> adapterView) {}
		});
		relative.addView(spinner, new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT));

	  	ListView list = new ListView(this);
		list.setId(2);
		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
				_onMovieSelected.run(movies.get(i).getMovie());
			}
		});
		list.setAdapter(new MoviesArrayAdapter(this, R.layout.movielistview, movies, (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE)));

		RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		lp.addRule(RelativeLayout.BELOW, spinner.getId());

		relative.addView(list, lp);

		layout.addView(relative, new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT));

		if (isFirst)
			this.setTitle("Movies - " + label);

		_tabs.put(label, new Tab(label, relative));
	}

	/**
	 * Updates the viewable list of genre movies.
	 * @param movies The movies collection filtered by genre.
	 */
	public void updateGenreMovies(UserMovieCollection movies) {
		ListView list = (ListView) _tabs.get("Genres").getView().findViewById(2);
		list.setAdapter(new MoviesArrayAdapter(this, R.layout.movielistview, movies, (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE)));
	}

	//------------------------------------------------------------------------------------------------------------------
	//	Event Handlers

	/**
	 * Fired after the user changes the genre.
	 * @param handler The event handler.
	 */
	public void onGenreChangedHandler(IParameterizedEventHandler<String> handler) {
		if (handler == null)
			throw new IllegalArgumentException("handler");

		_onGenreChanged = handler;
	}

	/**
	 * Fired after the user has selected a movie.
	 * @param handler The event handler.
	 */
	public void onMovieSelected(IParameterizedEventHandler<Movie> handler) {
		if (handler == null)
			throw new IllegalArgumentException("handler");

		_onMovieSelected = handler;
	}

	//------------------------------------------------------------------------------------------------------------------
	//	Tab Class

	/**
	 * A class used to keep track of tab information.
	 */
	private class Tab {
		private String _label;
		private View _view;

		public String getLabel() { return _label; }
		public View getView() { return _view; }

		public Tab(String label, View view) {
			_label = label;
			_view = view;
		}
	}
}
