/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author laptop368
 */
public class Order_Detail {
    private String detail_id;
    private String b_id;
    private int price;
    private int quantity;
    private String o_id;

    public Order_Detail() {
    }

    public Order_Detail(String detail_id, String b_id, int price, int quantity, String o_id) {
        this.detail_id = detail_id;
        this.b_id = b_id;
        this.price = price;
        this.quantity = quantity;
        this.o_id = o_id;
    }

    public String getDetail_id() {
        return detail_id;
    }

    public void setDetail_id(String detail_id) {
        this.detail_id = detail_id;
    }

    public String getB_id() {
        return b_id;
    }

    public void setB_id(String b_id) {
        this.b_id = b_id;
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

    public String getO_id() {
        return o_id;
    }

    public void setO_id(String o_id) {
        this.o_id = o_id;
    }

    @Override
    public String toString() {
        return "Order_Detail{" + "detail_id=" + detail_id + ", b_id=" + b_id + ", price=" + price + ", quantity=" + quantity + ", o_id=" + o_id + '}';
    }
    
}
