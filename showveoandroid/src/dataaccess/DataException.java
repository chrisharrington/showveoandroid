package dataaccess;

/**
 * Represents an error that occurs during regular data access operations.
 */
public class DataException extends Throwable {

	//------------------------------------------------------------------------------------------------------------------
	//	Constructors

	/**
	 * The default constructor.
	 * @param t The wrapped thrown exception.
	 */
	public DataException(Throwable t) {
		super(t);
	}
}
