/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.DAOOrder;
import dal.DAOOrderDetail;
import entity.Order;
import entity.OrderDetail;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;

/**
 *
 * @author HP
 */
@WebServlet(name="OrderManager", urlPatterns={"/OrderManager"})
public class OrderManagerServlet extends HttpServlet {
   
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
    
    // Create PrintWriter for logging
    PrintWriter out = response.getWriter();
    
    DAOOrder o = new DAOOrder();
    String service = request.getParameter("Service");

    // Logging the service parameter
    out.println("Service: " + service);

    if(service == null) {
        service = "getAll";
    }
    
    if(service.equals("getAll")) {
        Vector<Order> listOrder = o.getAllOrder();
        request.setAttribute("listOrder", listOrder);
        
        // Logging the number of orders retrieved
        out.println("Number of Orders: " + listOrder.size());
        
        request.getRequestDispatcher("OrderManager.jsp").forward(request, response);
    }

    if (service.equals("searchOrder")) {
        int oid = -1;
        String pid_raw = request.getParameter("searchId");
        try {
            oid = Integer.parseInt(pid_raw);
        } catch (NumberFormatException e) {
            oid = -1;
        }
        
        // Logging the search id
        out.println("Search Order ID: " + oid);
        
        Order order = o.getOrderById(oid);
        Vector<Order> listOrder = new Vector<>();
        if (order == null) {
            request.setAttribute("mess", "Not found this product");
            listOrder = o.getAllOrder();
        } else {
            listOrder.add(order);
        }
        
        // Logging the search result
        out.println("Orders found: " + listOrder.size());
        
        request.setAttribute("listOrder", listOrder);
        request.setAttribute("valueSearch", pid_raw);
        request.getRequestDispatcher("OrderManager.jsp").forward(request, response);
    }
    
    if(service.equals("searchByStatus")) {
        String status = request.getParameter("status");
        Vector<Order> listOrder = o.getOrderByStatus(status);
        request.setAttribute("listOrder", listOrder);
        request.setAttribute("statusSearch", status);
        
        // Logging the status and number of orders found
        out.println("Search Status: " + status);
        out.println("Orders found: " + listOrder.size());
        
        request.getRequestDispatcher("OrderManager.jsp").forward(request, response);
    }

    if (service.equals("updateStatus")) {
        String oId_raw = request.getParameter("oId");
        int oId = Integer.parseInt(oId_raw);
        String status = request.getParameter("status");
        
        // Logging the update request
        out.println("Update Order ID: " + oId + " to Status: " + status);
        
        boolean n = o.updateStatusOrder(status, oId);
        if (n) {
            request.setAttribute("mess", "update success");
            out.println("Update success");
        } else {
            request.setAttribute("mess", "update unsuccess");
            out.println("Update unsuccess");
        }
        response.sendRedirect("OrderManager");
    }
    
    if(service.equals("detailOrder")) {
        String orderId_raw = request.getParameter("OId");
        int orderId = Integer.parseInt(orderId_raw);
        DAOOrderDetail DOI = new DAOOrderDetail();
        Vector<OrderDetail> orderDetails = DOI.getOrderDetail(orderId);
        
        // Logging the order details request
        out.println("Order ID for Details: " + orderId);
        out.println("Number of Order Details: " + orderDetails.size());
        
        request.setAttribute("orderDetails", orderDetails);
        request.setAttribute("OrderId", orderId);
        request.getRequestDispatcher("OrderDetail.jsp").forward(request, response);
    }
    
    // Close the PrintWriter
    out.close();
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

    public static void main(String[] args) {
        DAOOrderDetail DOI = new DAOOrderDetail();
        Vector<OrderDetail> list = DOI.getOrderDetail(7);
        for (OrderDetail orderItem : list) {
            System.out.println(orderItem);
        }
    }
}
