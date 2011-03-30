package controller;

public interface IBaseController<TBaseView> {

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Public Methods

	/**
	 * Registers the view with this controller.
	 * @param view The view to register.
	 */
	void registerView(TBaseView view);

}
