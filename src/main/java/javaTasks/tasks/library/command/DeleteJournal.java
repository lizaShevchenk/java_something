package javaTasks.tasks.library.command;

import javaTasks.tasks.library.controller.View;
import javaTasks.tasks.library.models.Journal;
import javaTasks.tasks.library.storage.Repository;

public class DeleteJournal implements Command {

    private final View view;
    private final Repository<Journal> repository;

    public DeleteJournal(View view, Repository<Journal> repository) {
        this.view = view;
        this.repository = repository;
    }

    @Override
    public Boolean canHandle(String command) {
        return command.equalsIgnoreCase(CommandNames.DELETE_JOURNAL.getCode());
    }

    @Override
    public void handle() {
        view.write("Journal repository: " + repository.toString());
        view.write("Удалить ПУБЛИКАЦИЮ по индексу? Нажмите 0. Удалить КНИГУ введя ее данные? Нажмите 1. To exit, press 2.");
        int methodToDelete = view.readInt();
        if (methodToDelete == 0) {
            view.write("Введите индекс публикациии которую хотите удалить: ");
            int index = view.readInt();
            repository.deleteByIndex(index);
        } else if (methodToDelete == 1) {
            Journal journal = getJournal();
            repository.delete(journal);
        } else if (methodToDelete == 2) new Exit(view).handle();
        else {
            view.write("Вы не выбрали корректный метод. Попробуйте снова.");
            handle();
        }
        view.write("Storage " + repository);
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
