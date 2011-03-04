package model;

/**
 * Defines a base model.
 */
public interface IBaseModel<T> {

	/**
	 * Registers the given view with the model.
	 * @param view The view to register.
	 */
	void registerView(T view);

}
