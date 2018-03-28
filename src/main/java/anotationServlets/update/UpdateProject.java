package anotationServlets.update;

import dao.hibernate.HibernateProjectsDAOImpl;
import entities.Projects;
import hibernateFunctionality.HibernateFunctionality;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/updateProject")
public class UpdateProject extends HttpServlet {
private static final int DO_UPDATE = -1;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HibernateProjectsDAOImpl hbPrImpl = new HibernateProjectsDAOImpl();
        List<Projects> projects = hbPrImpl.getAll();

        req.setAttribute("allProjects", projects);
        req.getRequestDispatcher("view/read/readAllProjectsList.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String param = req.getParameter("projectId");
        int id = Integer.parseInt(param);

        HibernateFunctionality hbFunc = new HibernateFunctionality();
        if (id != DO_UPDATE) {
            req.setAttribute("idProject", id);
            req.getRequestDispatcher("view/update/updateProject.jsp").forward(req, resp);

        } else {

            for (String paramName : req.getParameterMap().keySet()) {
                System.out.println(paramName + "=" + req.getParameter(paramName));
            }
            HibernateFunctionality hibFunc = new HibernateFunctionality();

            int idProj = Integer.parseInt(req.getParameter("prId"));
            System.out.println("idproj = " + idProj);
            String prName = req.getParameter("projectName");
            String prDescr = req.getParameter("projectDescr");
            int prPrice = Integer.valueOf(req.getParameter("projectPrice"));

            hibFunc.hibUpdateProject(idProj, prName, prDescr, prPrice);
            resp.sendRedirect("http://localhost:8080/updateProject?method=2");  //need to update url
        }
    }
}
