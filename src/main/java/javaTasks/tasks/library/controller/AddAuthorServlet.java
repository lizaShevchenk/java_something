package javaTasks.tasks.library.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import javaTasks.tasks.library.author.Author;
import javaTasks.tasks.library.config.DatabaseConnectionManager;
import javaTasks.tasks.library.config.PropertyConfig;
import javaTasks.tasks.library.storage.AuthorRepository;

import java.io.IOException;
import java.util.List;


@WebServlet(urlPatterns = "/addAuthor")
public class AddAuthorServlet extends HttpServlet {

    private final AuthorRepository authorRepository;

    public AddAuthorServlet() {
        DatabaseConnectionManager databaseConnectionManager = new DatabaseConnectionManager(new PropertyConfig());
        this.authorRepository = new AuthorRepository(databaseConnectionManager);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Author> authors = authorRepository.get();
        req.setAttribute("authors", authors);

        req.getRequestDispatcher("/html/addAuthor.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("email");

        if (authorRepository.checkIfAuthorExistByEmail(email)) {
            req.getSession().setAttribute("errorMessage", "Author with this email already exists! Please use a different email.");
            resp.sendRedirect(req.getContextPath() + "/addAuthor");
            return;
        }
        Author author = new Author(firstName, lastName, email);

        authorRepository.add(author);

        req.getSession().setAttribute("message", "Author added successfully!");
        resp.sendRedirect(req.getContextPath() + "/index");
    }
}
