package main;

import controller.IMainController;
import model.IMainModel;
import service.event.IEventHandler;
import view.IMainView;

/**
 * The controller for the main page.
 */
public class MainController implements IMainController {

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Data Members

	//	The model for the main page.
	private final IMainModel _model;

	//	The view for the main page.
	private IMainView _view;

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Constructors

	/**
	 * The default constructor.
	 * @param model The model for the main page.
	 */
	public MainController(IMainModel model) {
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
	public void registerView(IMainView view) {
		if (view == null)
			throw new IllegalArgumentException("view");

		_view = view;
		_model.registerView(view);

		view.onLoad(new LoadMenuEntries());
	}

	//----------------------------------------------------------------------------------------------------------------------------------
	//	LoadMenuEntries Event Handler

	/**
	 * An event handler used to load the menu entries on the view.
	 */
	private class LoadMenuEntries implements IEventHandler {

		/**
		 * Runs the event handler.
		 */
		public void run() {
			_model.loadMenuEntries();
		}

	}

}
