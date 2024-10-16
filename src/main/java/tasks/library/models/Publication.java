package tasks.library.models;

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
        return String.format("name=%s,countPages=%s", name, countPages);
    }
}
