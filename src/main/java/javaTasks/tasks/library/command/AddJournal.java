package javaTasks.tasks.library.command;

import javaTasks.tasks.library.controller.View;
import javaTasks.tasks.library.models.Journal;
import javaTasks.tasks.library.storage.Repository;

public class AddJournal implements Command {

    private final View view;
    private final Repository<Journal> repository;

    public AddJournal(View view, Repository<Journal> repository) {
        this.view = view;
        this.repository = repository;
    }

    @Override
    public Boolean canHandle(String command) {
        return command.equalsIgnoreCase(CommandNames.ADD_JOURNAL.getCode());
    }

    @Override
    public void handle() {
        view.write("Добавление журнала.");
        Journal journal = getJournal();
        try {
            repository.add(journal);
        } catch (RuntimeException ex) {
            view.write("Something wrong when adding journal, try again. Error: " + ex.getMessage().replace("\n", "\t"));
            handle();
        }
        System.out.println("Added journal " + journal.print());
    }

    private Journal getJournal() {
        String name;
        int countPages, number, publicationYear;

        view.write("\tВведите название журнала: ");
        name = view.read();
        view.write("Введите количество страниц (число) : ");
        countPages = view.readInt();
        view.write("Введите номер журнала (число) : ");
        number = view.readInt();
        view.write("Введите год публикации : ");
        publicationYear = view.readValueByPattern();

        return new Journal(name, countPages, number, publicationYear);
    }
}
