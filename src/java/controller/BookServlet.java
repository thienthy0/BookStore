package controller;

import dal.DAOBook;
import dal.DAOCategory;
import dal.DAOAuthor;
import entity.Book;
import entity.Category;
import entity.Author;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "BookServlet", urlPatterns = {"/BookURL"})
public class BookServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        DAOBook dao = new DAOBook();
        DAOCategory categoryDao = new DAOCategory();
        DAOAuthor authorDao = new DAOAuthor();

        List<Book> list;
        String[] categoryIds = request.getParameterValues("category_id");
        String[] authorIds = request.getParameterValues("author_id");

        if (categoryIds != null || authorIds != null) {
            List<Integer> categoryIdList = new ArrayList<>();
            List<Integer> authorIdList = new ArrayList<>();

            if (categoryIds != null) {
                for (String categoryId : categoryIds) {
                    categoryIdList.add(Integer.parseInt(categoryId));
                }
            }

            if (authorIds != null) {
                for (String authorId : authorIds) {
                    authorIdList.add(Integer.parseInt(authorId));
                }
            }

            list = dao.getBooksByCategoryAndAuthor(categoryIdList, authorIdList);
        } else {
            list = dao.getAllProduct();
        }

        List<Category> category = categoryDao.getCategory();
        List<Author> authors = authorDao.getAllAuthors();

        request.setAttribute("listBook", list);
        request.setAttribute("category", category);
        request.setAttribute("authors", authors);

        request.getRequestDispatcher("BookList.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(BookServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(BookServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
