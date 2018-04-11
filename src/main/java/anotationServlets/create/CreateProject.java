package anotationServlets.create;

import anotationServlets.managers.ProjectManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/createProject")
public class CreateProject extends HttpServlet {
    private ProjectManager projectManager = new ProjectManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        projectManager.createProjectGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        projectManager.createProjectPost(req, resp);
    }
}