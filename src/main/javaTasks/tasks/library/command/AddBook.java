package tasks.library.command;

import tasks.library.author.Author;
import tasks.library.command.utils.CommandUtil;
import tasks.library.controller.View;
import tasks.library.models.Book;
import tasks.library.storage.Repository;

public class AddBook extends CommandUtil implements Command {

    private final View view;
    private final Repository repository;

    public AddBook(View view, Repository repository, Repository<Author> inMemoryAuthorStorageRepository) {
        super(view, inMemoryAuthorStorageRepository);
        this.view = view;
        this.repository = repository;
    }

    @Override
    public Boolean canHandle(String command) {
        return command.equalsIgnoreCase(CommandNames.ADD_BOOK.getCommandName());
    }

    @Override
    public void handle() {
        Book book = getBook();
        repository.add(book);
        view.write("Added book " + book.print());
    }

}
