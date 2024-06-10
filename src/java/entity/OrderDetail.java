/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author laptop368
 */
public class OrderDetail {
    private String detail_id;
    private String book_id;
    private String o_id;
    private int price;
    private int quantity;
    private int discount;

    public OrderDetail(String string, String string1, String string2, int aInt, int aInt1, int aInt2, Book Pro) {
    }

    public OrderDetail(String detail_id, String book_id, String o_id, int price, int quantity, int discount) {
        this.detail_id = detail_id;
        this.book_id = book_id;
        this.o_id = o_id;
        this.price = price;
        this.quantity = quantity;
        this.discount = discount;
    }

    public String getDetail_id() {
        return detail_id;
    }

    public void setDetail_id(String detail_id) {
        this.detail_id = detail_id;
    }

    public String getBook_id() {
        return book_id;
    }

    public void setBook_id(String book_id) {
        this.book_id = book_id;
    }

    public String getO_id() {
        return o_id;
    }

    public void setO_id(String o_id) {
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

    @Override
    public String toString() {
        return "Order_Detail{" + "detail_id=" + detail_id + ", book_id=" + book_id + ", o_id=" + o_id + ", price=" + price + ", quantity=" + quantity + ", discount=" + discount + '}';
    }

    
}
