/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.DAOBook;
import dal.DAOCategory;
import entity.Book;
import entity.Category;
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
@WebServlet(name="BookServlet", urlPatterns={"/BookURL"})
public class BookServlet extends HttpServlet {
   
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
        DAOBook dao=new DAOBook();
        DAOCategory ca=new DAOCategory();
        
       List<Book> list=dao.getAllProduct();
       List<Category> listC=ca.getCategory();
       String[] category_name = request.getParameterValues("category");
       
       request.setAttribute("listBook",list);
       request.setAttribute("listC",listC);
       
       request.getRequestDispatcher("HomePage.jsp").forward(request, response);
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
        processRequest(request, response);
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


///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
// */
//
//package controller;
//
//import dal.DAOBook;
//import dal.DAOCategory;
//import entity.Book;
//import entity.Category;
//import java.io.IOException;
//import java.io.PrintWriter;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import java.util.List;
//
///**
// *
// * @author laptop368
// */
//@WebServlet(name="BookServlet", urlPatterns={"/BookURL"})
//public class BookServlet extends HttpServlet {
//   
//    /** 
//     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//  
//protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//    throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        DAOBook dao=new DAOBook();
//         
//        DAOCategory ca=new DAOCategory();   
//       List<Category> listC=ca.getCategory();
//       String[] category_name = request.getParameterValues("category");
//       
//      
//       List<Book> allBooks = dao.getAllProduct();
//        // Lấy số trang hiện tại từ request, nếu không có thì mặc định là trang 1
//        int page = 1;
//        if (request.getParameter("page") != null) {
//            page = Integer.parseInt(request.getParameter("page"));
//        }
//
//        // Tính toán tổng số trang
//        int totalBooks = allBooks.size();
//        int totalPages = (int) Math.ceil((double) totalBooks / 10);
//
//        // Tính toán chỉ số bắt đầu và kết thúc cho trang hiện tại
//        int startIndex = (page - 1) * 10;
//        int endIndex = Math.min(startIndex + 10, totalBooks);
//
//        // Lấy danh sách sách cho trang hiện tại
//        List<Book> booksForPage = allBooks.subList(startIndex, endIndex);
//
//        // Thiết lập các thuộc tính request để truyền cho JSP
//       
//       request.setAttribute("listC",listC);
//        request.setAttribute("booksForPage", booksForPage);
//        request.setAttribute("currentPage", page);
//        request.setAttribute("totalPages", totalPages);
//       
//       request.getRequestDispatcher("HomePage.jsp").forward(request, response);
//    } 
//}
