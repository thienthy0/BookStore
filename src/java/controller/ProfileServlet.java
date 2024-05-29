package controller;

import dal.DAOAccount;
import entity.Account;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.regex.Pattern;

/**
 * ProfileServlet handles profile-related requests.
 */
public class ProfileServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String[] listService = {"Account info", "Change password"};
        request.setAttribute("listService", listService);

        Account acc = (Account) session.getAttribute("account");
        String service = request.getParameter("Service");

        if (acc != null) {
            if (service == null) {
                service = listService[0];
            }
            request.setAttribute("current", service);
            request.getRequestDispatcher("Profile.jsp").forward(request, response);
        } else {
            response.sendRedirect("login");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAOAccount d = new DAOAccount();
        String service = request.getParameter("Service");
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("account");

        if ("updateInfo".equals(service)) {
            updateInfo(request, response, d, session, acc);
        } else if ("updatePassword".equals(service)) {
            updatePassword(request, response, d, acc);
        }
    }

    private void updateInfo(HttpServletRequest request, HttpServletResponse response, DAOAccount d, HttpSession session, Account acc)
            throws ServletException, IOException {
        String first_name = request.getParameter("first_name");
        String last_name = request.getParameter("last_name");
        String accountImage = request.getParameter("accountImage");

        if (accountImage == null || accountImage.isEmpty()) {
            accountImage = request.getParameter("beforeImage");
        }

        String account_email = request.getParameter("account_email");
        String account_address = request.getParameter("account_address");
        String account_phone = request.getParameter("account_phone");

        String mess = "";
        boolean isSuccess = false;

        if (isValidName(first_name) && isValidName(last_name)) {
            boolean haveUpdate = d.updateAccount(
                    acc.getAccount_id(), account_email, first_name, last_name,
                    account_phone, accountImage, account_address
            );

            Account updatedAccount = d.getAccountById(acc.getAccount_id());
            session.setAttribute("account", updatedAccount);

            mess = haveUpdate ? "Update success" : "Update unsuccess";
            isSuccess = haveUpdate;
        } else {
            mess = "First name and last name must not contain numbers or special characters";
        }

        request.setAttribute("mess", mess);
        request.setAttribute("isSuccess", isSuccess);
        setCommonAttributes(request);
        request.setAttribute("current", "Account info");
        request.getRequestDispatcher("Profile.jsp").forward(request, response);
    }

    private void updatePassword(HttpServletRequest request, HttpServletResponse response, DAOAccount d, Account acc)
            throws ServletException, IOException {
        String currentPassword = request.getParameter("currentPassword");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");

        String mess = "";
        boolean isSuccess = false;

        if (d.validateCustomer(acc.getEmail(), currentPassword) != null) {
            if (isValidPassword(newPassword)) {
                if (newPassword.equals(confirmPassword)) {
                    d.updatePassword(newPassword, acc.getAccount_id());
                    mess = "Change password success";
                    isSuccess = true;
                } else {
                    mess = "The confirm password does not match the new password";
                }
            } else {
                mess = "New password must be at least 8 characters long and include both letters and numbers";
            }
        } else {
            mess = "The current password is incorrect";
        }

        request.setAttribute("mess", mess);
        request.setAttribute("isSuccess", isSuccess);
        setCommonAttributes(request);
        request.setAttribute("current", "Change password");
        request.getRequestDispatcher("Profile.jsp").forward(request, response);
    }
//Password has at least 8 characters including numbers and letters
    private boolean isValidPassword(String password) {
        if (password.length() < 8) {
            return false;
        }
        boolean hasLetter = false;
        boolean hasDigit = false;
        for (char c : password.toCharArray()) {
            if (Character.isLetter(c)) {
                hasLetter = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            }
            if (hasLetter && hasDigit) {
                return true;
            }
        }
        return false;
    }

    private boolean isValidName(String name) {
        // Check if name contains only letters and spaces
        String regex = "^[a-zA-Z\\s]+$";
        return Pattern.matches(regex, name);
    }

    private void setCommonAttributes(HttpServletRequest request) {
        String[] listService = {"Account info", "Change password"};
        request.setAttribute("listService", listService);
    }

    @Override
    public String getServletInfo() {
        return "ProfileServlet handles profile-related requests";
    }
}


