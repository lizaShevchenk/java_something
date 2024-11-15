package javaTasks.tasks.library.storage;

import javaTasks.tasks.library.models.Publication;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ListRepository implements Repository<Publication> {
    private List<Publication> publications;
    private final int defaultSize = 16;

    public ListRepository() {
        this.publications = new ArrayList<>(defaultSize);
    }

    public ListRepository(int size) {
        this.publications = new ArrayList<>(size);
    }

    @Override
    public void add(Publication publication) {
        publications.add(publication);
    }

    @Override
    public void delete(Publication publication) {
        if (publication == null) {
            System.out.println("\nНельзя удалить пустую публикацию. Удаление не произошло.");
            return;
        }
        if (publications == null || publications.isEmpty()) {
            System.out.println("\nНельзя удалить публикацию с уже/еще пустого хранилища. Удаление не произошло.");
            return;
        }
        if (publications.stream().noneMatch(Objects::nonNull)) {
            System.out.println("\nВ хранилище нет публикаций. Нечего удалять. Удаление не произошло.");
            return;
        }
        if (publications.remove(publication)) System.out.printf("\nПроизошло удаление публикации %s с хранилища.", publication.print());
        else System.out.println("\nНет такой публикации в хранилище. Нет необходимости в удалении.");
    }

    @Override
    public void deleteByIndex(int index) {
        if (publications.stream().noneMatch(Objects::nonNull)) {
            System.out.println("\nВ хранилище нет публикаций. Нечего удалять. Удаление не произошло.");
            return;
        }
        if (!(index <= publications.stream().filter(Objects::nonNull).count() - 1 && index >= 0)) {
            System.out.println("В хранилище нет публикации с индексом " + index);
            return;
        }
        publications.remove(index);
    }

    @Override
    public Publication getByIndex(int index) {
        if (index >= 0 && index < publications.size()) {
            return publications.get(index);
        }
        return null;
    }

    @Override
    public List<Publication> get() {
        return publications;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("\nPublication list:");
        for (Publication publication : publications) {
            if (publication != null) {
                stringBuilder.append("\n").append(publication.print());
            }
        }
        return stringBuilder.toString();
    }
}
