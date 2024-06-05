/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author laptop368
 */
public class Book {
    private String name;
    private int id;
    private int quantity;
    private int price;
    private String author;
    private String image;
    private String language;
    private String category;
    private String publisher;
    private int num_of_page;

    public Book() {
    }

    public Book(String name, int id, int quantity, int price, String author, String image, String language, String category, String publisher, int num_of_page) {
        this.name = name;
        this.id = id;
        this.quantity = quantity;
        this.price = price;
        this.author = author;
        this.image = image;
        this.language = language;
        this.category = category;
        this.publisher = publisher;
        this.num_of_page = num_of_page;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getNum_of_page() {
        return num_of_page;
    }

    public void setNum_of_page(int num_of_page) {
        this.num_of_page = num_of_page;
    }

    @Override
    public String toString() {
        return "Book{" + "name=" + name + ", id=" + id + ", quantity=" + quantity + ", price=" + price + ", author=" + author + ", image=" + image + ", language=" + language + ", category=" + category + ", publisher=" + publisher + ", num_of_page=" + num_of_page + '}';
    }
    


   
    
}