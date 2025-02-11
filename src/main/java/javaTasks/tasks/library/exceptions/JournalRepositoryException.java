package javaTasks.tasks.library.exceptions;

public class JournalRepositoryException extends RepositoryException {

    public JournalRepositoryException(String message, Exception exception) {
        super(message, exception);
    }
}
