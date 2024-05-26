/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.CustomerDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Dell
 */
@WebServlet(name="CChangePassOTPControl", urlPatterns={"/CChangePassOTP"})
public class CChangePassOTPControl extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
            out.println("<title>Servlet CChangePassOTPControl</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CChangePassOTPControl at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String cOTP = request.getParameter("cOTP");
        String newPassword = request.getParameter("cNewPass");
        String confirmPassword = request.getParameter("cConfirmPass");
    
    // Check if the new password and confirm password match
    if (!newPassword.equals(confirmPassword)) {
        // Display an error message or handle the error
        request.setAttribute("message", "New password and confirm password do not match.");
        request.getRequestDispatcher("CChangePassOTP.jsp").forward(request, response);
        return;
    }
    
    // Check if the eOTP is valid
    CustomerDAO customerDAO = new CustomerDAO();
    boolean isCOTPValid = customerDAO.isCOTPValid(cOTP);
    
    if (!isCOTPValid) {
        request.setAttribute("message", "Your OTP is incorrect.");
        request.getRequestDispatcher("CChangePassOTP.jsp").forward(request, response);
        return;
    }
    
    // Change the password in the database
    boolean passwordChanged = customerDAO.changeCPassOTP(cOTP, newPassword);
    
    if (passwordChanged) {
        // Password changed successfully, you can redirect to a success page or display a success message
        response.sendRedirect("Login.jsp");
    } else {
        // Password change failed, you can redirect to an error page or display an error message
        request.setAttribute("message", "Password change failed. Please try again.");
        request.getRequestDispatcher("CChangePassOTP.jsp").forward(request, response);
    }
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
