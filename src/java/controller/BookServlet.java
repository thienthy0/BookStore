/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.DAOAuthor;
import dal.DAOCategory;
import dal.DAOBook;
import entity.Author;
import entity.Category;
import entity.Book;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.Vector;

/**
 *
 * @author HP
 */
@WebServlet(name = "BookServlet", urlPatterns = {"/BookURL"})
public class BookServlet extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        DAOBook d = new DAOBook();
        try {
            String service = request.getParameter("Service");
            if (service == null) {
                service = "getAll";
            }
            String order = request.getParameter("orderBy");
            if(order != null) {
             request.setAttribute("order", order);
            }
            
            //default will getAll
            if (service.equals("getAll")) {
                Vector<Book> list = new Vector<>();
                
                list = d.getAllProduct(order);
                
                request.setAttribute("listBook", list);
                setCommonAttributes(request, d);
                request.getRequestDispatcher("BookList.jsp").forward(request, response);
            }
            
            //filter product
            if (service.equals("filter")) {
                Vector<Book> list = new Vector<>();
                String[] category_name = request.getParameterValues("category");
                String[] author_name = request.getParameterValues("author");
                
                String min_price = request.getParameter("minPrice");
                String max_price = request.getParameter("maxPrice");
                String discount = request.getParameter("discount");

                request.setAttribute("category_select", category_name);
                request.setAttribute("author_select", author_name);
                request.setAttribute("min_price", min_price);
                request.setAttribute("max_price", max_price);
                request.setAttribute("listBook", discount);
                
                list = d.search(category_name, author_name, min_price, max_price, discount, order);
                    
                setCommonAttributes(request, d);
                request.setAttribute("listBook", list);
//                request.setAttribute("linkProduct", d.link(category_name, author_name, min_price,
//                                max_price, discount, order));
                request.getRequestDispatcher("BookList.jsp").forward(request, response);
            }

            //dieu huong den trang product detail
            if(service.equals("BookDetail")) {
             String pid_raw = request.getParameter("Pid");
             int pid = Integer.parseInt(pid_raw);
             Book currentP = d.getProductById(pid);
             request.setAttribute("currentProduct", currentP);
             request.getRequestDispatcher("BookDetail.jsp").forward(request, response);
            }
            
            //tra ve danh sach product co like name
            if (service.equals("search")) {
              String nameTxt = request.getParameter("keyword");
              Vector<Book> list = d.getBookByName(nameTxt);
              request.setAttribute("listBook", list);
              setCommonAttributes(request, d);
              request.getRequestDispatcher("BookList.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    private void setCommonAttributes(HttpServletRequest request, DAOBook d) throws SQLException {
        DAOCategory dCat = new DAOCategory();
        DAOAuthor dAuthor = new DAOAuthor();
        Vector<Author> list_author = dAuthor.getAllAuthor();
        Vector<Category> list_category = dCat.getAllCategory();
        int[] Listdiscount = {5, 10, 20, 30, 40};
        request.setAttribute("listAuthor", list_author);
        request.setAttribute("listCategory", list_category);
        request.setAttribute("ListDiscount", Listdiscount);
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
        processRequest(request, response);
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
        processRequest(request, response);
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
//        Vector<Brand> list_brand = dbrand.getAllBrand();

    public static void main(String[] args) throws SQLException {
       DAOBook d = new DAOBook();
       System.out.println(d.getProductById(4));
    }
}
