package controller;

import dal.DAOCategory;
import dal.DAOBook;
import entity.Account;
import entity.Author;
import entity.Category;
import entity.Book;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.nio.file.Paths;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "ManageController", urlPatterns = {"/ManageProduct"})
@MultipartConfig
public class ManageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html;charset=UTF-8");
        DAOBook daoBook = new DAOBook();
        String service = request.getParameter("Service");
        if (service == null) {
            service = "getAll";
        }
        try {
            switch (service) {
                case "getAll":
                    handleGetAllProducts(request, response, daoBook);
                    break;
                case "SearchProduct":
                    handleSearchProduct(request, response, daoBook);
                    break;
                case "addProduct":
                    handleAddProduct(request, response, daoBook);
                    break;
                case "Delete":
                    handleDeleteProduct(request, response, daoBook);
                    break;
                case "Update":
                    handleUpdateProduct(request, response, daoBook);
                    break;
                default:
                    handleGetAllProducts(request, response, daoBook);
                    break;
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Consider using a logging framework
            throw new ServletException(e);
        }
    }

    private void handleGetAllProducts(HttpServletRequest request, HttpServletResponse response, DAOBook daoBook) throws SQLException, ServletException, IOException {
        List<Book> list = daoBook.getAllProduct();
        request.setAttribute("listProduct", list);
        System.out.println(list);
        request.getRequestDispatcher("Admin.jsp").forward(request, response);
    }

    private void handleSearchProduct(HttpServletRequest request, HttpServletResponse response, DAOBook daoBook) throws SQLException, ServletException, IOException {
        int pid = -1;
        String pidRaw = request.getParameter("searchId");
        try {
            pid = Integer.parseInt(pidRaw);
        } catch (Exception e) {
            pid = -1;
        }
        Book product = daoBook.getProductById(pid);
        Vector<Book> list = new Vector<>();
        if (product == null) {
            request.setAttribute("mess", "Not found this book");
            list = (Vector<Book>) daoBook.getAllProduct();
        } else {
            list.add(product);
        }
        request.setAttribute("listProduct", list);
        request.setAttribute("valueSearch", pidRaw);
        request.getRequestDispatcher("Admin.jsp").forward(request, response);
    }

    private void handleAddProduct(HttpServletRequest request, HttpServletResponse response, DAOBook daoBook) throws SQLException, ServletException, IOException {
        setCommonAttributes(request);
        String addBtn = request.getParameter("add");
        if (addBtn == null) {
            request.getRequestDispatcher("InsertProduct.jsp").forward(request, response);
        } else {
            String proName = request.getParameter("productName");
            String proImage = request.getParameter("productImage");
            if (proImage == null || proImage.isEmpty()) {
                proImage = request.getParameter("beforeImage");
            }
            String applicationPath = request.getServletContext().getRealPath("");
            String uploadFilePath = applicationPath+ File.separator + "images";

            // Creates the directory if it does not exist.
            File uploadDir = new File(uploadFilePath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }
            Part part = request.getPart("file");
            String fileName="";
            if(part != null){
                fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
                part.write(uploadFilePath + File.separator + fileName);
            }
            String categoryIdRaw = request.getParameter("category");
            String authorRaw = request.getParameter("author");
            String proPriceRaw = request.getParameter("pro_price");
            String productLanguage = request.getParameter("language");
            String publisher = request.getParameter("publisher");
            String quantityRaw = request.getParameter("quantity");
            String numberOfPageRaw = request.getParameter("numberPage");

            int numberOfPage = Integer.parseInt(numberOfPageRaw);
//            int authorId = Integer.parseInt(authorRaw);
            int quantity = Integer.parseInt(quantityRaw);
            int proPrice = Integer.parseInt(proPriceRaw);

            int prId = daoBook.getAllProduct().get(daoBook.getAllProduct().size() - 1).getId() + 1;
//            Book product = new Book(proName, prId, quantity, prId, addBtn, proImage, proImage, catId, proImage, prId);
            Book product = new Book(proName, prId, quantity, proPrice, authorRaw, fileName, productLanguage, categoryIdRaw, publisher, numberOfPage);
            daoBook.addProduct(product);
            response.sendRedirect("ManageProduct");
        }
    }

    private void handleDeleteProduct(HttpServletRequest request, HttpServletResponse response, DAOBook daoBook) throws SQLException, IOException {
        String pidRaw = request.getParameter("pid");
        int pid = Integer.parseInt(pidRaw);
        daoBook.deleteProduct(pid);
        response.sendRedirect("ManageProduct");
    }

    private void handleUpdateProduct(HttpServletRequest request, HttpServletResponse response, DAOBook daoBook) throws SQLException, ServletException, IOException {
        String submit = request.getParameter("Submit");
        if (submit != null) {
            String proIdRaw = request.getParameter("productId");
            String proName = request.getParameter("productName");
            String proImage = request.getParameter("productImage");
            if (proImage == null || proImage.isEmpty()) {
                proImage = request.getParameter("beforeImage");
            }
            String applicationPath = request.getServletContext().getRealPath("");
            String uploadFilePath = applicationPath+ File.separator + "images";

            // Creates the directory if it does not exist.
            File uploadDir = new File(uploadFilePath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }
            Part part = request.getPart("file");
            String fileName="";
            if(part != null){
                fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
                part.write(uploadFilePath + File.separator + fileName);
            }
            String categoryIdRaw = request.getParameter("category");
            String authorRaw = request.getParameter("author");
            String proPriceRaw = request.getParameter("pro_price");
            String productLanguage = request.getParameter("language");
            String publisher = request.getParameter("publisher");
            String quantityRaw = request.getParameter("quantity");
            String numberOfPageRaw = request.getParameter("numberPage");

            int numberOfPage = Integer.parseInt(numberOfPageRaw);
//            int authorId = Integer.parseInt(authorRaw);
            int quantity = Integer.parseInt(quantityRaw);
            int proPrice = Integer.parseInt(proPriceRaw);

            int prId = Integer.parseInt(proIdRaw);
//            Book product = new Book(proName, prId, quantity, prId, addBtn, proImage, proImage, catId, proImage, prId);
            Book product = new Book(proName, prId, quantity, proPrice, authorRaw, fileName, productLanguage, categoryIdRaw, publisher, numberOfPage);
            daoBook.updateProduct(product);
            response.sendRedirect("ManageProduct");
        } else {
            String pidRaw = request.getParameter("pid");
            DAOCategory daoC = new DAOCategory();
            int pid = Integer.parseInt(pidRaw);
            Book product = daoBook.getProductById(pid);
            List<Category> listCategory = daoC.getAllCategory();
            request.setAttribute("product", product);
            request.setAttribute("listCategory", listCategory);
            setCommonAttributes(request);
            request.getRequestDispatcher("UpdateProduct.jsp").forward(request, response);
        }
    }

    private void setCommonAttributes(HttpServletRequest request) throws SQLException {
        DAOCategory daoCategory = new DAOCategory();
//        List<Brand> listBrand = daoBrand.getAllBrand();
        List<Category> listCategory = daoCategory.getCategory();
        int[] listDiscount = {1, 10, 20, 30, 40};
        request.setAttribute("listCategory", listCategory);
        request.setAttribute("listDiscount", listDiscount);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("account");
        if (acc != null && acc.getIs_admin()) {
            processRequest(request, response);
        } else {
            response.sendRedirect(acc == null ? "login" : "ProductURL");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        DAOBook daoBook = new DAOBook();
        String service = request.getParameter("Service");
        if (service == null) {
            service = "getAll";
        }
        try {
            switch (service) {
                case "getAll":
                    handleGetAllProducts(request, response, daoBook);
                    break;
                case "SearchProduct":
                    handleSearchProduct(request, response, daoBook);
                    break;
                case "addProduct":
                    handleAddProduct(request, response, daoBook);
                    break;
                case "Delete":
                    handleDeleteProduct(request, response, daoBook);
                    break;
                case "Update":
                    handleUpdateProduct(request, response, daoBook);
                    break;
                default:
                    handleGetAllProducts(request, response, daoBook);
                    break;
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Consider using a logging framework
            throw new ServletException(e);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

    public static void main(String[] args) throws SQLException {
        // Main method can be used for testing purposes
    }
}
