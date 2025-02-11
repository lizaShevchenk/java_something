package javaTasks.tasks.library.models;

import java.util.Objects;

public class Book extends Publication {
    private long authorId;
    private int id;

    public Book(String name, int countPages, long authorId) {
        super(name, countPages);
        this.authorId = authorId;
    }

    public long getAuthorId() {
        return authorId;
    }

    public int getId() {
        return id;
    }

    public Book(String name, int countPages, long authorId, int id) {
        super(name, countPages);
        this.authorId = authorId;
        this.id = id;
    }

    @Override
    public String print() {
        return String.format("Book{name=%s, countPages=%s, author=%s}", getName(), getCountPages(), authorId);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        Book book = (Book) object;
        return authorId == book.authorId && id == book.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), authorId, id);
    }
}
