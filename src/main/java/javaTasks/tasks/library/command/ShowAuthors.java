package javaTasks.tasks.library.command;

import javaTasks.tasks.library.author.Author;
import javaTasks.tasks.library.controller.View;
import javaTasks.tasks.library.storage.Repository;

public class ShowAuthors implements Command {

    private final View view;
    private final Repository<Author> repository;

    public ShowAuthors(View view, Repository<Author> repository) {
        this.view = view;
        this.repository = repository;
    }

    @Override
    public Boolean canHandle(String command) {
        return command.equalsIgnoreCase(CommandNames.SHOW_AUTHORS.getCommandName());
    }

    @Override
    public void handle() {
        view.write(repository.toString());
    }
}
