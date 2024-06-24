/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.sql.Date;
import java.util.Vector;

/**
 *
 * @author laptop368
 */
public class Order {

    private int o_id, account_id;
    private String status;
    private Date order_date, receive_date;
    private Vector<OrderDetail> listOrder;
    private Account acc;
    

    

    public Order(int o_id, int account_id, String status, Date order_date, Date receive_date) {
        this.o_id = o_id;
        this.account_id = account_id;
        this.status = status;
        this.order_date = order_date;
        this.receive_date = receive_date;
    }

    public Order(int o_id, String status, Date order_date, Date receive_date, Vector<OrderDetail> listOrder, Account acc) {
        this.o_id = o_id;
        this.status = status;
        this.order_date = order_date;
        this.receive_date = receive_date;
        this.listOrder = listOrder;
        this.acc = acc;
    }

    

    public int getO_id() {
        return o_id;
    }

    public void setO_id(int o_id) {
        this.o_id = o_id;
    }

    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getAccountFullName() {
        return acc.getFirst_name() + " " + acc.getLast_name();
    }

    public Double totalOrder() {
        double total = 0;
        for (OrderDetail orderItem : listOrder) {
            total += orderItem.orderItemPrice();
        }
        return total;
    }

    public String getOrderTotal() {
        String formattedPrice = String.format("%,.0f", totalOrder());
        formattedPrice = formattedPrice.replace(",", ".");
        return formattedPrice;
    }

    public String getAccountImg() {
        return acc.getAccount_image();
    }

    @Override
    public String toString() {
        return "Order{" + "o_id=" + o_id + ", account_id=" + account_id + ", status=" + status + ", order_date=" + order_date + ", receive_date=" + receive_date + '}';
    }

}
