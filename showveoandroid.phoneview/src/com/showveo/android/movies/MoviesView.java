package com.showveo.android.movies;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import com.showveo.android.BaseTabView;
import com.showveo.android.R;
import com.showveo.android.ShowveoApplication;
import container.DR;
import controller.IMoviesController;
import domain.UserMovie;
import domain.UserMovieCollection;
import service.event.IEventHandler;
import view.movies.IMoviesView;

import java.util.List;
import java.util.UUID;

/**
 * The view for the movie list page.
 */
public class MoviesView extends BaseTabView implements IMoviesView {

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Data Members

	//	The load event handler.  Fired after the view loads.
	private IEventHandler _onLoad;

	//	The controller used to control this view.
	private final IMoviesController _controller;

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

		if (_onLoad != null)
			_onLoad.run(null);
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
		ShowveoApplication application = (ShowveoApplication) getApplication();
		UUID id = application.addData(new UserMovieCollection(movies));

		TabHost host = this.getTabHost();
		Intent intent = new Intent().setClass(this, MoviesTab.class);
		intent.putExtra("tabname", name);
		intent.putExtra("tabmoviesid", id.toString());
		TabHost.TabSpec spec = host.newTabSpec(name).setIndicator(label).setContent(intent);
		host.addTab(spec);
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
}
