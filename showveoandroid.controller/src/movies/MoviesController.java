package movies;

import controller.IMoviesController;
import model.IMoviesModel;
import view.movies.IMoviesView;

/**
 * The controller for the movie list view.
 */
public class MoviesController implements IMoviesController {

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Data Members

	//	The model containing the state of the movie list page.
	private final IMoviesModel _model;

	//	The view for to be controlled.
	private IMoviesView _view;

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Constructors

	/**
	 * The default constructor.
	 * @param model The model containing the state of the movie list page.
	 */
	public MoviesController(IMoviesModel model) {
		if (model == null)
			throw new IllegalArgumentException("model");

		_model = model;
	}

	/**
	 * Registers the view with this controller.
	 * @param view The view to register.
	 */
	public void registerView(IMoviesView view) {
		if (view == null)
			throw new IllegalArgumentException("view");

		_view = view;
		_model.registerView(view);

		loadHandlers();
	}

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Private Methods

	/**
	 * Loads the event handlers.
	 */
	private void loadHandlers() {
		_view.onLoadHandler(new IMoviesView.onLoad() {
			public <T> void run(T data) {
				_model.loadMovies();
			}
		});
	}

}