package tasks.library.author;

import tasks.library.controller.View;
import tasks.library.pagination.Pagination;
import tasks.library.storage.InMemoryAuthorStorage;
import tasks.library.storage.Repository;

import java.util.Objects;

public class Author {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    final static int numberOfAuthorsPerPage = 10;

    public Author() {}

    public Author(long id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public static Author selectAnAuthor(View view, Repository<Author> authorList) {
        if (((InMemoryAuthorStorage) authorList).getAll().isEmpty()) {
            System.out.println("В хранилище пусто. Нужно добавить авторов.");
            return null;
        }
        Author author = new Author();
        int pageIndex = 1;

        view.write("Выберите автора из списка за ИД или нажмите 'next' или 'prev' для пролистывания:");
        pageIndex = Pagination.printEntities(pageIndex, authorList, numberOfAuthorsPerPage, view);

        while (author.equals(new Author())) {
            String value = view.read();
            if (value.equalsIgnoreCase("next")) {
                pageIndex++;
                pageIndex = Pagination.printEntities(pageIndex, authorList, numberOfAuthorsPerPage, view);
                continue;
            }
            if (value.equalsIgnoreCase("prev")) {
                pageIndex--;
                pageIndex = Pagination.printEntities(pageIndex, authorList, numberOfAuthorsPerPage, view);
                continue;
            }
            view.write("\npageIndex = " + pageIndex);
            try {
                author = ((InMemoryAuthorStorage) authorList).findById(Long.parseLong(value));
            } catch (Exception e) {
                view.write("Error: " + e);
            }
        }

        return author;
    }

    public static Repository<Author> addAuthors(int count) {
        if (count < 1) {
            throw new IllegalArgumentException("Unable to add " + count + " authors.");
        }
        Repository<Author> authorRepository = new InMemoryAuthorStorage();
        for (int j = 1; j <= count; j++) {
            authorRepository.add(new Author(j, "name", "last", "email" + j + "@alksdalk.com"));
        }
        return authorRepository;
    }

    @Override
    public String toString() {
        return String.format("Author: Id - %s, first name - %s, last name - %s, email - %s.", id, firstName, lastName, email);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return id == author.id && Objects.equals(firstName, author.firstName) && Objects.equals(lastName, author.lastName) && Objects.equals(email, author.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email);
    }

}
