package javaTasks.tasks.library.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import javaTasks.tasks.library.config.DatabaseConnectionManager;
import javaTasks.tasks.library.config.PropertyConfig;
import javaTasks.tasks.library.models.Journal;
import javaTasks.tasks.library.storage.JournalRepository;

import java.io.IOException;
import java.util.List;


@WebServlet(urlPatterns = "/addJournal")
public class AddJournalServlet extends HttpServlet {

    private final JournalRepository journalRepository;

    public AddJournalServlet() {
        DatabaseConnectionManager databaseConnectionManager = new DatabaseConnectionManager(new PropertyConfig());
        this.journalRepository = new JournalRepository(databaseConnectionManager);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Journal> journals = journalRepository.get();
        req.setAttribute("journals", journals);

        req.getRequestDispatcher("/html/addJournal.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String name = req.getParameter("name");
        int countPages = Integer.parseInt(req.getParameter("countPages"));
        int number = Integer.parseInt(req.getParameter("number"));
        int publicationYear = Integer.parseInt(req.getParameter("publicationYear"));

        if (!journalRepository.checkPublicationYearByPattern(publicationYear)) {
            req.setAttribute("errorMessage", "Please enter a valid publication year between 1900 and the current year.");
            req.getRequestDispatcher("/html/addJournal.jsp").forward(req, resp);
            return;
        }
        Journal journal = new Journal(name, countPages, number, publicationYear);

        journalRepository.add(journal);

        req.getSession().setAttribute("message", "Journal added successfully!");
        resp.sendRedirect(req.getContextPath() + "/index");
    }
}
