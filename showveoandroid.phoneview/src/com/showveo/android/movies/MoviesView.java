package com.showveo.android.movies;

import android.os.Bundle;
import com.showveo.android.BaseView;
import com.showveo.android.R;
import container.DR;
import controller.IMoviesController;
import domain.UserMovie;
import service.event.IEventHandler;
import view.movies.IMoviesView;

import java.util.List;

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
        setContentView(R.layout.main);

		if (_onLoad != null)
			_onLoad.run(null);
    }

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Public Methods

	/**
	 * Sets the list of all movies.
	 *
	 * @param movies The list of movies.
	 */
	public void setMovies(List<UserMovie> movies) {
		System.out.println("Movies set!");
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
