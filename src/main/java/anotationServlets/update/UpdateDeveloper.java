package anotationServlets.update;

import anotationServlets.managers.DeveloperManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/updateDeveloper")
public class UpdateDeveloper extends HttpServlet {
    private DeveloperManager developerManager = new DeveloperManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        developerManager.developerReadAllGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        developerManager.developerUpdatePost(req, resp);
    }
}
