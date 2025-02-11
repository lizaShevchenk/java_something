package javaTasks.tasks.library.models;

import java.util.Objects;

public class Journal extends Publication {
    private int number;
    private int publicationYear;
    private int id;

    public int getNumber() {
        return number;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public int getId() {
        return id;
    }

    public Journal(String name, int countPages, int number, int publicationYear) {
        super(name, countPages);
        this.number = number;
        this.publicationYear = publicationYear;
    }

    public Journal(int id, String name, int countPages, int number, int publicationYear) {
        super(name, countPages);
        this.number = number;
        this.publicationYear = publicationYear;
        this.id = id;
    }

    @Override
    public String print() {
        return String.format("Journal{id=%d, name=%s, countPages=%s, number=%s, year=%s}", getId(), getName(), getCountPages(), number, publicationYear);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        Journal journal = (Journal) object;
        return number == journal.number && publicationYear == journal.publicationYear && id == journal.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), number, publicationYear, id);
    }
}
