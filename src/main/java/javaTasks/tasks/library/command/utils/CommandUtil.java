package javaTasks.tasks.library.command.utils;

import javaTasks.tasks.library.author.Author;
import javaTasks.tasks.library.controller.View;
import javaTasks.tasks.library.exceptions.AuthorRepositoryException;
import javaTasks.tasks.library.models.Book;
import javaTasks.tasks.library.storage.Repository;

import java.util.Optional;

public class CommandUtil {

    private final View view;
    private final Repository<Author> authorRepository;

    protected CommandUtil(View view, Repository<Author> authorRepository) {
        this.view = view;
        this.authorRepository = authorRepository;
    }

    protected Book getBook() {
        String name;
        int countPages;

        view.write("Добавление книги. \nВеедите название книги: ");
        name = view.read();
        view.write("Введите количество страниц (число) : ");
        countPages = view.readInt();
        view.write("Введите автора книги: ");
        long authorId = Optional.ofNullable(Author
                        .selectAnAuthor(view, authorRepository))
                .stream()
                .findFirst()
                .orElseThrow(() -> new AuthorRepositoryException("Error when selecting an author!"))
                .getId();

        return new Book(name, countPages, authorId);
    }
}
