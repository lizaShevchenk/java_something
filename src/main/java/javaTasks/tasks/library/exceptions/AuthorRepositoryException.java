package javaTasks.tasks.library.exceptions;

public class AuthorRepositoryException extends RepositoryException {

    public AuthorRepositoryException(String message) {
        super(message);
    }

    public AuthorRepositoryException(String message, Exception exception) {
        super(message, exception);
    }
}
