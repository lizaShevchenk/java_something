package javaTasks.tasks.library.command;

import javaTasks.tasks.library.controller.View;
import javaTasks.tasks.library.models.Journal;
import javaTasks.tasks.library.storage.Repository;

public class ShowJournalRepository implements Command {

    private final View view;
    private final Repository<Journal> repository;

    public ShowJournalRepository(View view, Repository<Journal> repository) {
        this.view = view;
        this.repository = repository;
    }

    @Override
    public Boolean canHandle(String command) {
        return command.equalsIgnoreCase(CommandNames.SHOW_JOURNALS.getCode());
    }

    @Override
    public void handle() {
        view.write(repository.toString());
    }
}
