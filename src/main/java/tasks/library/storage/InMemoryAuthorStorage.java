package tasks.library.storage;

import tasks.library.author.Author;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InMemoryAuthorStorage {
    List<Author> authorList;
    int numberOfAuthorsPerPage = 10;

    public InMemoryAuthorStorage() {
        this.authorList = new ArrayList<>();
    }

    public Author findById(long id) {
        if (authorList == null || authorList.isEmpty()) {
            System.out.println("В хранилище пусто.");
        }
        if (id < 0) {
            System.out.println("Идентификатор не может быть отрицательным.");
        }
        return authorList.stream().filter(author -> author.getId() == id).findFirst().orElse(null);
    }

    public Author findByEmail(String email) {
        if (authorList == null || authorList.isEmpty()) {
            System.out.println("В хранилище пусто.");
        }
        return authorList.stream().filter(author -> author.getEmail().equals(email)).findFirst().orElse(null);
    }

    public void addAuthor(Author author) {
        if (authorList.contains(author) || authorAlreadyExistCheck(author)) {
            System.out.println("Этот автор уже есть в системе.");
            return;
        }
        authorList.add(author);
    }

    public void deleteAuthor(String email) {
        authorList.stream().filter(author -> author.getEmail().equals(email)).findFirst()
                .ifPresentOrElse(author -> {
                    System.out.printf("Происходит удаление автора с почтовым адресом '%s'", author.getEmail());
                    authorList.remove(author);
                }, () -> System.out.println("Автора с таким почтовым адресом нет. Удаление не произошло."));
    }

    public void editAuthor(Author author) {
        if (authorAlreadyExistCheck(author)) {
            Author authorToEdit = findById(author.getId()) == null ? findByEmail(author.getEmail()) : findById(author.getId());
            authorList.set(authorList.indexOf(authorToEdit), author);
            System.out.printf("Внесли изменения для автора %s, \nИзмененные данные - %s.", authorToEdit, author);
            return;
        }
        System.out.printf("Нет автора с ИД %s и/или постовым адресом %s", author.getId(), author.getEmail());
    }

    private boolean authorAlreadyExistCheck(Author author) {
        if (authorList.stream().anyMatch(a -> a.getId() == author.getId())) {
            System.out.println("Уже существует автор с таким ИД.");
            return true;
        }
        if (authorList.stream().anyMatch(a -> a.getEmail().equals(author.getEmail()))) {
            System.out.println("Уже существует автор с таким почтовым адресом.");
            return true;
        }
        return false;
    }

    public Author selectAnAuthor(Scanner scanner) {
        if (authorList.isEmpty()) {
            System.out.println("В хранилище пусто. Нужно добавить авторов.");
            return null;
        }
        int pageIndex = 1;
        Author author = null;

        while (author == null) {
            System.out.println("Выберите автора из списка за ИД или нажмите 'next' или 'prev' для пролистывания:");
            showAuthors(pageIndex);
            scanner.nextLine();
            String value = scanner.nextLine();
            if (value.equalsIgnoreCase("next")) {
                pageIndex++;
                showAuthors(pageIndex);
            }
            if (value.equalsIgnoreCase("prev")) {
                pageIndex--;
                showAuthors(pageIndex);
            }
            System.out.println("\npageIndex = " + pageIndex);
            try {
                author = findById(Long.parseLong(value));
            } catch (Exception e) {
                System.out.println("Error: " + e);
            }
        }

        return author;
    }

    public void showAuthors(int pageIndex) {
        double pagesCount = (double) authorList.size() / numberOfAuthorsPerPage;
        if (pagesCount < 0) {
            System.out.printf("Это весь список авторов. Пагинация недоступна. \n%s", authorList);
            return;
        }
        if (pageIndex > pagesCount && (pageIndex - pagesCount) < 1) {
            System.out.printf("Последняя страница хранилища:\n%s", authorList.subList((pageIndex - 1) * numberOfAuthorsPerPage, authorList.size() - 1));
            return;
        }
        if (pageIndex < 1) {
            System.out.printf("1 страница:\n%s", authorList.subList(0, numberOfAuthorsPerPage - 1));
        }
        if (pageIndex < pagesCount) {
            System.out.printf("%s страница:\n%s", pageIndex, authorList.subList((pageIndex - 1) * numberOfAuthorsPerPage, pageIndex * numberOfAuthorsPerPage - 1));
            return;
        }
        System.out.println("Need to investigate this error.");
    }

    @Override
    public String toString() {
        return authorList.stream().map(Author::toString).collect(Collectors.joining("\n"));
    }
}
