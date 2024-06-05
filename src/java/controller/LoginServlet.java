package controller;

import dal.DAOAccount;
import entity.Account;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("Login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAOAccount daoAccount = new DAOAccount();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Account account = daoAccount.validateCustomer(username, password);
        
        if (account != null) {
            HttpSession session = request.getSession();
            session.setAttribute("account", account);
            
            if (account.getIs_admin()) {
                response.sendRedirect("employee");
            } else if (account.getIs_employee()) {
                response.sendRedirect("ManageProduct");
            } else if (account.getActive()) {
                response.sendRedirect("BookURL");
            } else {
                response.sendRedirect("BookURL");
            }

        } else {
            request.setAttribute("mess", "Email or password is wrong");
            request.getRequestDispatcher("Login.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
