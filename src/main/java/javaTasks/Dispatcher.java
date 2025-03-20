package javaTasks;

import javaTasks.tasks.library.author.Author;
import javaTasks.tasks.library.command.*;
import javaTasks.tasks.library.controller.Console;
import javaTasks.tasks.library.controller.View;
import javaTasks.tasks.library.models.Book;
import javaTasks.tasks.library.models.Journal;
import javaTasks.tasks.library.storage.*;

import java.util.ArrayList;
import java.util.List;

public class Dispatcher {
    public static void initialise(AuthorRepository authorRepository,
                                  Repository<Book> bookRepository,
                                  Repository<Journal> journalRepository) {
        View view = new Console();
//        Repository<Author> authorRepository = Author.addAuthors(23);

        List<Command> commands = new ArrayList<>();
//        commands.add(new Exit(view));
//        commands.add(new Help(view));
        commands.add(new ShowJournalRepository(view, journalRepository));
        commands.add(new ShowBookRepository(view, bookRepository));
        commands.add(new ShowAuthors(view, authorRepository));
        commands.add(new AddAuthor(view, authorRepository));
        commands.add(new AddBook(view, bookRepository, authorRepository));
        commands.add(new AddJournal(view, journalRepository));
//        commands.add(new DeleteJournal(view, journalRepository));
//        commands.add(new DeleteBook(view, authorRepository, bookRepository));
//        commands.add(new DeleteAuthor(view, authorRepository, bookRepository));

//        execute(view, commands);
    }

    public static void execute(View view, List<Command> commandList) {
        view.write("Please enter command code...");
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
