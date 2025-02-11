package javaTasks.tasks.library.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import javaTasks.Main;

import java.io.IOException;


@WebServlet(urlPatterns = "/index")
public class IndexServlet extends HttpServlet {

    @Override
    public void init() {
        Main.initLibrary();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/html/index.jsp").forward(req, resp);
    }
}
