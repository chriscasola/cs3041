package ccasola.man2oh.model;

/**
 * An exception to be thrown when an unknown manual entry is requested
 * 
 * Reference: http://www.seasite.niu.edu/cs580java/testexception.htm
 */
@SuppressWarnings("serial")
public class UnknownEntryException extends Exception {

	protected String message;
	
	public UnknownEntryException() {
		super();
		message = "The manual entry you requested does not exist!";
	}
	
	public UnknownEntryException(String message) {
		super();
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
}
