package exceptions;

public class NonexistentEntityException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = -5646798492106933140L;
	public NonexistentEntityException(String message, Throwable cause) {
        super(message, cause);
    }
    public NonexistentEntityException(String message) {
        super(message);
    }
}
