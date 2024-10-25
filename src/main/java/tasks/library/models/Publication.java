package tasks.library.models;

import java.util.Objects;

public class Publication {
    private String name;
    private int countPages;

    public Publication(String name, int countPages) {
        this.name = name;
        this.countPages = countPages;
    }

    protected String getName() {
        return name;
    }

    protected int getCountPages() {
        return countPages;
    }

    public String print() {
        return String.format("Publication{name=%s,countPages=%s}", name, countPages);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Publication that = (Publication) o;
        return countPages == that.countPages && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, countPages);
    }
}
