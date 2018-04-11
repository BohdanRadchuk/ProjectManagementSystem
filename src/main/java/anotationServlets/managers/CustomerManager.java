package anotationServlets.managers;

import dao.hibernate.HibernateCustomersDAOImpl;
import entities.Customers;
import hibernateFunctionality.HibernateFunctionality;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CustomerManager {
    private static final String CREATE_CUSTOMER_URL = "view/create/createCustomer.jsp";
    private static final String CREATE_REDIRECT_URL = "http://localhost:8080/createCust";
    private static final String READ_ALL_CUSTOMERS_URL = "view/read/readAllCustomers.jsp";
    private static final String DELETE_REDIRECT_URL = "http://localhost:8080/deleteCustomer?method=3";
    private static final int DO_UPDATE = -1;
    private static final String UPDATE_CUSTOMERS_URL = "view/update/updateCustomer.jsp";
    private static final String UPDATE_REDIRECT_URL = "http://localhost:8080/updateCustomer?method=2";
    private HibernateFunctionality hbFunc = new HibernateFunctionality();
    private HibernateCustomersDAOImpl hbCustImpl = new HibernateCustomersDAOImpl();

    //create customer form
    public void customerCreateGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        req.getRequestDispatcher(CREATE_CUSTOMER_URL).forward(req, resp);
    }

    //creating new customer
    public void customerCreatePost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        for (String paramName : req.getParameterMap().keySet()) {
            System.out.println(paramName + "=" + req.getParameter(paramName));
        }
        String custName = req.getParameter("custName");
        byte stOrPr = Byte.valueOf(req.getParameter("stOrPr"));
        System.out.println(stOrPr);
        hbFunc.hibCreateNewCustomer(custName, stOrPr);
        resp.sendRedirect(CREATE_REDIRECT_URL);
    }

    //show all customers (parameter method displays buttons: delete, update)
    public void customerReadGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Customers> customers = hbCustImpl.getAll();

        req.setAttribute("customers", customers);
        req.getRequestDispatcher(READ_ALL_CUSTOMERS_URL).forward(req, resp);
    }

    //update customer with id in parameter
    public void customerUpdatePost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        String param = req.getParameter("customerId");
        int id = Integer.parseInt(param);
        //checking for customer id in params if id was not set - display form to insert new customer data
        if (id != DO_UPDATE) {
            req.setAttribute("idCustomer", id);
            req.getRequestDispatcher(UPDATE_CUSTOMERS_URL).forward(req, resp);
        }
        //submitted form updates new customer data to database
        else {
            for (String paramName : req.getParameterMap().keySet()) {
                System.out.println(paramName + "=" + req.getParameter(paramName));
            }
            int idCust = Integer.parseInt(req.getParameter("custId"));
            System.out.println("idCust = " + idCust);
            String custName = req.getParameter("custName");
            byte stOrPr = Byte.valueOf(req.getParameter("stOrPr"));
            System.out.println(stOrPr);
            hbFunc.hibUpdateCustomer(idCust, custName, stOrPr);
            resp.sendRedirect(UPDATE_REDIRECT_URL);
        }
    }

    //delete customer with id in parameter
    public void customerDeletePost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String param = req.getParameter("customerId");
        int id = Integer.parseInt(param);
        System.out.println(id);
        hbFunc.hibDeleteCustomer(id);

        resp.sendRedirect(DELETE_REDIRECT_URL);
    }
}
