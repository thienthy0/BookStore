package controller;

import dal.DAOEmployee;
import entity.Account;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "EmployeeServlet", urlPatterns = {"/employee"})
public class EmployeeServlet extends HttpServlet {

    private static final int NUMBER_EMPLOYEE_IN_PAGE = 5;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        DAOEmployee daoEmployee = new DAOEmployee();
        String searchText = request.getParameter("searchText");
        List<Account> list;
        if (searchText != null && !searchText.isEmpty()) {
            list = daoEmployee.searchAccountsByName(searchText);
        } else {
            int numberOfEmployees = daoEmployee.countAccounts();
            int totalPage = calculateTotalPages(NUMBER_EMPLOYEE_IN_PAGE, numberOfEmployees);
            int pageIndex = parsePageIndex(request.getParameter("pageIndex"));
            list = daoEmployee.getAccountsByPage(pageIndex, NUMBER_EMPLOYEE_IN_PAGE);
            request.setAttribute("totalPage", totalPage);
            request.setAttribute("pageIndex", pageIndex);
        }
        request.setAttribute("list", list);
        request.getRequestDispatcher("employeeList.jsp").forward(request, response);
    }

    private int calculateTotalPages(int numberEmployeeInPage, int totalEmployees) {
        int totalPages = totalEmployees / numberEmployeeInPage;
        if (totalEmployees % numberEmployeeInPage != 0) {
            totalPages += 1;
        }
        return totalPages;
    }

    private int parsePageIndex(String pageIndexStr) {
        int pageIndex = 1;
        try {
            pageIndex = Integer.parseInt(pageIndexStr);
            if (pageIndex <= 0) {
                pageIndex = 1;
            }
        } catch (NumberFormatException e) {
            // Do nothing, pageIndex will remain 1
        }
        return pageIndex;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Employee Servlet with pagination";
    }
}
