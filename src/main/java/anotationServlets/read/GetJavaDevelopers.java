package anotationServlets.read;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/getJavaDevelopers")
public class GetJavaDevelopers extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> javaDevs = new ArrayList<>();
        javaDevs.add("asdd");
        req.setAttribute("asd",javaDevs.get(0));
        javaDevs.add("qweee");
        req.setAttribute("qwe",javaDevs.get(1));
        req.setAttribute("javadevs", javaDevs);
        req.getRequestDispatcher("view/read/readJavaDevs.jsp").forward(req,resp);
    }
}
