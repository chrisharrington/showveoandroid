package movies;

import controller.IMovieDetailsController;
import model.movies.IMovieDetailsModel;
import view.moviedetails.IMovieDetailsView;

/**
 * A controller for the movie details page.
 */
public class MovieDetailsController implements IMovieDetailsController {

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Data Members

	//	The view for this controller.
	private IMovieDetailsView _view;

	//	The model used by the controller to retreive state information.
	private final IMovieDetailsModel _model;

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Constructors

	/**
	 * The default constructor.
	 * @param model The model used by the controller to retreive state information.
	 */
	public MovieDetailsController(IMovieDetailsModel model) {
		if (model == null)
			throw new IllegalArgumentException("model");

		_model = model;
	}

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Public Methods

	/**
	 * Registers the view with this controller.
	 * @param view The view to register.
	 */
	public void registerView(IMovieDetailsView view) {
		if (view == null)
			throw new IllegalArgumentException("view");

		_view = view;
	}
}
