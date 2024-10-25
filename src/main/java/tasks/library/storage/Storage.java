package tasks.library.storage;

import tasks.library.models.Publication;

import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Storage implements Store {
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
    //

    @Override
    public void addPublication(Publication publication) {
        if (publications.length == index) {
            increaseSize();
        }
        publications[index] = publication;
        index++;
    }

    private void increaseSize() {
        System.out.println("\nInfo: Increase storage size");
        int newSize = size + (size >> oneElementSize);
        this.publications = Arrays.copyOf(publications, newSize);
        this.size = newSize;
    }

    @Override
    public void deletePublication(Publication publication) {
        if (publication == null) {
            System.out.println("\nНельзя удалить пустую публикацию. Удаление не произошло.");
            return;
        }
        if (publications == null || publications.length == 0) {
            System.out.println("\nНельзя удалить публикацию с уже/еще пустого хранилища. Удаление не произошло.");
            return;
        }
        if (Arrays.stream(publications).noneMatch(Objects::nonNull)) {
            System.out.println("\nВ хранилище нет публикаций. Нечего удалять. Удаление не произошло.");
            return;
        }
        AtomicInteger index = new AtomicInteger(0);
        int newSize = size - oneElementSize;
        AtomicBoolean isDeleted = new AtomicBoolean(false);
        Arrays.stream(publications).forEach(p -> {
            if (p != null && p.equals(publication)) {
                System.arraycopy(publications, index.get() + 1, publications, index.get(), newSize - index.getAndIncrement());
                publications[newSize] = null;
                System.out.printf("\nПроизошло удаление публикации %s с хранилища.", publication.print());
                isDeleted.set(true);
                return;
            }
            index.getAndIncrement();
        });
        if (!isDeleted.get()) {
            System.out.println("\nНет такой публикации в хранилище. Нет необходимости в удалении.");
        }
    }

    @Override
    public void deletePublicationByIndex(int index) {
        if (Arrays.stream(publications).noneMatch(Objects::nonNull)) {
            System.out.println("\nВ хранилище нет публикаций. Нечего удалять. Удаление не произошло.");
            return;
        }
        if (!(index <= Arrays.stream(publications).filter(Objects::nonNull).count() - 1 && index >= 0)) {
            System.out.println("В хранилище нет публикации с индексом " + index);
            return;
        }
        System.arraycopy(publications, index + 1, publications, index, size - oneElementSize - index);
    }

    @Override
    public Publication getPublicationByIndex(int index) {
        if (index >= 0 && index < publications.length) {
            return publications[index];
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("\nPublications:");
        for (Publication publication : publications) {
            if (publication != null) {
                stringBuilder.append("\n").append(publication.print());
            }
        }
        return stringBuilder.toString();
    }
}
