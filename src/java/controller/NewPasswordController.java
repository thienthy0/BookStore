/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.DAOAccount;

import entity.Account;
import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static util.Validate.toSHA1;

/**
 *
 * @author msii
 */
@WebServlet(name = "NewPasswordController", urlPatterns = {"/newPassword"})
public class NewPasswordController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            String email = (String) session.getAttribute("email");
            String newPassword = request.getParameter("password");
            String confPassword = request.getParameter("confPassword");
            RequestDispatcher dispatcher = null;
            Account acc=new Account();
            DAOAccount dao = new DAOAccount();

            if (newPassword != null && confPassword != null && newPassword.equals(confPassword)) {
                acc = dao.getAccountByEmail(email);
                //Ma hoa pass
//                String newPassword = toSHA1(newPassword);
                dao.changePassword(newPassword, acc.getAccount_id());

                if (dao.getAccountByEmail(email).getPassword().equals(newPassword)) {
                    request.setAttribute("status", "resetSuccess");
                    dispatcher = request.getRequestDispatcher("Login.jsp");
                } else {
                    request.setAttribute("status", "resetFailed");
                    dispatcher = request.getRequestDispatcher("newPassword.jsp");
                }
                dispatcher.forward(request, response);

            } else {
                request.setAttribute("status", "New password and confirm receive password are not the same");
                request.getRequestDispatcher("newPassword.jsp").forward(request, response);
            }
        } catch (Exception e){
            
        }
    }

}
