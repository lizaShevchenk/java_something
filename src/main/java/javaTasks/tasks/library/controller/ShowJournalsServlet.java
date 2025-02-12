package javaTasks.tasks.library.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import javaTasks.tasks.library.author.Author;
import javaTasks.tasks.library.config.DatabaseConnectionManager;
import javaTasks.tasks.library.config.PropertyConfig;
import javaTasks.tasks.library.models.Journal;
import javaTasks.tasks.library.storage.JournalRepository;

import java.io.IOException;
import java.util.List;


@WebServlet(urlPatterns = "/showJournals")
public class ShowJournalsServlet extends HttpServlet {

    private final JournalRepository journalRepository;

    public ShowJournalsServlet() {
        DatabaseConnectionManager databaseConnectionManager = new DatabaseConnectionManager(new PropertyConfig());
        this.journalRepository = new JournalRepository(databaseConnectionManager);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Journal> journals = journalRepository.get();
        req.setAttribute("journals", journals);

        req.getRequestDispatcher("/html/showJournals.jsp").forward(req, resp);
    }
}
