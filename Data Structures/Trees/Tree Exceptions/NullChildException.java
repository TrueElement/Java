package TreeExceptions;


public class NullChildException extends Exception {
	private static final long serialVersionUID = 1234L;

	public NullChildException() {
		super("Trying to operate on null child.");
	}

	public NullChildException(String message) {
		super(message);
	}

	public NullChildException(String location, String key) {
		super("Null child at: " + location + " with key: " + key);
	}
}