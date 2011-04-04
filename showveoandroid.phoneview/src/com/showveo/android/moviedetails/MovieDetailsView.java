package com.showveo.android.moviedetails;

import android.os.Bundle;
import com.showveo.android.BaseView;
import com.showveo.android.R;
import controller.IMovieDetailsController;
import view.moviedetails.IMovieDetailsView;

/**
 * A view for the movie details page.
 */
public class MovieDetailsView extends BaseView implements IMovieDetailsView {

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Data Members

	//	The controller for the movie details view.
	private final IMovieDetailsController _controller;

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Constructors

	/**
	 * The default constructor.
	 * @param controller The controller for the movie details view.
	 */
	public MovieDetailsView(IMovieDetailsController controller) {
		if (controller == null)
			throw new IllegalArgumentException("controller");

		_controller = controller;
	}

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Public Methods

	/**
	 * Called when this ActivityType is first created.
	 * @param savedInstanceState Any saved instance state information.
	 */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
		_controller.registerView(this);
		super.onCreate(savedInstanceState);

		setContentView(R.layout.moviedetails);
		setTitle("Movies");
    }

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Event Handlers

}
