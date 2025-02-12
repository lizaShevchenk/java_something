package javaTasks.tasks.library.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import javaTasks.Main;

import java.io.IOException;


@WebServlet(urlPatterns = "/findBookForm")
public class FindBookFormServlet extends HttpServlet {

    @Override
    public void init() {
        Main.initLibrary();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/html/findBookForm.jsp").forward(req, resp);
    }
}
