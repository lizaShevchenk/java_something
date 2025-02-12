package javaTasks.tasks.library.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import javaTasks.tasks.library.author.Author;
import javaTasks.tasks.library.config.DatabaseConnectionManager;
import javaTasks.tasks.library.config.PropertyConfig;
import javaTasks.tasks.library.models.Book;
import javaTasks.tasks.library.storage.AuthorRepository;
import javaTasks.tasks.library.storage.BookRepository;

import java.io.IOException;
import java.util.List;


@WebServlet(urlPatterns = "/showAuthors")
public class ShowAuthorsServlet extends HttpServlet {

    private final AuthorRepository authorRepository;

    public ShowAuthorsServlet() {
        DatabaseConnectionManager databaseConnectionManager = new DatabaseConnectionManager(new PropertyConfig());
        this.authorRepository = new AuthorRepository(databaseConnectionManager);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Author> authors = authorRepository.get();
        req.setAttribute("authors", authors);

        req.getRequestDispatcher("/html/showAuthors.jsp").forward(req, resp);
    }
}
