package javaTasks.tasks.library.exceptions;

public class RepositoryException extends RuntimeException {

    protected RepositoryException(String message) {
        super(message);
    }

    protected RepositoryException(String message, Exception exception) {
        super(message, exception);
    }
}
