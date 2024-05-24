/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author laptop368
 */
public class Employee {
    private String e_id;
    private String e_name;
    private int position;
    private String e_email;
    private int e_phone;
    private String adress;
    private boolean gender;
    private String b_id;
    private String dob;

    public Employee() {
    }

    public Employee(String e_id, String e_name, int position, String e_email, int e_phone, String adress, boolean gender, String b_id, String dob) {
        this.e_id = e_id;
        this.e_name = e_name;
        this.position = position;
        this.e_email = e_email;
        this.e_phone = e_phone;
        this.adress = adress;
        this.gender = gender;
        this.b_id = b_id;
        this.dob = dob;
    }

    public String getE_id() {
        return e_id;
    }

    public void setE_id(String e_id) {
        this.e_id = e_id;
    }

    public String getE_name() {
        return e_name;
    }

    public void setE_name(String e_name) {
        this.e_name = e_name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getE_email() {
        return e_email;
    }

    public void setE_email(String e_email) {
        this.e_email = e_email;
    }

    public int getE_phone() {
        return e_phone;
    }

    public void setE_phone(int e_phone) {
        this.e_phone = e_phone;
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

    public String getB_id() {
        return b_id;
    }

    public void setB_id(String b_id) {
        this.b_id = b_id;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    @Override
    public String toString() {
        return "Employee{" + "e_id=" + e_id + ", e_name=" + e_name + ", position=" + position + ", e_email=" + e_email + ", e_phone=" + e_phone + ", adress=" + adress + ", gender=" + gender + ", b_id=" + b_id + ", dob=" + dob + '}';
    }
    
}
