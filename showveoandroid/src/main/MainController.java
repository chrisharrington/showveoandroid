package main;

import controller.IMainController;
import model.IMainModel;
import service.event.IEmptyEventHandler;
import service.event.IParameterizedEventHandler;
import view.ActivityType;
import view.main.IMainView;
import view.main.MainMenuType;

/**
 * The controller for the main page.
 */
public class MainController implements IMainController {

	//------------------------------------------------------------------------------------------------------------------
	//	Data Members

	//	The model for the main page.
	private final IMainModel _model;

	//	The view for the main page.
	private IMainView _view;

	//------------------------------------------------------------------------------------------------------------------
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

	//------------------------------------------------------------------------------------------------------------------
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

	//------------------------------------------------------------------------------------------------------------------
	//	Private Methods

	/**
	 * Loads the event handlers.
	 */
	private void loadHandlers() {
		_view.onLoadHandler(new IEmptyEventHandler() {
			public void run() {
				_model.loadMenuEntries();
			}
		});

		_view.onMenuItemSelectedHandler(new IParameterizedEventHandler<MainMenuType>() {
			public void run(MainMenuType data, Throwable... error) {
				if (data == null)
					throw new IllegalArgumentException("data");

				switch (data)  {
					case Movies:
						_view.loadActivity(ActivityType.Movies);
						break;
					case TV:
						break;
				}
			}
		});
	}

}
