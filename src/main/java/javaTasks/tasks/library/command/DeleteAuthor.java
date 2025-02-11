package javaTasks.tasks.library.command;

import javaTasks.tasks.library.author.Author;
import javaTasks.tasks.library.controller.View;
import javaTasks.tasks.library.models.Book;
import javaTasks.tasks.library.storage.AuthorRepository;
import javaTasks.tasks.library.storage.Repository;

public class DeleteAuthor implements Command {

    private final View view;
    private final AuthorRepository repository;
    private final Repository<Book> bookRepository;

    public DeleteAuthor(View view, AuthorRepository repository, Repository<Book> bookRepository) {
        this.view = view;
        this.repository = repository;
        this.bookRepository = bookRepository;
    }

    @Override
    public Boolean canHandle(String command) {
        return command.equalsIgnoreCase(CommandNames.DELETE_AUTHOR.getCode());
    }

    @Override
    public void handle() {
        view.write("Author repository: " + repository.toString());
        view.write("Do you want to delete an author by index? Press 0. By email, press 1. To enter the author's details, press 2. To exit, press 3.");
        int methodToDelete = view.readInt();
        if (methodToDelete == 0) {
            view.write("Please enter the index of the author you wish to delete: ");
            int index = view.readInt();
            checkingAuthorUsage(index);
            repository.deleteByIndex(index);
        } else if (methodToDelete == 1) {
            view.write("Please enter the email of the author you wish to delete: ");
            String email = view.read();
            checkingAuthorUsage(email);
            repository.deleteByEmail(email);
        } else if (methodToDelete == 2) {
            Author author = getAuthor();
            checkingAuthorUsage((int) repository.getByValues(author).getId());
            repository.delete(author);
        } else if (methodToDelete == 3) new Exit(view).handle();
        else {
            view.write("You did not select the correct deletion method. Please try again.");
            handle();
        }
        view.write("Repository " + repository);
    }

    private Author getAuthor() {
        String firstName, lastName, email;
        view.write("Add author. \nPlease enter first name: ");
        firstName = view.read();
        view.write("\nPlease enter last name: ");
        lastName = view.read();
        view.write("\nPlease enter email: ");
        email = view.read();
        return new Author(firstName, lastName, email);
    }

    private void checkingAuthorUsage(int index) {
        if (isAuthorInUse(index)) {
            view.write("This author is used by. Select another author.");
            handle();
        }
    }

    private boolean isAuthorInUse(int index) {
        return bookRepository.get().stream().anyMatch(book -> book.getAuthorId() == index);
    }

    private void checkingAuthorUsage(String email) {
        repository.get().stream().filter(author -> author.getEmail().equals(email))
                .findFirst().ifPresentOrElse(
                        (author) -> checkingAuthorUsage((int) author.getId()),
                        () -> {
                            view.write(String.format("There is no author with '%s' email. Please try again.", email));
                            handle();
                        });
    }
}
