package tasks.library.storage;

import tasks.library.models.Publication;

public interface Store {

    void addPublication(Publication publication);

    void deletePublication(Publication publication);

    void deletePublicationByIndex(int index);

    Publication getPublicationByIndex(int index);
}
