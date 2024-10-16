package tasks.task2;

public class Journal extends Publication {
    private int number;
    private int publicationYear;

    Journal(String name, int countPages, int number, int publicationYear) {
        super(name, countPages);
        this.number = number;
        this.publicationYear = publicationYear;
    }

    @Override
    public String print() {
        return String.format("Journal{name=%s,countPages=%s,number=%s,year=%s}", getName(), getCountPages(), number, publicationYear);
    }
}
