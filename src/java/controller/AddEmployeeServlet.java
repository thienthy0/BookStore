package controller;

import dal.EmployeeDao;
import entity.Employee;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class AddEmployeeServlet
 */
public class AddEmployeeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddEmployee</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddEmployee at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

   @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String e_name = request.getParameter("e_name");
    int position = Integer.parseInt(request.getParameter("position"));
    String e_email = request.getParameter("e_email");
    String e_phone = request.getParameter("e_phone");
    String e_address = request.getParameter("e_address");
    boolean gender = Boolean.parseBoolean(request.getParameter("gender"));
    String DOB = request.getParameter("DOB");

    if (position >= 5) {
        request.setAttribute("error", "Position must be less than 4");
        request.setAttribute("ed", new Employee(0, e_name, position, e_email, e_phone, e_address, gender, DOB)); 
        request.getRequestDispatcher("createEmployee.jsp").forward(request, response);
        return;
    }

    if (!e_phone.matches("\\d{10}")) {
        request.setAttribute("error", "Phone must be 10 digits and contain only numbers");
         request.setAttribute("ed", new Employee(0, e_name, position, e_email, e_phone, e_address, gender, DOB)); 
      
        request.getRequestDispatcher("createEmployee.jsp").forward(request, response);
        return;
    }

    if (e_name.matches(".*\\d.*")) {
        request.setAttribute("error", "Name must not contain numbers");
         request.setAttribute("ed", new Employee(0, e_name, position, e_email, e_phone, e_address, gender, DOB)); 
      
        request.getRequestDispatcher("createEmployee.jsp").forward(request, response);
        return;
    }

    if (!e_email.endsWith("@gmail.com")) {
        request.setAttribute("error", "Email must be in the format of example@gmail.com");
         request.setAttribute("ed", new Employee(0, e_name, position, e_email, e_phone, e_address, gender, DOB)); 
      
        request.getRequestDispatcher("createEmployee.jsp").forward(request, response);
        return;
    }

    Employee newEmployee = new Employee(0, e_name, position, e_email, e_phone, e_address, gender, DOB);

    EmployeeDao employeeDao = new EmployeeDao();
    employeeDao.createEmployee(newEmployee);
    response.sendRedirect("employee");
}


    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
