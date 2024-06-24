/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.DAOOrder;
import dal.DAOOrderDetail;
import entity.Account;
import entity.Order;
import entity.OrderDetail;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Vector;

/**
 *
 * @author HP
 */
@WebServlet(name = "OrderManager", urlPatterns = {"/OrderManager"})
public class OrderManagerServlet extends HttpServlet {

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
        DAOOrder o = new DAOOrder();
        String service = request.getParameter("Service");

        if (service == null) {
            service = "getAll";
        }
        //GET LIST ORDER
        if (service.equals("getAll")) {
            Vector<Order> listOrder = o.getAllOrder();
//          PrintWriter out= response.getWriter();
//        out.print(listOrder);
            request.setAttribute("listOrder", listOrder);
            request.getRequestDispatcher("OrderManager.jsp").forward(request, response);
        }
//        SEARCH ORDER
        if (service.equals("searchOrder")) {
            int oid = -1;
            String pid_raw = request.getParameter("searchId");
            try {
                oid = Integer.parseInt(pid_raw);
            } catch (NumberFormatException e) {
                oid = -1;
            }
            Order order = o.getOrderById(oid);
            //if not found will return the get all order
            Vector<Order> listOrder = new Vector<>();
            if (order == null) {
                request.setAttribute("mess", "Not found this order");
                listOrder = o.getAllOrder();
            } else {
                //if have order found will add to list
                listOrder.add(order);
            }
            request.setAttribute("listOrder", listOrder);
            request.setAttribute("valueSearch", pid_raw);
            request.getRequestDispatcher("OrderManager.jsp").forward(request, response);
        }

        if (service.equals("searchByStatus")) {
            String status = request.getParameter("status");
            Vector<Order> listOrder = o.getOrderByStatus(status);
            request.setAttribute("listOrder", listOrder);
            request.setAttribute("statusSearch", status);
            request.getRequestDispatcher("OrderManager.jsp").forward(request, response);
        }

        if (service.equals("updateStatus")) {
            String oId_raw = request.getParameter("oId");
            int oId = Integer.parseInt(oId_raw);
            String status = request.getParameter("status");

            boolean n = o.updateStatusOrder(status, oId);
            if (n) {
                request.setAttribute("mess", "update success");
            } else {
                request.setAttribute("mess", "update unsuccess");
            }
            response.sendRedirect("OrderManager");
        }

        if (service.equals("detailOrder")) {
            String orderId_raw = request.getParameter("OId");
            int orderId = Integer.parseInt(orderId_raw);
            DAOOrderDetail DOI = new DAOOrderDetail();
            
            Vector<Order> listOrder = new Vector<>();
            Vector<OrderDetail> orderDetails = DOI.getOrderDetail(orderId);
            Order order = o.getOrderById(orderId); // Lấy thông tin đơn hàng
            request.setAttribute("listOrder", listOrder);
            request.setAttribute("orderDetails", orderDetails);
            request.setAttribute("OrderId", orderId);
            request.setAttribute("order", order); // Đưa thông tin đơn hàng vào request
            request.getRequestDispatcher("OrderDetail.jsp").forward(request, response);
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
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("account");

        if (acc != null) {
            int roleId = acc.getRole_id();

            if (roleId == 1) { // Admin
                processRequest(request, response);
            } else if (roleId == 4) { // Sale
                processRequest(request, response);
            } else {
                response.sendRedirect("OrderManager"); // Redirect to ProductURL for other roles
            }
        } else {
            response.sendRedirect("login"); // Redirect to login if the user is not logged in
        }
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

    public static void main(String[] args) {
        DAOOrderDetail DOI = new DAOOrderDetail();
        Vector<OrderDetail> list = DOI.getOrderDetail(7);
        for (OrderDetail order : list) {
            System.out.println(order);
        }
    }
}
