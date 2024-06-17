package controller;

import dao.DAOBlog;
import entity.Blog;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "AddBlogServlet", urlPatterns = {"/addBlog"})
public class AddBlogServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(AddBlogServlet.class.getName());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String authorIdStr = request.getParameter("author_id");
        String image = request.getParameter("image");

        if (title == null || title.isEmpty() || content == null || content.isEmpty() || image == null || image.isEmpty()) {
        request.setAttribute("error", "Title, content, and image are required.");
        request.setAttribute("title", title);
        request.setAttribute("content", content);
        request.setAttribute("author_id", authorIdStr);
        request.getRequestDispatcher("addBlog.jsp").forward(request, response);
        return;
    }

        int authorId = 0;
        if (authorIdStr != null && !authorIdStr.isEmpty()) {
            try {
                authorId = Integer.parseInt(authorIdStr);
            } catch (NumberFormatException e) {
                request.setAttribute("error", "Invalid author ID");
                request.setAttribute("title", title);
                request.setAttribute("content", content);
                request.setAttribute("author_id", authorIdStr);
                request.getRequestDispatcher("addBlog.jsp").forward(request, response);
                return;
            }
        }

        Date createdAt = new Date();
        try {
            createdAt = new SimpleDateFormat("yyyy-MM-dd").parse(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        } catch (ParseException e) {
            request.setAttribute("error", "Error parsing date");
            request.setAttribute("title", title);
            request.setAttribute("content", content);
            request.setAttribute("author_id", authorIdStr);
            request.getRequestDispatcher("addBlog.jsp").forward(request, response);
            return;
        }

        DAOBlog daoBlog = new DAOBlog();
        int blogId = daoBlog.getNextBlogId();

        Blog blog = new Blog(blogId, image, title, content, createdAt, authorId);
        try {
            daoBlog.insertBlog(blog);
            request.setAttribute("message", "Blog added successfully!");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error adding blog", e);
            request.setAttribute("error", "Error adding blog: " + e.getMessage());
            request.setAttribute("title", title);
            request.setAttribute("content", content);
            request.setAttribute("author_id", authorIdStr);
        }

        request.setAttribute("blog", blog);
        request.getRequestDispatcher("addBlog.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("addBlog.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "AddBlogServlet handles the creation of a new blog";
    }
}
