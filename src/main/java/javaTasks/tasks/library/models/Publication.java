package javaTasks.tasks.library.models;

import java.util.Objects;

public class Publication {
    private String name;
    private int countPages;

    public Publication() {}

    public Publication(String name, int countPages) {
        this.name = name;
        this.countPages = countPages;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountPages(int countPages) {
        this.countPages = countPages;
    }

    public String getName() {
        return name;
    }

    public int getCountPages() {
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
