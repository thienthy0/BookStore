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
        Account account = daoAccount.validateUser(username, password);

        if (account != null) {
            HttpSession session = request.getSession();
            session.setAttribute("account", account);

            // Kiểm tra vai trò của tài khoản và chuyển hướng phù hợp
            int roleId = account.getRole_id();

            if (roleId == 1) { // Admin
                response.sendRedirect("Home.jsp");
            } else if (roleId == 2) { // Quản lý
                response.sendRedirect("ManageProduct"); // Điều chỉnh điều này đến trang quản lý
            } else if (roleId == 3) { // Nhân viên
                response.sendRedirect("ManageProduct");
            } else if (roleId == 4) { // Người dùng
                response.sendRedirect("BookURL");
            } else {
                response.sendRedirect("BookURL"); // Trường hợp vai trò không xác định
            }
        } else {
            request.setAttribute("mess", "Email or Password is wrong");
            request.getRequestDispatcher("Login.jsp").forward(request, response);
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
