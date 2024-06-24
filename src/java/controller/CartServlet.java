/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.DAOBook;
import dal.DAOOrder;
import entity.Account;
import entity.Book;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author trand
 */
@WebServlet(name = "CartServlet", urlPatterns = {"/CartURL"})
public class CartServlet extends HttpServlet {

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
        //create constant ship price
        DAOBook dp = new DAOBook();
        DAOOrder d = new DAOOrder();
        HttpSession session = request.getSession();
        String service = request.getParameter("Service");
        if (service == null) {
            request.getRequestDispatcher("Cart.jsp").forward(request, response);
            return;
        }
        if (service.equals("addToCart")) {
            String pid_raw = request.getParameter("id");
            String key = "cart-" + pid_raw;
            String quantity_raw = request.getParameter("quantity");
            int pid = Integer.parseInt(pid_raw);
            int quantity = Integer.parseInt(quantity_raw);
            Book p = (Book) session.getAttribute(key);
            //IF DONT EXIST
            if (p == null) {
                Book p_root = dp.getProductById(pid);
                Book pAdd = new Book(
                        p_root.getName(),
                        pid,
                        quantity,
                        p_root.getPrice(),
                        p_root.getAuthor(),
                        p_root.getImage(),
                        p_root.getLanguage(),
                        p_root.getCategory(),
                        p_root.getPublisher(),
                        p_root.getDescription(),
                        p_root.getNum_of_page(),
                        p_root.getDiscount()
                );
                session.setAttribute(key, pAdd);
            } else {
                //IF HAVE EXIST create a new p and set in old key
                Book pAdd = new Book(
                        p.getName(),
                        pid,
                        p.getQuantity() + quantity,
                        p.getPrice(),
                        p.getAuthor(),
                        p.getImage(),
                        p.getLanguage(),
                        p.getCategory(),
                        p.getPublisher(),
                        p.getDescription(),
                        p.getNum_of_page(),
                        p.getDiscount()
                );

                session.setAttribute(key, pAdd);
            }
            response.sendRedirect("Cart.jsp");
            return;
        }
        
        if(service.equals("addQuantity")) {
         String pid_raw = request.getParameter("product_id");
         int pid = Integer.parseInt(pid_raw);
         String key = "cart-" + pid_raw;
         Book p_root = dp.getProductById(pid);
         Book p = (Book)session.getAttribute(key);
         int quantity = 0;
            //IF DONT EXIST
            if(p != null) {
            if ((p.getQuantity() + 1) == p_root.getQuantity()) {
                    quantity = p_root.getQuantity();
            } else {
              quantity = p.getQuantity()+1;
            }
           // create a new p and set in old key
            Book pAdd = new Book(
                        p_root.getName(),
                        pid,
                        quantity,
                        p_root.getPrice(),
                        p_root.getAuthor(),
                        p_root.getImage(),
                        p_root.getLanguage(),
                        p_root.getCategory(),
                        p_root.getPublisher(),
                        p_root.getDescription(),
                        p_root.getNum_of_page(),
                        p_root.getDiscount()
                );
            session.setAttribute(key, pAdd);
            } 
           response.sendRedirect("CartURL");
        }
        if(service.equals("minusQuantity")) {
         String pid_raw = request.getParameter("product_id");
         int pid = Integer.parseInt(pid_raw);
         String key = "cart-" + pid_raw;
         Book p = (Book)session.getAttribute(key);
            //IF DONT EXIST
            if (p != null) {
                // create a new p and set in old key
                //check is delete
                if ((p.getQuantity() - 1) == 0) {
                    session.removeAttribute(key);
                } else {
                    Book pAdd = new Book(
                        p.getName(),
                        pid,
                        p.getQuantity() -1,
                        p.getPrice(),
                        p.getAuthor(),
                        p.getImage(),
                        p.getLanguage(),
                        p.getCategory(),
                        p.getPublisher(),
                        p.getDescription(),
                        p.getNum_of_page(),
                        p.getDiscount()
                );
                    session.setAttribute(key, pAdd);
                }
            }
          response.sendRedirect("CartURL");
        }
        
        if(service.equals("deleteInCart")) {
          String pid_raw = request.getParameter("product_id");
          String key = "cart-" + pid_raw;
          session.removeAttribute(key);
          response.sendRedirect("CartURL");
        }
        
        if (service.equals("checkout")) {
            Vector<Book> listItem = new Vector<>();
            Account acc = (Account) session.getAttribute("account");
            if(acc != null) {
            java.util.Enumeration em = session.getAttributeNames();
            while (em.hasMoreElements()) {
                //when get from session id is cart-id
                String id = em.nextElement().toString();
                if (id.startsWith("cart")) {
                    //add product into list before remove in session
                    Book pro_session = (Book) session.getAttribute(id);
                    //get product in database but the quantity is number want to order
                    Book pAdd = dp.getProductById(pro_session.getId());
                    pAdd.setQuantity(pro_session.getQuantity());
                    listItem.add(pAdd);
                    session.removeAttribute(id);
                }
            }
            if(listItem.size() > 0) {
              d.checkcount(acc, listItem);
            }
            response.sendRedirect("CartURL");
            } else {
             response.sendRedirect("login");
            }
        }
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

}
