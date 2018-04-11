package anotationServlets.create;

import anotationServlets.managers.DeveloperManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/createDev")
public class CreateDeveloper extends HttpServlet {
    private DeveloperManager developerManager = new DeveloperManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        developerManager.developerCreateGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        developerManager.developerCreatePost(req, resp);
    }
}
