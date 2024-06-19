/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.sql.Date;

/**
 *
 * @author laptop368
 */
public class OrderDetail {

    private int detail_id;
    private int book_id;
    private int o_id;
    private int price;
    private int quantity;
    private int discount;
    private Order order;
    private Book book;
    private Date order_date;
    private Date receive_date;

    public OrderDetail() {
    }

    public OrderDetail( int book_id, int o_id, int price, int quantity, int discount, Book book) {
        this.book_id = book_id;
        this.o_id = o_id;
        this.price = price;
        this.quantity = quantity;
        this.discount = discount;
        this.book = book;
    }

    public OrderDetail(int detail_id, int o_id, int book_id, int discount, int quantity, int price) {
        this.detail_id = detail_id;
        this.book_id = book_id;
        this.o_id = o_id;
        this.price = price;
        this.quantity = quantity;
        this.discount = discount;
    }

    public OrderDetail(int detail_id, int discount, int price, int quantity, Book book) {
        this.detail_id = detail_id;
        this.discount = discount;
        this.price = price;
        this.quantity = quantity;
        this.book = book;
    }

    public OrderDetail(int detail_id, int book_id, int o_id, int price, int quantity, int discount, Order order, Book book, Date order_date, Date receive_date) {
        this.detail_id = detail_id;
        this.book_id = book_id;
        this.o_id = o_id;
        this.price = price;
        this.quantity = quantity;
        this.discount = discount;
        this.order = order;
        this.book = book;
        this.order_date = order_date;
        this.receive_date = receive_date;
    }

    public Date getOrder_date() {
        return order_date;
    }

    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
    }

    public Date getReceive_date() {
        return receive_date;
    }

    public void setReceive_date(Date receive_date) {
        this.receive_date = receive_date;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getDetail_id() {
        return detail_id;
    }

    public void setDetail_id(int detail_id) {
        this.detail_id = detail_id;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public int getO_id() {
        return o_id;
    }

    public void setO_id(int o_id) {
        this.o_id = o_id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    
    public Double orderItemPrice() {
        return price * (1 - (discount * 0.01)) * quantity;
    }

    public String getorderItemPrice() {
        String formattedPrice = String.format("%,.0f", orderItemPrice());
        formattedPrice = formattedPrice.replace(",", ".");
        return formattedPrice;
    }

    @Override
    public String toString() {
        return "OrderDetail{" + "detail_id=" + detail_id + ", book_id=" + book_id + ", o_id=" + o_id + ", price=" + price + ", quantity=" + quantity + ", discount=" + discount + ", order=" + order + ", book=" + book + ", order_date=" + order_date + ", receive_date=" + receive_date + '}';
    }



}
