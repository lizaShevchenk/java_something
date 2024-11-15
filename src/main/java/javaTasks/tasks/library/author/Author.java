package javaTasks.tasks.library.author;

import javaTasks.tasks.library.command.AddAuthor;
import javaTasks.tasks.library.controller.View;
import javaTasks.tasks.library.pagination.Pagination;
import javaTasks.tasks.library.storage.InMemoryAuthorStorage;
import javaTasks.tasks.library.storage.Repository;

import java.util.Objects;

public class Author {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    final static int numberOfAuthorsPerPage = 10;

    public Author() {}

    public Author(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        if (authorList.get().isEmpty()) {
            new AddAuthor(view, authorList).handle();
            selectAnAuthor(view, authorList);
        }
        Author author = new Author();
        int pageIndex = 1;

        while (Objects.equals(author, null) || Objects.equals(author, new Author())) {
            view.write("Выберите автора из списка за ИД или нажмите 'next' или 'prev' для пролистывания:");
            pageIndex = Pagination.printEntities(pageIndex, authorList, numberOfAuthorsPerPage, view);

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

    public static Repository<Author> addAuthors(int count, Repository<Author> authorRepository) {
        if (count < 1) {
            throw new IllegalArgumentException("Unable to add " + count + " authors.");
        }
        int size = authorRepository.get().size();
        for (int j = size; j < size + count; j++) {
            authorRepository.add(new Author("name " + j, "last", "email" + j + "@alksdalk.com"));
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
