/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author laptop368
 */
public class Order {
    private String o_id;
    private String date;
    private String c_id;
    private boolean status;

    public Order() {
    }

    public Order(String o_id, String date, String c_id, boolean status) {
        this.o_id = o_id;
        this.date = date;
        this.c_id = c_id;
        this.status = status;
    }

    public String getO_id() {
        return o_id;
    }

    public void setO_id(String o_id) {
        this.o_id = o_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getC_id() {
        return c_id;
    }

    public void setC_id(String c_id) {
        this.c_id = c_id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order{" + "o_id=" + o_id + ", date=" + date + ", c_id=" + c_id + ", status=" + status + '}';
    }
   
}
