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


@WebServlet(urlPatterns = "/addBook")
public class AddBookServlet extends HttpServlet {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public AddBookServlet() {
        DatabaseConnectionManager databaseConnectionManager = new DatabaseConnectionManager(new PropertyConfig());
        this.bookRepository = new BookRepository(databaseConnectionManager);
        this.authorRepository = new AuthorRepository(databaseConnectionManager);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Author> authors = authorRepository.get();
        req.setAttribute("authors", authors);

        req.getRequestDispatcher("/html/addBook.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        int countPages = Integer.parseInt(req.getParameter("countPages"));
        long authorId = Long.parseLong(req.getParameter("authorId"));

        Book book = new Book(name, countPages, authorId);

        bookRepository.add(book);

        req.getSession().setAttribute("message", "Book added successfully!");
        resp.sendRedirect(req.getContextPath() + "/index");
    }
}
