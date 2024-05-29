/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.DaoEmployee;
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
public class updateServlet extends HttpServlet {

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
        DaoEmployee dao = new DaoEmployee();
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
         DaoEmployee dao = new DaoEmployee();
         dao.updateEmployee(e_id, e_name, position, e_email, e_phone, e_address, gender, DOB);
         response.sendRedirect("employee");
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
