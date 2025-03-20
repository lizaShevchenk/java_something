package javaTasks.tasks.library.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import javaTasks.tasks.library.config.DatabaseConnectionManager;
import javaTasks.tasks.library.config.PropertyConfig;
import javaTasks.tasks.library.models.Book;
import javaTasks.tasks.library.storage.BookRepository;

import java.io.IOException;
import java.util.List;


@WebServlet(urlPatterns = "/showBooks")
public class ShowBooksServlet extends HttpServlet {

    private final BookRepository bookRepository;

    public ShowBooksServlet() {
//        DatabaseConnectionManager databaseConnectionManager = new DatabaseConnectionManager(new PropertyConfig());
//        this.bookRepository = new BookRepository(databaseConnectionManager);
        this.bookRepository = new BookRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Book> books = bookRepository.get();
        req.setAttribute("books", books);

        req.getRequestDispatcher("/html/showBooks.jsp").forward(req, resp);
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
