package javaTasks.tasks.library.command;

import javaTasks.tasks.library.author.Author;
import javaTasks.tasks.library.command.utils.CommandUtil;
import javaTasks.tasks.library.controller.View;
import javaTasks.tasks.library.exceptions.AuthorRepositoryException;
import javaTasks.tasks.library.storage.Repository;

import java.util.concurrent.atomic.AtomicInteger;

public class AddAuthor extends CommandUtil implements Command {

    private final View view;
    private final Repository<Author> authorRepository;

    public AddAuthor(View view, Repository<Author> authorRepository) {
        super(view, authorRepository);
        this.view = view;
        this.authorRepository = authorRepository;
    }

    @Override
    public Boolean canHandle(String command) {
        return command.equalsIgnoreCase(CommandNames.ADD_AUTHOR.getCode());
    }

    @Override
    public void handle() {
        view.write("Adding authors.. Want to create author ('c' command) or multiple authors automatically ('m' command)? Please enter command: ");
        String command;
         while (true) {
             command = view.read();
             if (command.equalsIgnoreCase("c")) {
                 AtomicInteger atomicInteger = new AtomicInteger();
                 addAuthor(atomicInteger);
                 break;
             }
             if (command.equalsIgnoreCase("m")) {
                 view.write("Enter count of authors to create: ");
                 int count = view.readInt();
//                 Author.addAuthors(count, authorRepository);
                 break;
             }
             view.write("Incorrect command, please enter again ('c' or 'm'):");
         }


    }

    private void addAuthor(AtomicInteger atomicInteger) {
        Author author = getAuthor();
        if (authorRepository.get().stream().anyMatch(a -> a.getEmail().equals(author.getEmail()))) {
            view.write("Author with this email already exist. PLease try again.");
            while (atomicInteger.getAndIncrement() < 3) addAuthor(atomicInteger);
        }

        try {
            authorRepository.add(author);
        } catch (AuthorRepositoryException ex) {
            view.write("Something wrong when adding journal. Error: " + ex.getMessage().replace("\n", "\t"));
            while (atomicInteger.getAndIncrement() < 3) {
                view.write("Please try again.");
                handle();
            }
        }
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
}
