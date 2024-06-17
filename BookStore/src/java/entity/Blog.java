/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.Date;

/**
 *
 * @author admin
 */
public class Blog {

    private int blog_id;
    private String image;
    private String title;
    private String content;
    private Date created_at;
    private int author_id;

    public Blog() {
    }

    public Blog(int blog_id, String image, String title, String content, Date created_at, int author_id) {
        this.blog_id = blog_id;
        this.image = image;
        this.title = title;
        this.content = content;
        this.created_at = created_at;
        this.author_id = author_id;
    }

    public int getBlog_id() {
        return blog_id;
    }

    public void setBlog_id(int blog_id) {
        this.blog_id = blog_id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    @Override
    public String toString() {
        return "Blog{" + "blog_id=" + blog_id + ", image=" + image + ", title=" + title + ", content=" + content + ", created_at=" + created_at + ", author_id=" + author_id + '}';
    }

}
