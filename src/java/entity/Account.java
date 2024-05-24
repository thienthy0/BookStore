/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author laptop368
 */
public class Account {
    private String a_id;
    private String usename;
    private String password;
    private int position;

    public Account() {
    }

    public Account(String a_id, String usename, String password, int position) {
        this.a_id = a_id;
        this.usename = usename;
        this.password = password;
        this.position = position;
    }

    public String getA_id() {
        return a_id;
    }

    public void setA_id(String a_id) {
        this.a_id = a_id;
    }

    public String getUsename() {
        return usename;
    }

    public void setUsename(String usename) {
        this.usename = usename;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Account{" + "a_id=" + a_id + ", usename=" + usename + ", password=" + password + ", position=" + position + '}';
    }
    
}
