package exceptions;

public class PreexistingEntityException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1260747312789134563L;
	public PreexistingEntityException(String message, Throwable cause) {
        super(message, cause);
    }
    public PreexistingEntityException(String message) {
        super(message);
    }
}
