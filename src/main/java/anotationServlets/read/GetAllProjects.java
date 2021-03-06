package anotationServlets.read;

import anotationServlets.managers.ProjectManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/getAllProjects")
public class GetAllProjects extends HttpServlet {
    private ProjectManager projectManager = new ProjectManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        projectManager.projectAllGet(req, resp);
    }
}


