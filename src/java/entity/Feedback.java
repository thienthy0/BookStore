/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author laptop368
 */
public class Feedback {
    private String f_id;
    private String b_id;
    private String c_id;
    private String comment;
    private int rating;

    public Feedback() {
    }

    public Feedback(String f_id, String b_id, String c_id, String comment, int rating) {
        this.f_id = f_id;
        this.b_id = b_id;
        this.c_id = c_id;
        this.comment = comment;
        this.rating = rating;
    }

    public String getF_id() {
        return f_id;
    }

    public void setF_id(String f_id) {
        this.f_id = f_id;
    }

    public String getB_id() {
        return b_id;
    }

    public void setB_id(String b_id) {
        this.b_id = b_id;
    }

    public String getC_id() {
        return c_id;
    }

    public void setC_id(String c_id) {
        this.c_id = c_id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Feedback{" + "f_id=" + f_id + ", b_id=" + b_id + ", c_id=" + c_id + ", comment=" + comment + ", rating=" + rating + '}';
    }
    
}
