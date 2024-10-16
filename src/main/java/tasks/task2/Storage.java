package tasks.task2;

import java.util.Arrays;

public class Storage {
    private Publication[] publications;
    private int index = 0;

    Storage() {
        this.publications = new Publication[16];
    }

    Storage(int size) {
        this.publications = new Publication[size];
    }

    public void addPublication(Publication publication) {
        if (publications.length == index) {
            increaseSize();
        }
        publications[index] = publication;
        index++;
    }

    private void increaseSize() {
        System.out.println("Info: Increase storage size");
        this.publications = Arrays.copyOf(publications, index + 1);
    }
}
