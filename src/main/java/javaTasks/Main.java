package javaTasks;

import javaTasks.tasks.library.config.DatabaseConnectionManager;
import javaTasks.tasks.library.config.PropertyConfig;
import javaTasks.tasks.library.models.Book;
import javaTasks.tasks.library.storage.AuthorRepository;
import javaTasks.tasks.library.storage.BookRepository;
import javaTasks.tasks.library.storage.JournalRepository;
import javaTasks.tasks.library.storage.Repository;


public class Main {

    public static void main(String[] args) {
//        Dispatcher.initialise(); previous taskΩ

//        String filePath = "/Users/shevchenko.fm/IdeaProjects/java_something/src/main/resources/sql/init.sql";
//        PropertyConfig propertyConfig = new PropertyConfig();
//        DatabaseConnectionManager connectionManager = new DatabaseConnectionManager(propertyConfig);
//
//        //JDBC init
//        Repository<Book> bookRepository = new BookRepository(connectionManager);
//        AuthorRepository authorRepository = new AuthorRepository(connectionManager);
//        JournalRepository journalRepository = new JournalRepository(connectionManager);
//
//        Dispatcher.initialise(authorRepository, bookRepository, journalRepository);

    }

    public static void initLibrary() {
        DatabaseConnectionManager connectionManager = new DatabaseConnectionManager(new PropertyConfig());

        //JDBC init
//        Repository<Book> bookRepository = new BookRepository(connectionManager);
        BookRepository bookRepository = new BookRepository();
        AuthorRepository authorRepository = new AuthorRepository();
        JournalRepository journalRepository = new JournalRepository();

        Dispatcher.initialise(authorRepository, bookRepository, journalRepository);
    }
}
