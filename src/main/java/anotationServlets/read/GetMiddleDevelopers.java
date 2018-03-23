package anotationServlets.read;

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
        //HibernateFunctionality hbFunc = new HibernateFunctionality();
        //List<String[]> middleDevelopers =  hbFunc.hbGetMiddleDevelopers();
        List<String[]> middleDevelopers = new ArrayList<>();
        String[] asd = {"asdd", "asfgq"};
        String[] qwe = {"qwe", "qweras"};
        middleDevelopers.add(asd);
        middleDevelopers.add(qwe);
        for (String[] names : middleDevelopers
             ) {
            req.setAttribute("firstName",names[0]);
            req.setAttribute("lastName",names[1]);
        }

        req.setAttribute("middleDevelopers", middleDevelopers);
        req.getRequestDispatcher("view/read/readMiddleDevelopers.jsp").forward(req,resp);
    }
}
