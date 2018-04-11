package anotationServlets.managers;

import dao.hibernate.HibernateDeveloperDAOImpl;
import dao.hibernate.HibernateProjectsDAOImpl;
import entities.Developer;
import entities.Projects;
import hibernateFunctionality.HibernateFunctionality;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class DeveloperManager {
    private static final String CREATE_DEVELOPER_URL = "view/create/createDeveloper.jsp";
    private static final String CREATE_REDIRECT_URL = "http://localhost:8080/createDev";
    private static final String READ_DEVELOPERS_URL = "view/read/readDevelopers.jsp";
    private static final String READ_ALL_PROJECTS_URL = "view/read/readAllProjectsList.jsp";
    private static final String DELETE_REDIRECT_URL = "http://localhost:8080/deleteDeveloper?method=3";
    private static final int DO_UPDATE = -1;
    private static final String UPDATE_DEVELOPERS_URL = "view/update/updateDeveloper.jsp";
    private static final String UPDATE_REDIRECT_URL = "http://localhost:8080/updateDeveloper?method=2";

    private HibernateFunctionality hbFunc = new HibernateFunctionality();
    private HibernateDeveloperDAOImpl hbDevImpl = new HibernateDeveloperDAOImpl();

    //create developer form
    public void developerCreateGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        req.getRequestDispatcher(CREATE_DEVELOPER_URL).forward(req, resp);
    }

    //creating new developer
    public void developerCreatePost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        for (String paramName : req.getParameterMap().keySet()) {
            System.out.println(paramName + "=" + req.getParameter(paramName));
        }

        String devFirstName = req.getParameter("firstName");
        String devSecName = req.getParameter("secondaryName");
        int age = Integer.valueOf(req.getParameter("age"));
        String gender = req.getParameter("gender");
        long salary = Long.valueOf(req.getParameter("salary"));

        hbFunc.hibCreateNewDeveloper(devFirstName, devSecName, age, gender, salary);
        resp.sendRedirect(CREATE_REDIRECT_URL);
    }

    //shows all existing developers (parameter method displays buttons: delete, update)
    public void developerReadAllGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        List<Developer> developers = hbDevImpl.getAll();

        req.setAttribute("developers", developers);
        req.getRequestDispatcher(READ_DEVELOPERS_URL).forward(req, resp);
    }

    //shows all Java developers
    public void developersReadJavaGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        List<Developer> javaDevs = hbFunc.hbGetJavaDevelopers();
        req.setAttribute("developers", javaDevs);
        req.getRequestDispatcher(READ_DEVELOPERS_URL).forward(req, resp);
    }

    //shows all Middle developers
    public void developersReadMiddleGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        List<Developer> middleDevelopers = hbFunc.hbGetMiddleDevelopers();
        req.setAttribute("developers", middleDevelopers);
        req.getRequestDispatcher(READ_DEVELOPERS_URL).forward(req, resp);
    }

    //shows table of all projects
    public void developerReadOnOneProjectGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        HibernateProjectsDAOImpl hbPrImpl = new HibernateProjectsDAOImpl();
        List<Projects> projects = hbPrImpl.getAll();

        req.setAttribute("allProjects", projects);
        req.getRequestDispatcher(READ_ALL_PROJECTS_URL).forward(req, resp);
    }

    //shows developers working on single project
    public void developerReadOnOneProjectPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        String param = req.getParameter("projectId");                       //getting project id from parameter
        int id = Integer.parseInt(param);
        List<Developer> projectDevs = hbFunc.hbGetDevelopersOfProject(id);      //getting developers on project with id
        req.setAttribute("developers", projectDevs);
        req.getRequestDispatcher(READ_DEVELOPERS_URL).forward(req, resp);
    }


    //update developer with id in parameter
    public void developerUpdatePost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String param = req.getParameter("developerId");
        int id = Integer.parseInt(param);

        System.out.println(id);
        //checking for developer id in params if id was not set - display form to insert new developer data
        if (id != DO_UPDATE) {
            System.out.println("we are in if");
            System.out.println("ifid  " + id);
            req.setAttribute("idDeveloper", id);
            req.getRequestDispatcher(UPDATE_DEVELOPERS_URL).forward(req, resp);
        }
        //submitted form updates new developer data to database
        else {
            System.out.println("we are in else");
            for (String paramName : req.getParameterMap().keySet()) {
                System.out.println(paramName + "=" + req.getParameter(paramName));
            }
            int idDev = Integer.parseInt(req.getParameter("devId"));
            String devFirstName = req.getParameter("firstName");
            String devSecName = req.getParameter("secondaryName");
            int age = Integer.valueOf(req.getParameter("age"));
            String gender = req.getParameter("gender");
            long salary = Long.valueOf(req.getParameter("salary"));

            hbFunc.hibUpdateDeveloper(idDev, devFirstName, devSecName, age, gender, salary);

            resp.sendRedirect(UPDATE_REDIRECT_URL);
        }
    }

    //delete developer with id from parameter
    public void developerDeletePost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String param = req.getParameter("developerId");
        int id = Integer.parseInt(param);
        hbFunc.hibDeleteDeveloper(id);

        resp.sendRedirect(DELETE_REDIRECT_URL);
    }
}
