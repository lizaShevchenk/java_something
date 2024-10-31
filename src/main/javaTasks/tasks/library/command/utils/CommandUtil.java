package tasks.library.command.utils;

import tasks.library.author.Author;
import tasks.library.controller.View;
import tasks.library.models.Book;
import tasks.library.storage.Repository;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

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
        AtomicLong authorId = new AtomicLong(0);

        view.write("Добавление книги. \nВеедите название книги: ");
        name = view.read();
        view.write("Введите количество страниц (число) : ");
        countPages = view.readInt();
        view.write("Введите автора книги: ");
        Optional.ofNullable(Author.selectAnAuthor(view, authorRepository)).ifPresent(author -> authorId.set(author.getId()));

        return new Book(name, countPages, authorId.get());
    }
}
