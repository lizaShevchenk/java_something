package javaTasks.tasks.library.author;

import javaTasks.tasks.library.command.AddAuthor;
import javaTasks.tasks.library.controller.View;
import javaTasks.tasks.library.pagination.Pagination;
import javaTasks.tasks.library.storage.AuthorRepository;
import javaTasks.tasks.library.storage.Repository;

import java.util.Objects;
import java.util.UUID;

public class Author {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    final static int numberOfAuthorsPerPage = 10;

    public Author(Integer id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Author() {}

    public Author(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public static Author selectAnAuthor(View view, AuthorRepository authorList) {
        if (authorList.get().isEmpty()) {
            view.write("Author list is empty! PLease add an author..");
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
                if (authorList.get().stream().anyMatch(a -> a.getId() == Integer.parseInt(value))) author = authorList.getByIndex(Integer.parseInt(value));
                else {
                    view.write(String.format("There is no author with id %s! Please try to select an author again..", value));
                    selectAnAuthor(view, authorList);
                }
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
            String randomUUID = UUID.randomUUID().toString().substring(0, 5);
            authorRepository.add(new Author("name", "last" + randomUUID, "email" + randomUUID + "@email.com"));
        }
        return authorRepository;
    }

    @Override
    public String toString() {
        return String.format("Author: Id - %s, first name - %s, last name - %s, email - %s.", id, firstName, lastName, email);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return Objects.equals(id, author.id) && Objects.equals(firstName, author.firstName) && Objects.equals(lastName, author.lastName) && Objects.equals(email, author.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email);
    }

}
