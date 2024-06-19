package controller;

import dal.DAOAccount;
import dal.DAOOrder;
import dal.DAOOrderDetail;
import entity.Account;
import entity.Order;
import entity.OrderDetail;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.util.Vector;
import java.util.regex.Pattern;

/**
 * ProfileServlet handles profile-related requests.
 */
public class ProfileServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String[] listService = {"Account info", "My order", "Change password"};
        request.setAttribute("listService", listService);
        DAOOrder o = new DAOOrder();
        Account acc = (Account)session.getAttribute("account");
        String service = request.getParameter("Service");
        
        if (acc != null) {
            if (service == null) {
                service = listService[0];
            }
            
            if(service.equals(listService[1])) {
                DAOOrderDetail doi = new DAOOrderDetail();
                DAOOrder dorder = new DAOOrder();
                int accId = acc.getAccount_id();
                Vector<Order> myOrder = dorder.getAllOrderByAccount(accId);
                Vector<OrderDetail> orderItems = doi.getAllOrderDetail();
                request.setAttribute("myOrder", myOrder);
                request.setAttribute("myOrderItem", orderItems);
            }
            request.setAttribute("current", service);
            request.getRequestDispatcher("Profile.jsp").forward(request, response);
            return;
        } else {
            response.sendRedirect("login");
            return;
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
        String service = request.getParameter("Service");
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("account");

        if ("updateInfo".equals(service)) {
            DAOAccount d = new DAOAccount();
            updateInfo(request, response, d, session, acc);
        } else if ("updatePassword".equals(service)) {
            DAOAccount d = new DAOAccount();
            updatePassword(request, response, d, acc);
        } else if ("updateStatus".equals(service)) {
            updateStatus(request, response);
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
        String account_description = request.getParameter("account_description");

        String mess = "";
        boolean isSuccess = false;

        if (isValidName(first_name) && isValidName(last_name)) {
            boolean haveUpdate = d.updateAccount(
                    acc.getAccount_id(), account_email, first_name, last_name,
                    account_phone, accountImage, account_address, account_description
            );

            Account updatedAccount = d.getAccountById(acc.getAccount_id());
            session.setAttribute("account", updatedAccount);

            mess = haveUpdate ? "Update success" : "Update unsuccess";
            isSuccess = haveUpdate;
        } else {
            mess = "First name and last name must not contain numbers, leading spaces, or special character.";
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

        if (d.validateUser(acc.getEmail(), currentPassword) != null) {
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

    private void updateStatus(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String oId_raw = request.getParameter("oId");
        int oId = Integer.parseInt(oId_raw);
        String status = request.getParameter("status");

        DAOOrder o = new DAOOrder();
        boolean n = o.updateStatusOrder(status, oId);
        String mess = n ? "Update success" : "Update unsuccess";

        request.setAttribute("mess", mess);
        request.setAttribute("isSuccess", n);
        setCommonAttributes(request);
        request.setAttribute("current", "My order");
        processRequest(request, response);
    }

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
        String regex = "^(?! )[a-zA-ZÀ-ÿ\\s]*(?! {3,})$";
        return Pattern.matches(regex, name);
    }

    private void setCommonAttributes(HttpServletRequest request) {
        String[] listService = {"Account info", "My order", "Change password"};
        request.setAttribute("listService", listService);
    }

    @Override
    public String getServletInfo() {
        return "ProfileServlet handles profile-related requests";
    }
}
