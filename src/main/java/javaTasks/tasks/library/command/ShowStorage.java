package javaTasks.tasks.library.command;

import javaTasks.tasks.library.controller.View;
import javaTasks.tasks.library.models.Publication;
import javaTasks.tasks.library.storage.Repository;

public class ShowStorage implements Command {

    private final View view;
    private final Repository<Publication> repository;

    public ShowStorage(View view, Repository<Publication> repository) {
        this.view = view;
        this.repository = repository;
    }

    @Override
    public Boolean canHandle(String command) {
        return command.equalsIgnoreCase(CommandNames.SHOW_STORAGE.getCommandName());
    }

    @Override
    public void handle() {
        view.write(repository.toString());
    }
}
