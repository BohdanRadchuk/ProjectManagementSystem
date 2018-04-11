package anotationServlets.managers;

import dao.hibernate.HibernateProjectsDAOImpl;
import entities.ProjectInfo;
import entities.Projects;
import hibernateFunctionality.HibernateFunctionality;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ProjectManager {
    private static final String CREATE_PROJECT_URL = "view/create/createProject.jsp";
    private static final String CREATE_REDIRECT_URL = "http://localhost:8080/createProject";
    private static final String READ_DEVELOPERS_ON_PROJECT_URL = "view/read/readAllDevelopersOnProjects.jsp";
    private static final String READ_ALL_PROJECTS_URL = "view/read/readAllProjectsList.jsp";
    private static final int DO_UPDATE = -1;
    private static final String UPDATE_PROJECT_URL = "view/update/updateProject.jsp";
    private static final String UPDATE_REDIRECT_URL = "http://localhost:8080/updateProject?method=2";
    private static final String DELETE_REDIRECT_URL = "http://localhost:8080/deleteProject?method=3";

    private HibernateFunctionality hbFunc = new HibernateFunctionality();
    private HibernateProjectsDAOImpl hbPrImpl = new HibernateProjectsDAOImpl();

    //create project form
    public void createProjectGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(CREATE_PROJECT_URL).forward(req, resp);
    }

    //creating new project
    public void createProjectPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        for (String paramName : req.getParameterMap().keySet()) {
            System.out.println(paramName + "=" + req.getParameter(paramName));
        }

        String prName = req.getParameter("projectName");
        String prDescr = req.getParameter("projectDescr");
        int prPrice = Integer.valueOf(req.getParameter("projectPrice"));

        hbFunc.hibCreateNewProject(prName, prDescr, prPrice);
        resp.sendRedirect(CREATE_REDIRECT_URL);
    }

    //show all projects (parameter method displays buttons: delete, update)
    public void projectAllGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Projects> projects = hbPrImpl.getAll();

        req.setAttribute("allProjects", projects);
        req.getRequestDispatcher(READ_ALL_PROJECTS_URL).forward(req, resp);
    }

    //shows all projects and amount of developers on it
    public void projectReadAmountOfDevelopersGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<ProjectInfo> projectInfo = hbFunc.hbGetProjectsInfo();

        req.setAttribute("projects", projectInfo);
        req.getRequestDispatcher(READ_DEVELOPERS_ON_PROJECT_URL).forward(req, resp);
    }

    //update customer with id in parameter
    public void projectUpdatePost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        String param = req.getParameter("projectId");
        int id = Integer.parseInt(param);
        //checking for project id in params if id was not set - display form to insert new customer data
        if (id != DO_UPDATE) {
            req.setAttribute("idProject", id);
            req.getRequestDispatcher(UPDATE_PROJECT_URL).forward(req, resp);
        }
        //submitted form updates new project data to database
        else {
            for (String paramName : req.getParameterMap().keySet()) {
                System.out.println(paramName + "=" + req.getParameter(paramName));
            }
            int idProj = Integer.parseInt(req.getParameter("prId"));
            System.out.println("idproj = " + idProj);
            String prName = req.getParameter("projectName");
            String prDescr = req.getParameter("projectDescr");
            int prPrice = Integer.valueOf(req.getParameter("projectPrice"));

            hbFunc.hibUpdateProject(idProj, prName, prDescr, prPrice);
            resp.sendRedirect(UPDATE_REDIRECT_URL);
        }
    }

    //delete project with id in parameter
    public void projectDeletePost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String param = req.getParameter("projectId");
        int id = Integer.parseInt(param);
        System.out.println(id);
        hbFunc.hibDeleteProject(id);

        resp.sendRedirect(DELETE_REDIRECT_URL);
    }
}

