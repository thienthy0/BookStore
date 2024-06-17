package controller;

import dal.DAOEmployee;
import entity.Account;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import jakarta.servlet.http.HttpServlet;
import java.io.PrintWriter;
import java.sql.Date;
@WebServlet(name = "updateEmployeeServlet", urlPatterns = {"/edit"})
public class updateEmployeeServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int e_id = Integer.parseInt(request.getParameter("e_id"));
        DAOEmployee dao = new DAOEmployee();
        Account e = dao.getAccountById(e_id);

        request.setAttribute("ed", e);

        request.getRequestDispatcher("employeeEdit.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int e_id = Integer.parseInt(request.getParameter("e_id"));
        String e_name = request.getParameter("e_name");
        int position = Integer.parseInt(request.getParameter("position"));
        String e_email = request.getParameter("e_email");
        String e_phone = request.getParameter("e_phone");
        String e_address = request.getParameter("e_address");
        boolean gender = Boolean.parseBoolean(request.getParameter("gender"));
        String DOB = request.getParameter("DOB");
        Date DateOfBirth = Date.valueOf(DOB); 

        if (position >= 4) {
            request.setAttribute("error", "Position must be less than or equal to 4");
            request.setAttribute("ed", new Account(e_id, e_id, e_name, e_name, e_phone, e_email, DOB, e_email, e_address, gender, DateOfBirth));
            request.getRequestDispatcher("employeeEdit.jsp").forward(request, response);
            return;
        }

        if (e_name.matches(".*\\d.*")) {
            request.setAttribute("error", "Name must not contain numbers");
            request.setAttribute("ed", new Account(e_id, e_id, e_name, e_name, e_phone, e_email, DOB, e_email, e_address, gender, DateOfBirth));
            request.getRequestDispatcher("employeeEdit.jsp").forward(request, response);
            return;
        }

        if (!e_phone.matches("\\d{10}")) {
            request.setAttribute("error", "Phone must be 10 digits and contain only numbers");
            request.setAttribute("ed", new Account(e_id, e_id, e_name, e_name, e_phone, e_email, DOB, e_email, e_address, gender, DateOfBirth));
            request.getRequestDispatcher("employeeEdit.jsp").forward(request, response);
            return;
        }

        if (!e_email.matches("[a-zA-Z0-9._%+-]+@gmail\\.com")) {
            request.setAttribute("error", "Invalid email format. Email must be in the form of example@gmail.com");
            request.setAttribute("ed", new Account(e_id, e_id, e_name, e_name, e_phone, e_email, DOB, e_email, e_address, gender, DateOfBirth));
            request.getRequestDispatcher("employeeEdit.jsp").forward(request, response);
            return;
        }

        DAOEmployee dao = new DAOEmployee();
        dao.updateAccount(e_id, e_name, e_name, e_phone, e_email, e_address, e_id, gender, DateOfBirth);
        response.sendRedirect("employee");
    }

    @Override
    public String getServletInfo() {
        return "Servlet for updating employee details.";
    }
}
