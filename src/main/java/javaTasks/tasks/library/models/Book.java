package javaTasks.tasks.library.models;

import javaTasks.tasks.library.author.Author;

import java.util.Objects;

public class Book extends Publication {
    private Author author;
    private int id;

    public Book() {}

    public Book(String name, int countPages, Author authorId) {
        super(name, countPages);
        this.author = authorId;
    }

    public Author getAuthor() {
        return author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Book(String name, int countPages, Author author, int id) {
        super(name, countPages);
        this.author = author;
        this.id = id;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public String print() {
        return String.format("Book{name=%s, countPages=%s, author=%s}", getName(), getCountPages(), author.toString());
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        Book book = (Book) object;
        return author == book.author && id == book.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), author, id);
    }
}
