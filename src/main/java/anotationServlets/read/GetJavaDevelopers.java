package anotationServlets.read;

import anotationServlets.managers.DeveloperManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/getJavaDevelopers")
public class GetJavaDevelopers extends HttpServlet {
    private DeveloperManager developerManager = new DeveloperManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        developerManager.developersReadJavaGet(req, resp);
    }
}
