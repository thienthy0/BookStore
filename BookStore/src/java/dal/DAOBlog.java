package dao;

import dal.DBConnect;
import entity.Blog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOBlog extends DBConnect {



    public Blog selectBlog(int id) {
        Blog blog = null;
        String sql = "SELECT * FROM Blog WHERE blog_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                String title = rs.getString("title");
                String content = rs.getString("content");
                java.sql.Date createdAt = rs.getDate("created_at");
                int authorId = rs.getInt("author_id");
                String image = rs.getString("image");
                blog = new Blog(id, image, title, content, createdAt, authorId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return blog;
    }

    public List<Blog> selectAllBlogs() {
        List<Blog> blogs = new ArrayList<>();
        String sql = "SELECT * FROM Blog";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("blog_id");
                String title = rs.getString("title");
                String content = rs.getString("content");
                java.sql.Date createdAt = rs.getDate("created_at");
                int authorId = rs.getInt("author_id");
                String image = rs.getString("image");
                blogs.add(new Blog(id, image, title, content, createdAt, authorId));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return blogs;
    }

    public boolean deleteBlog(int id) {
        String sql = "DELETE FROM Blog WHERE blog_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateBlog(Blog blog) {
        String sql = "UPDATE Blog SET title = ?, content = ?, created_at = ?, author_id = ?, image = ?  WHERE blog_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, blog.getTitle());
            preparedStatement.setString(2, blog.getContent());
            preparedStatement.setDate(3, new java.sql.Date(blog.getCreated_at().getTime()));
            preparedStatement.setInt(4, blog.getAuthor_id());
            preparedStatement.setInt(5, blog.getBlog_id());
             preparedStatement.setString(6, blog.getImage());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
     public void insertBlog(Blog blog) {
        String sql = "INSERT INTO Blog (blog_id, title, content, created_at, author_id, image) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, blog.getBlog_id());
            preparedStatement.setString(2, blog.getTitle());
            preparedStatement.setString(3, blog.getContent());
            preparedStatement.setDate(4, new java.sql.Date(blog.getCreated_at().getTime()));
            preparedStatement.setInt(5, blog.getAuthor_id());
            preparedStatement.setString(6, blog.getImage());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getNextBlogId() {
        String sql = "SELECT MAX(blog_id) AS max_id FROM Blog";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return rs.getInt("max_id") + 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 1; // If there are no records, return 1 as the initial blog_id
    }
}
    