package com.showveo.android;

import android.app.Activity;
import android.os.Bundle;
import domain.UserMovie;
import service.event.IEventHandler;
import view.IMovieListView;

import java.util.List;

/**
 * The view for the movie list page.
 */
public class MovieListView extends Activity implements IMovieListView {

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Data Members

	//	The load event handler.  Fired after the view loads.
	IEventHandler _onLoad;

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Event Handlers

	/**
	 * Fired after the view has loaded.
	 * @param handler The event handler.
	 */
	public void onLoad(IEventHandler handler) {
		if (handler == null)
			throw new IllegalArgumentException("handler");

		_onLoad = handler;
	}

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Overridden Methods

	/**
	 * Called when this Activity is first created.
	 * @param savedInstanceState Any saved instance state information.
	 */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

		if (_onLoad != null)
			_onLoad.run();
    }

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Public Methods

	/**
	 * Sets the recent movies list.
	 * @param list The list of recent movies.
	 */
	public void setRecentMovies(List<UserMovie> list) {

	}
}
