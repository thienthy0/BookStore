/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.DAOBook;
import entity.Book;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author laptop368
 */
@WebServlet(name = "ListBook", urlPatterns = {"/ListBook"})
public class ListBook extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy danh sách tất cả các sách
        
        DAOBook dao = new DAOBook();
        List<Book> allBooks = dao.getAllProduct();
        // Lấy số trang hiện tại từ request, nếu không có thì mặc định là trang 1
        int page = 1;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        // Tính toán tổng số trang
        int totalBooks = allBooks.size();
        int totalPages = (int) Math.ceil((double) totalBooks / 10);

        // Tính toán chỉ số bắt đầu và kết thúc cho trang hiện tại
        int startIndex = (page - 1) * 10;
        int endIndex = Math.min(startIndex + 10, totalBooks);

        // Lấy danh sách sách cho trang hiện tại
        List<Book> booksForPage = allBooks.subList(startIndex, endIndex);

        // Thiết lập các thuộc tính request để truyền cho JSP
        request.setAttribute("booksForPage", booksForPage);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);

        
        request.getRequestDispatcher("HomePage.jsp").forward(request, response);
    }

    
   

}
