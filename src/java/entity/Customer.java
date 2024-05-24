/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author laptop368
 */
public class Customer {
     private String c_id;
    private String c_name;   
    private String c_email;
    private int c_phone;
    private String adress;
    private boolean gender;   
    private String dob;

    public Customer() {
    }

    public Customer(String c_id, String c_name, String c_email, int c_phone, String adress, boolean gender, String dob) {
        this.c_id = c_id;
        this.c_name = c_name;
        this.c_email = c_email;
        this.c_phone = c_phone;
        this.adress = adress;
        this.gender = gender;
        this.dob = dob;
    }

    public String getC_id() {
        return c_id;
    }

    public void setC_id(String c_id) {
        this.c_id = c_id;
    }

    public String getC_name() {
        return c_name;
    }

    public void setC_name(String c_name) {
        this.c_name = c_name;
    }

    public String getC_email() {
        return c_email;
    }

    public void setC_email(String c_email) {
        this.c_email = c_email;
    }

    public int getC_phone() {
        return c_phone;
    }

    public void setC_phone(int c_phone) {
        this.c_phone = c_phone;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    @Override
    public String toString() {
        return "Customer{" + "c_id=" + c_id + ", c_name=" + c_name + ", c_email=" + c_email + ", c_phone=" + c_phone + ", adress=" + adress + ", gender=" + gender + ", dob=" + dob + '}';
    }
    
}
