/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.DAOBlog;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name="DeleteBlogServlet", urlPatterns={"/deleteBlog"})
public class DeleteBlogServlet extends HttpServlet {

    private DAOBlog daoBlog;

    @Override
    public void init() throws ServletException {
        daoBlog = new DAOBlog();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet DeleteBlogServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DeleteBlogServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get the blog ID from the request
        int blogId = Integer.parseInt(request.getParameter("id"));

        // Perform the delete operation
        daoBlog.deleteBlog(blogId);

        // Redirect back to the blog list page
        response.sendRedirect("blog?action=list");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet for deleting a blog";
    }
}
