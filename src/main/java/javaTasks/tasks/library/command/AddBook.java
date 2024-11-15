package javaTasks.tasks.library.command;

import javaTasks.tasks.library.author.Author;
import javaTasks.tasks.library.command.utils.CommandUtil;
import javaTasks.tasks.library.controller.View;
import javaTasks.tasks.library.models.Book;
import javaTasks.tasks.library.models.Publication;
import javaTasks.tasks.library.storage.Repository;

public class AddBook extends CommandUtil implements Command {

    private final View view;
    private final Repository<Publication> repository;

    public AddBook(View view, Repository<Publication> repository, Repository<Author> inMemoryAuthorStorageRepository) {
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
