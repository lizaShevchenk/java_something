package javaTasks.tasks.library.storage;

import javaTasks.tasks.library.author.Author;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryAuthorStorage implements Repository<Author> {
    private final List<Author> authorList;
    private int authorId = 0;

    public InMemoryAuthorStorage() {
        this.authorList = new ArrayList<>();
    }

    @Override
    public List<Author> get() {
        return authorList;
    }

    public Author findById(long id) {
        if (authorList.isEmpty()) {
            System.out.println("В хранилище пусто.");
        }
        if (id < 0) {
            System.out.println("Идентификатор не может быть отрицательным.");
        }
        return authorList.stream().filter(author -> author.getId() == id).findFirst().orElse(null);
    }

    public Author findByEmail(String email) {
        if (authorList.isEmpty()) {
            System.out.println("В хранилище пусто.");
        }
        return authorList.stream().filter(author -> author.getEmail().equals(email)).findFirst().orElse(null);
    }

    @Override
    public void add(Author author) {
        if (authorList.contains(author) || authorAlreadyExistCheck(author)) {
            System.out.println("Author already exist in system: " + author.toString());
            return;
        }
        author.setId(++authorId);
        authorList.add(author);
        System.out.println("Added author: " + author);
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
            System.out.println("Already exist author with same ID, author you wanted to add: " + author);
            return true;
        }
        if (authorList.stream().anyMatch(a -> a.getEmail().equals(author.getEmail()))) {
            System.out.println("Already exist author with the same email address, author you wanted to add: " + author);
            return true;
        }
        return false;
    }

    @Override
    public void delete(Author object) {
        authorList.remove(object);
    }

    @Override
    public void deleteByIndex(int index) {
        authorList.remove(index);
    }

    @Override
    public Author getByIndex(int index) {
        return authorList.get(index);
    }

    @Override
    public String toString() {
        return authorList.stream().map(Author::toString).collect(Collectors.joining("\n"));
    }
}
