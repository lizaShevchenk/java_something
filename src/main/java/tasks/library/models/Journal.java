package tasks.library.models;

import java.util.Objects;

public class Journal extends Publication {
    private int number;
    private int publicationYear;

    public Journal(String name, int countPages, int number, int publicationYear) {
        super(name, countPages);
        this.number = number;
        this.publicationYear = publicationYear;
    }

    @Override
    public String print() {
        return String.format("Journal{name=%s,countPages=%s,number=%s,year=%s}", getName(), getCountPages(), number, publicationYear);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Journal journal = (Journal) o;
        return number == journal.number && publicationYear == journal.publicationYear;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), number, publicationYear);
    }

}
