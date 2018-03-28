package anotationServlets.update;

import dao.hibernate.HibernateDeveloperDAOImpl;
import entities.Developer;
import hibernateFunctionality.HibernateFunctionality;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/updateDeveloper")
public class UpdateDeveloper extends HttpServlet {
    private static final int DO_UPDATE = -1;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HibernateDeveloperDAOImpl hbDevImpl = new HibernateDeveloperDAOImpl();
        List<Developer> developers = hbDevImpl.getAll();

        req.setAttribute("developers", developers);
        req.getRequestDispatcher("view/read/readDevelopers.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String param = req.getParameter("developerId");
        int id = Integer.parseInt(param);

        System.out.println(id);
        HibernateFunctionality hbFunc = new HibernateFunctionality();
        if (id != DO_UPDATE) {
            System.out.println("we are in if");
            System.out.println("ifid  " + id);
            req.setAttribute("idDeveloper", id);
            req.getRequestDispatcher("view/update/updateDeveloper.jsp").forward(req, resp);

        } else {
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

            resp.sendRedirect("http://localhost:8080/updateDeveloper?method=2");  //need to update url
        }
    }
}
