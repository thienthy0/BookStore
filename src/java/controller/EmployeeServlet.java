package controller;

import dal.DAOEmployee;
import entity.Employee;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "EmployeeServlet", urlPatterns = {"/employee"})
public class EmployeeServlet extends HttpServlet {

    private static final int NUMBER_EMPLOYEE_IN_PAGE = 5; // Số nhân viên trên mỗi trang

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        DAOEmployee daoEmployee = new DAOEmployee();

        // Tính tổng số nhân viên và tổng số trang
        int numberOfEmployees = daoEmployee.countEmployees();
        int totalPage = calculateTotalPages(NUMBER_EMPLOYEE_IN_PAGE, numberOfEmployees);

        // Lấy chỉ số trang hiện tại từ yêu cầu
        int pageIndex = parsePageIndex(request.getParameter("pageIndex"));

        // Lấy danh sách nhân viên cho trang hiện tại
        List<Employee> list = daoEmployee.getEmployeesByPage(pageIndex, NUMBER_EMPLOYEE_IN_PAGE);

        // Thiết lập các thuộc tính cho yêu cầu
        request.setAttribute("listEmployee", list);
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("pageIndex", pageIndex);

        // Chuyển tiếp yêu cầu đến trang JSP để hiển thị danh sách nhân viên
        request.getRequestDispatcher("employeeList.jsp").forward(request, response);
    }

    // Hàm tính tổng số trang
    private int calculateTotalPages(int numberEmployeeInPage, int totalEmployees) {
        int totalPages = totalEmployees / numberEmployeeInPage;
        if (totalEmployees % numberEmployeeInPage != 0) {
            totalPages += 1;
        }
        return totalPages;
    }

    // Hàm phân tích chỉ số trang từ yêu cầu
    private int parsePageIndex(String pageIndexStr) {
        int pageIndex = 1; // Mặc định là trang đầu tiên
        try {
            pageIndex = Integer.parseInt(pageIndexStr);
            if (pageIndex <= 0) {
                pageIndex = 1; // Nếu chỉ số trang không hợp lệ, thiết lập lại là trang đầu tiên
            }
        } catch (NumberFormatException e) {
            // Nếu không phân tích được chỉ số trang, giữ nguyên giá trị mặc định là trang đầu tiên
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
