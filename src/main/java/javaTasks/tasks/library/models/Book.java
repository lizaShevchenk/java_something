package javaTasks.tasks.library.models;

import java.util.Objects;

public class Book extends Publication {
    private long authorId;

    public Book(String name, int countPages, long authorId) {
        super(name, countPages);
        this.authorId = authorId;
    }

    @Override
    public String print() {
        return String.format("Book{name=%s,countPages=%s,author=%s}", getName(), getCountPages(), authorId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Book book = (Book) o;
        return authorId == book.authorId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), authorId);
    }

}
