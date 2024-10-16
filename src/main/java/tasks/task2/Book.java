package tasks.task2;

public class Book extends Publication {
    private String author;

    Book(String name, int countPages, String author) {
        super(name, countPages);
        this.author = author;
    }

    @Override
    public String print() {
        return String.format("Book{name=%s,countPages=%s,author=%s}", getName(), getCountPages(), author);
    }
}
