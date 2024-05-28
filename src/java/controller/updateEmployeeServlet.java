/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.DAOEmployee;
import entity.Employee;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author admin
 */
public class updateEmployeeServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet updateServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet updateServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int e_id = Integer.parseInt(request.getParameter("e_id"));
        DAOEmployee dao = new DAOEmployee();
        Employee e = dao.getEmployeeById(e_id);

        request.setAttribute("ed", e);

        request.getRequestDispatcher("employeeEdit.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    int e_id = Integer.parseInt(request.getParameter("e_id"));
    String e_name = request.getParameter("e_name");
    int position = Integer.parseInt(request.getParameter("position"));
    String e_email = request.getParameter("e_email");
    String e_phone = request.getParameter("e_phone");
    String e_address = request.getParameter("e_address");
    boolean gender = Boolean.parseBoolean(request.getParameter("gender"));
    String DOB = request.getParameter("DOB");

    // Kiểm tra điều kiện cho position và e_phone
    if (position > 4) {
        request.setAttribute("error", "Position must be less than or equal to 4");
        // Lưu các thông tin đã nhập vào request attribute để giữ lại trên form
        request.setAttribute("ed", new Employee(e_id, e_name, position, e_email, e_phone, e_address, gender, DOB));
        request.getRequestDispatcher("employeeEdit.jsp").forward(request, response);
        return;
    }
      // Kiểm tra tên không chứa số
    if (e_name.matches(".*\\d.*")) {
        request.setAttribute("error", "Name must not contain numbers");
        // Lưu các thông tin đã nhập vào request attribute để giữ lại trên form
        request.setAttribute("ed", new Employee(e_id, e_name, position, e_email, e_phone, e_address, gender, DOB)); 
        request.getRequestDispatcher("employeeEdit.jsp").forward(request, response);
        return;
    }

    if (!e_phone.matches("\\d{10}")) {
        request.setAttribute("error", "Phone must be 10 digits and contain only numbers");
        // Lưu các thông tin đã nhập vào request attribute để giữ lại trên form
        request.setAttribute("ed", new Employee(e_id, e_name, position, e_email, e_phone, e_address, gender, DOB));
        request.getRequestDispatcher("employeeEdit.jsp").forward(request, response);
        return;
    }

    // Kiểm tra định dạng email
    if (!e_email.matches("[a-zA-Z0-9._%+-]+@gmail\\.com")) {
        request.setAttribute("error", "Invalid email format. Email must be in the form of example@gmail.com");
        // Lưu các thông tin đã nhập vào request attribute để giữ lại trên form
        request.setAttribute("ed", new Employee(e_id, e_name, position, e_email, e_phone, e_address, gender, DOB));
        request.getRequestDispatcher("employeeEdit.jsp").forward(request, response);
        return;
    }

    DAOEmployee dao = new DAOEmployee();
    dao.updateEmployee(e_id, e_name, position, e_email, e_phone, e_address, gender, DOB);
    response.sendRedirect("employee");
}


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
