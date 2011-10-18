package com.showveo.android.moviedetails;

import android.widget.TextView;
import com.showveo.android.BaseView;
import com.showveo.android.R;
import container.DR;
import controller.IMovieDetailsController;
import domain.UserMovie;
import view.moviedetails.IMovieDetailsView;

/**
 * A view for the movie details page.
 */
public class MovieDetailsView extends BaseView implements IMovieDetailsView {

	//------------------------------------------------------------------------------------------------------------------
	//	Data Members

	//	The controller for the movie details view.
	private final IMovieDetailsController _controller;

	//------------------------------------------------------------------------------------------------------------------
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

    /**
     * The empty constructor  Pulls all required components from the dependency resolver.
     */
    public MovieDetailsView() {
        this(DR.get(IMovieDetailsController.class));
    }

	//------------------------------------------------------------------------------------------------------------------
	//	Public Methods

    /**
     * Called during the onCreate method of the activity, before the onLoad handler is fired.
     * @param arg Any arguments passed from the calling activity.
     */
    @Override
    protected void run(Object arg) {
        _controller.registerView(this);

        setContentView(R.layout.moviedetails);
		setTitle("Movies");

        if (arg.getClass() != UserMovie.class)
            return;

        UserMovie movie = (UserMovie) arg;

        TextView text = (TextView) findViewById(R.id.text);
        text.setText(movie.getMovie().getName());
    }

}
