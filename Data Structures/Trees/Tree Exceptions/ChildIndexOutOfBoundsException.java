package TreeExceptions;

/**
 * Exception when a node is trying to retrieve a child with 
 * an index that is out of bounds.
 * @author Tim Fleece
 *
 */
public class ChildIndexOutOfBoundsException extends Exception {
	private static final long serialVersionUID = 12345L;

	public ChildIndexOutOfBoundsException() {
		super("Trying to get child at an invalid index.");
	}

	public ChildIndexOutOfBoundsException(int index) {
		super("Trying to get child at an invalid index: " + Integer.toString(index));
	}

	public ChildIndexOutOfBoundsException(String message) {
		super(message);
	}

	public ChildIndexOutOfBoundsException(int index, String location) {
		super("Trying to get child at an invalid index: " + Integer.toString(index) + " at location: " + location);
	}
}