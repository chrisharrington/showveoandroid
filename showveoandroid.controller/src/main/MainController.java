package main;

import controller.IMainController;
import model.IMainModel;
import view.ActivityType;
import view.main.IMainView;
import view.main.MainMenuType;

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

		loadHandlers();
	}

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Private Methods

	/**
	 * Loads the event handlers.
	 */
	private void loadHandlers() {
		_view.onLoadHandler(new IMainView.onLoad() {
			public <T> void run(T data) {
				_model.loadMenuEntries();
			}
		});

		_view.onMenuItemSelectedHandler(new IMainView.onMenuItemSelected() {
			public <T> void run(T data) {
				if (data == null || !(data instanceof MainMenuType))
					throw new IllegalArgumentException("data");

				switch ((MainMenuType) data)  {
					case Movies:
						_view.switchActivity(ActivityType.Movies);
						break;
					case TV:
						break;
				}
			}
		});
	}

}
