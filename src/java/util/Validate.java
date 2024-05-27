/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.security.MessageDigest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.tomcat.util.codec.binary.Base64;

/**
 *
 * @author msii
 */
public class Validate {

    // md5
    // sha-1 => thường được sử dụng
    public static String toSHA1(String str) {
        String salt = "asjrlkmcoewj@tjle;oxqskjhdjksjf1jurVn";// Làm cho mật khẩu phức tap
        String result = null;

        str = str + salt;
        try {
            byte[] dataBytes = str.getBytes("UTF-8");
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            result = Base64.encodeBase64String(md.digest(dataBytes));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    //check fomat email
    public static boolean isValidEmail(String email) {
        String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        // Compile regular expression
        Pattern pattern = Pattern.compile(EMAIL_REGEX);

        // If the email is null, return false
        if (email == null) {
            return false;
        }

        // Match given string with compiled pattern
        Matcher matcher = pattern.matcher(email);

        // Return true if the email matches the pattern, otherwise return false
        return matcher.matches();
    }

    //check fomat phone
    public static boolean isValidPhoneNumber(String phoneNumber) {
        String regex = "^\\+?(84|0)(1[2689]|8[23456789]|9[0123456789])[0-9]{7}$";
        return phoneNumber.matches(regex);
    }

    public static String checkOrderStatus(int status) {
        String mess = "";
        switch (status) {
            case 0:
                mess = "Hủy";
                break;
            case 1:
                mess = "Chờ xác nhận";
                break;
            case 2:
                mess = "Chờ lấy hàng";
                break;
            case 3:
                mess = "Đang giao";
                break;
            case 4:
                mess = "Thành công";
                break;
            case 5:
                mess = "Trả hàng & Hoàn tiền";
                break;
            case 6:
                mess = "Trả hàng & Hoàn tiền";
                break;
            default:
                mess = "";
                break;
        }
        return mess;
    }

    public static String checkStockType(boolean type) {
        String mess = "";
        if (type) {
            mess = "Nhập";
        }
        if (!type) {
            mess = "Xuất";
        }
        return mess;
    }

//    public static void main(String[] args) {
//        UserDAO dao = new UserDAO();  
//        for(User u : dao.getAllAccount()){
//            System.out.println("Pass: "+u.getPassword()+" -> "+toSHA1(u.getPassword()));
//        }
//
//    }
}
