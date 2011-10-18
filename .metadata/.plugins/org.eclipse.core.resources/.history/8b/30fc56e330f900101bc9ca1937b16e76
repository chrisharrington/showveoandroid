package base;

import model.IBaseModel;

/**
 * Provides base functions for any model.
 */
public abstract class BaseModel<T> implements IBaseModel<T> {

	//------------------------------------------------------------------------------------------------------------------
	//	Data Members

	//	The view for the model.
	protected T _view;

	//------------------------------------------------------------------------------------------------------------------
	//	Public Methods

	/**
	 * Registers the given view with the model.
	 * @param view The view to register.
	 */
	public void registerView(T view) {
		if (view == null)
			throw new IllegalArgumentException("view");

		_view = view;
	}

}
