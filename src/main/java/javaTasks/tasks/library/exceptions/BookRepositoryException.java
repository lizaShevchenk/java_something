package javaTasks.tasks.library.exceptions;

public class BookRepositoryException extends RepositoryException {

    public BookRepositoryException(String message, Exception exception) {
        super(message, exception);
    }
}
