package javaTasks;

import javaTasks.tasks.library.author.Author;
import javaTasks.tasks.library.command.*;
import javaTasks.tasks.library.controller.Console;
import javaTasks.tasks.library.controller.View;
import javaTasks.tasks.library.models.Publication;
import javaTasks.tasks.library.storage.InMemoryAuthorStorage;
import javaTasks.tasks.library.storage.ListRepository;
import javaTasks.tasks.library.storage.Repository;

import java.util.ArrayList;
import java.util.List;

public class Dispatcher {
    public static void initialise() {
        View view = new Console();
        Repository<Publication> repository = new ListRepository();
        Repository<Author> authorRepository = new InMemoryAuthorStorage();
//        Repository<Author> authorRepository = Author.addAuthors(23);

        List<Command> commands = new ArrayList<>();
        commands.add(new Exit(view));
        commands.add(new Help(view));
        commands.add(new ShowStorage(view, repository));
        commands.add(new ShowAuthors(view, authorRepository));
        commands.add(new AddAuthor(view, authorRepository));
        commands.add(new AddBook(view, repository, authorRepository));
        commands.add(new AddJournal(view, repository));
        commands.add(new DeleteBook(view, authorRepository, repository));

        execute(view, commands);
    }

    public static void execute(View view, List<Command> commandList) {
        commandList.get(1).handle();
        do {
            String enteredCommand = view.read();
            boolean handled = false;
            for (Command command : commandList) {
                if (command.canHandle(enteredCommand)) {
                    command.handle();
                    view.write(enteredCommand + " command done.");
                    handled = true;
                    break;
                }
            }
            if (!handled) {
                view.write("Something went wrong. Please enter new command.");
            }
        } while (true);
    }
}
