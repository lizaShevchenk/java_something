package tasks.library.storage;

import tasks.library.models.Publication;

import java.util.Arrays;

public class Storage {
    private Publication[] publications;
    private final int defaultSize = 16;
    private final int oneElementSize = 1;
    private int index = 0;
    private int size;

    public Storage() {
        this.publications = new Publication[defaultSize];
        this.size = defaultSize;
    }

    public Storage(int size) {
        this.publications = new Publication[size];
        this.size = size;
    }

    //ToDo: these are for the logs, can be removed
    public int getStorageSize() {
        return size;
    }

    public int getNumberOfElements() {
        return index;
    }
    //

    public void addPublication(Publication publication) {
        if (publications.length == index) {
            increaseSize();
        }
        publications[index] = publication;
        index++;
    }

    private void increaseSize() {
        System.out.println("Info: Increase storage size");
        int newSize = size + (size >> oneElementSize);
        this.publications = Arrays.copyOf(publications, newSize);
        this.size = newSize;
    }
}
