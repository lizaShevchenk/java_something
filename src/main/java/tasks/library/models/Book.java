package tasks.library.models;

public class Book extends Publication {
    private String author;

    public Book(String name, int countPages, String author) {
        super(name, countPages);
        this.author = author;
    }

    @Override
    public String print() {
        return String.format("Book{name=%s,countPages=%s,author=%s}", getName(), getCountPages(), author);
    }
}
