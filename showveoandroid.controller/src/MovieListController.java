import controller.IMovieListController;
import model.IMovieListModel;
import service.event.IEventHandler;
import view.IMovieListView;

/**
 * The controller for the movie list view.
 */
public class MovieListController implements IMovieListController {

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Data Members

	//	The model containing the state of the movie list page.
	private final IMovieListModel _model;

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Constructors

	/**
	 * The default constructor.
	 * @param view The view controlled by the controller.
	 * @param model The model containing the state of the movie list page.
	 */
	public MovieListController(IMovieListView view, IMovieListModel model) {
		if (view == null)
			throw new IllegalArgumentException("view");
		if (model == null)
			throw new IllegalArgumentException("model");

		_model = model;

		view.onLoad(new OnLoad());
	}

	//----------------------------------------------------------------------------------------------------------------------------------
	//	OnLoad Handler

	/**
	 * A private class used to handle the load event of the movie list view.
	 */
	private class OnLoad implements IEventHandler {

		/**
		 * Executed on a notification.
		 */
		public void run() {
			_model.loadRecentMovies();
		}
	}

}
