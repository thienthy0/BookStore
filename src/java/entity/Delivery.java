/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author laptop368
 */
public class Delivery {
    private String d_id;
    private String o_id;
    private String c_name;
    private String location;
    private String date;
    private boolean time;

    public Delivery() {
    }

    public Delivery(String d_id, String o_id, String c_name, String location, String date, boolean time) {
        this.d_id = d_id;
        this.o_id = o_id;
        this.c_name = c_name;
        this.location = location;
        this.date = date;
        this.time = time;
    }

    public String getD_id() {
        return d_id;
    }

    public void setD_id(String d_id) {
        this.d_id = d_id;
    }

    public String getO_id() {
        return o_id;
    }

    public void setO_id(String o_id) {
        this.o_id = o_id;
    }

    public String getC_name() {
        return c_name;
    }

    public void setC_name(String c_name) {
        this.c_name = c_name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isTime() {
        return time;
    }

    public void setTime(boolean time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Delivery{" + "d_id=" + d_id + ", o_id=" + o_id + ", c_name=" + c_name + ", location=" + location + ", date=" + date + ", time=" + time + '}';
    }
    
}
