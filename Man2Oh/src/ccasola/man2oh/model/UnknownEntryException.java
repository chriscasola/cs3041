/*
 * References:
 * 	- http://www.seasite.niu.edu/cs580java/testexception.htm
 */

package ccasola.man2oh.model;

/**
 * An exception to be thrown when an unknown manual entry is requested
 */
@SuppressWarnings("serial")
public class UnknownEntryException extends Exception {

	/** The error message */
	protected String message;
	
	/**
	 * Construct the UnknownEntryException with the default message
	 */
	public UnknownEntryException() {
		super();
		message = "The manual entry you requested does not exist!";
	}
	
	/**
	 * Construct the UnknownEntryException with the given error message
	 * @param message the error message
	 */
	public UnknownEntryException(String message) {
		super();
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
}
