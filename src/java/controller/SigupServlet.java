/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.DAOAccount;
import entity.Account;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;

/**
 *
 * @author HP
 */
public class SigupServlet extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SigupServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SigupServlet at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("SignUp.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAOAccount d = new DAOAccount();
        String first_name = request.getParameter("first_name");
        String last_name = request.getParameter("last_name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        //khai bao 1 bien error
        String error = "error";

        // Kiểm tra dữ liệu đầu vào
        if (first_name.isEmpty() || last_name.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty()) {
            String mess = "Please fill in all fields.";
            setCommonValues(request, response, mess, first_name, last_name, email, phone);
            return;
        }
//
        if (!isValidEmail(email)) {
            String mess = "Invalid email format.";
            setCommonValues(request, response, mess, first_name, last_name, "", phone);
            return;
        }
//check format password
        if (!isValidPassword(password)) {
            String mess = "Password must be at least 8 character and combination of letters and numbers.";
            setCommonValues(request, response, mess, first_name, last_name, email, phone);
            return;
        }
//check password equal with confirm
        if (!password.equals(confirmPassword)) {
            String mess = "Password and confirm password do not match.";
            setCommonValues(request, response, mess, first_name, last_name, email, phone);
            return;
        }
//Check dublicate email
        Account haveExistEmail = d.getAccountByEmail(email);
        if (haveExistEmail != null) {
            String mess = "Email already exists.";
            setCommonValues(request, response, mess, first_name, last_name, "", phone);
            request.getRequestDispatcher("SignUp.jsp").forward(request, response);
            return;
        }

        int newId = d.getAllAccount().get(d.getAllAccount().size() - 1).getAccount_id() + 1;
        Account acc = new Account(newId, first_name, last_name, phone, email, password);
        boolean haveAdd = d.addAccount(acc);
        HttpSession session = request.getSession();
        if (haveAdd) {
            session.setAttribute("account", acc);
            response.sendRedirect("HomePage.jsp");
        } else {
            String mess = "have error";
            request.setAttribute("message", mess);
            request.getRequestDispatcher("SignUp.jsp").forward(request, response);
        }

    }

    private void setCommonValues(HttpServletRequest request, HttpServletResponse response, String mess, String first_name,
            String last_name, String email, String phone) throws ServletException, IOException {
        request.setAttribute("message", mess);
        request.setAttribute("first_name", first_name);
        request.setAttribute("last_name", last_name);
        request.setAttribute("email", email);
        request.setAttribute("phone", phone);
        request.getRequestDispatcher("SignUp.jsp").forward(request, response);
    }

    private boolean isValidEmail(String email) {
        // Kiểm tra định dạng email
        return email.matches("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
    }

    private boolean isValidPassword(String password) {
        // Kiểm tra độ dài mật khẩu và mức độ mạnh
        return password.length() >= 8 && password.matches(".*[A-Za-z].*") && password.matches(".*\\d.*");
    }
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    public static void main(String[] args) {
        DAOAccount d = new DAOAccount();
        Account acc = new Account(21, "Nguyen", "vu", "0912393759", "vu@gmail.com", "123");
        boolean haveAdd = d.addAccount(acc);
        System.out.println(haveAdd);
    }
}
