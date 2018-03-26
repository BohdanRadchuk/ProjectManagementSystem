package anotationServlets.read;

import entities.Developer;
import hibernateFunctionality.HibernateFunctionality;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/getMiddleDevelopers")
public class GetMiddleDevelopers extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HibernateFunctionality hbFunc = new HibernateFunctionality();
        List<Developer> middleDevelopers = hbFunc.hbGetMiddleDevelopers();
        req.setAttribute("developers", middleDevelopers);
        req.getRequestDispatcher("view/read/readDevelopers.jsp").forward(req,resp);
    }
}
