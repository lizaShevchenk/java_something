package javaTasks.tasks.library.command;

import javaTasks.tasks.library.author.Author;
import javaTasks.tasks.library.command.utils.CommandUtil;
import javaTasks.tasks.library.controller.View;
import javaTasks.tasks.library.storage.Repository;

public class AddAuthor extends CommandUtil implements Command {

    private final View view;
    private Repository<Author> authorRepository;

    public AddAuthor(View view, Repository<Author> authorRepository) {
        super(view, authorRepository);
        this.view = view;
        this.authorRepository = authorRepository;
    }

    @Override
    public Boolean canHandle(String command) {
        return command.equalsIgnoreCase(CommandNames.ADD_AUTHOR.getCommandName());
    }

    @Override
    public void handle() {
        view.write("Adding authors.. Want to create author ('c' command) or multiple authors automatically ('m' command)? Please enter command: ");
        String command;
         while (true) {
             command = view.read();
             if (command.equalsIgnoreCase("c")) {
                 addAuthor();
                 break;
             }
             if (command.equalsIgnoreCase("m")) {
                 view.write("Enter count of authors to create: ");
                 int count = view.readInt();
                 Author.addAuthors(count, authorRepository);
                 break;
             }
             view.write("Incorrect command, please enter again ('c' or 'm'):");
         }


    }

    private void addAuthor() {
        String firstName, lastName, email;
        view.write("Add author. \nPlease enter first name: ");
        firstName = view.read();
        view.write("\nPlease enter last name: ");
        lastName = view.read();
        view.write("\nPlease enter email: ");
        email = view.read();

        authorRepository.add(new Author(firstName, lastName, email));
    }
}
