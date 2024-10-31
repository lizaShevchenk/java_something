package tasks.library.command;

import tasks.library.controller.View;
import tasks.library.storage.Repository;

public class ShowStorage<T> implements Command {

    private final View view;
    private final Repository<T> repository;

    public ShowStorage(View view, Repository<T> repository) {
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
