package anotationServlets.update;

import anotationServlets.managers.ProjectManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/updateProject")
public class UpdateProject extends HttpServlet {
    private ProjectManager projectManager = new ProjectManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        projectManager.projectAllGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        projectManager.projectUpdatePost(req, resp);
    }
}
