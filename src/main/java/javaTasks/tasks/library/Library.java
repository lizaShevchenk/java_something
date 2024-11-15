package javaTasks.tasks.library;

import javaTasks.tasks.library.models.Publication;

import java.util.Arrays;
import java.util.Objects;

public class Library {
    public void printPublications(Publication[] publications) {
        if (Objects.isNull(publications) || publications.length == 0) {
            System.out.println("Нет публикаций для печати");
            return;
        }
        Arrays.stream(publications).filter(Objects::nonNull).forEach(publication -> System.out.println(publication.print()));
    }
}
