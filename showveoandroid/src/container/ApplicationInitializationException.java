package container;

/**
 * An exception indicating that the application failed to initialize.
 */
public class ApplicationInitializationException extends Throwable {

	//------------------------------------------------------------------------------------------------------------------
	//	Constructors

	/**
	 * The throwable constructor.
	 * @param throwable The inner exception for this exception.
	 */
	public ApplicationInitializationException(Throwable throwable) {
		super(throwable);
	}

}
