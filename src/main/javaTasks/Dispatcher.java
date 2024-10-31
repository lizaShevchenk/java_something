import tasks.library.author.Author;
import tasks.library.command.*;
import tasks.library.controller.Console;
import tasks.library.controller.View;
import tasks.library.models.Book;
import tasks.library.models.Publication;
import tasks.library.storage.InMemoryAuthorStorage;
import tasks.library.storage.ListRepository;
import tasks.library.storage.Repository;

import java.util.ArrayList;
import java.util.List;

public class Dispatcher {
    public static void initialise() {
        View view = new Console();
        Repository<Publication> repository = new ListRepository();
        Repository<Author> authorRepository = Author.addAuthors(23);

        List<Command> commands = new ArrayList<>();
        commands.add(new Exit(view));
        commands.add(new Help(view));
        commands.add(new ShowStorage<>(view, repository));
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
