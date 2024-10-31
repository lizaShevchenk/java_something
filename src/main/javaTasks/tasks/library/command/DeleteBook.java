package tasks.library.command;

import tasks.library.author.Author;
import tasks.library.command.utils.CommandUtil;
import tasks.library.controller.View;
import tasks.library.models.Book;
import tasks.library.storage.Repository;

public class DeleteBook extends CommandUtil implements Command {

    private final View view;
    private final Repository repository;

    public DeleteBook(View view, Repository<Author> authorRepository, Repository repository) {
        super(view, authorRepository);
        this.view = view;
        this.repository = repository;
    }

    @Override
    public Boolean canHandle(String command) {
        return command.equalsIgnoreCase(CommandNames.DELETE.getCommandName());
    }

    @Override
    public void handle() {
        view.write("Storage " + repository.toString());
        view.write("Удалить ПУБЛИКАЦИЮ по индексу? Нажмите 0. Удалить КНИГУ введя ее данные? Нажмите 1. (По индексу можно удалить любую публикацию, по данным исключительно КНИГУ. Спасибо за понимание.)");
        int methodToDelete = view.readInt();
        if (methodToDelete == 0) {
            view.write("Введите индекс публикациии которую хотите удалить: ");
            int index = view.readInt();
            repository.deleteByIndex(index);
        } else if (methodToDelete == 1) {
            Book book = getBook();
            repository.delete(book);
        } else {
            view.write("Вы не выбрали корректный метод. Попробуйте снова.");
            handle();
        }
        view.write("Storage " + repository);
    }
}
