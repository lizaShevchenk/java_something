import tasks.library.Library;
import tasks.library.models.Book;
import tasks.library.models.Journal;
import tasks.library.models.Publication;
import tasks.library.storage.Storage;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Publication[] publications = new Publication[4];
        publications[0] = new Journal("journal", 100, 1, 2000);
        publications[1] = new Book("book", 150, "Blabla");
        publications[2] = new Publication(null, 20);
        publications[3] = null;

        Storage storage = new Storage(2);
        Storage storage1 = new Storage(4);
        Arrays.stream(publications).forEach(storage::addPublication);
        new Library().printPublications(publications);

        for (int i = 0; i < 4; i++) {//checking storage increase, should increase 4 times: 4->6->9->13->19
            Arrays.stream(publications).forEach(storage1::addPublication);
        }
        System.out.printf("Storage number of elements = %s, size = %s%n", storage1.getNumberOfElements(), storage1.getStorageSize());
    }
}
