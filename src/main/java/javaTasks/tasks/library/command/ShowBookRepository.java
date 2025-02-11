package javaTasks.tasks.library.command;

import javaTasks.tasks.library.controller.View;
import javaTasks.tasks.library.models.Book;
import javaTasks.tasks.library.storage.Repository;

public class ShowBookRepository implements Command {

    private final View view;
    private final Repository<Book> repository;

    public ShowBookRepository(View view, Repository<Book> repository) {
        this.view = view;
        this.repository = repository;
    }

    @Override
    public Boolean canHandle(String command) {
        return command.equalsIgnoreCase(CommandNames.SHOW_BOOKS.getCode());
    }

    @Override
    public void handle() {
        view.write(repository.toString());
    }
}
