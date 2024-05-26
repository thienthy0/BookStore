/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;


import entity.Customer;
import entity.Employee;
import static java.lang.Math.random;
import static java.lang.StrictMath.random;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.SecureRandom;

/**
 *
 * @author admin
 */
public class DAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    // Set eOTP = NULL after 5'
//    public void scheduleOTPCleanup(final String email, final String otp) {
//        Timer timer = new Timer();
//        EmployeeDAO eDAO = new EmployeeDAO();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                eDAO.updateOTPinDatabase(email, null);
//                System.out.println("OTP cleaned up for email: " + email);
//            }
//        }, 5 * 60 * 1000); // 5 phút
//    }

    //Get random OTP
    public String generateOTP() {
        int otpLength = 8;
        String characters = "0123456789";
        StringBuilder otp = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < otpLength; i++) {
            char randomChar = characters.charAt(random.nextInt(characters.length()));
            otp.append(randomChar);
        }
        return otp.toString();
    }

    //Send OTP Employee
    public void sendOTPEmail(String toEmail, String otp) {
        final String fromEmail = "g2baking@gmail.com";
        final String password = "oujk khnc aimq ebgn";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject("Password Reset OTP");
            message.setText("Your OTP code is: " + otp + ". It will expired after 5 minutes.");

            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public String generatePassword() {
        String uppercaseChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String specialChars = "!@#$%^&*()-_+=<>?";
        String digits = "0123456789";
        SecureRandom random = new SecureRandom();
        
        StringBuilder password = new StringBuilder();
        
        // Thêm một chữ hoa ngẫu nhiên vào mật khẩu
        password.append(uppercaseChars.charAt(random.nextInt(uppercaseChars.length())));
        
        // Thêm một ký tự đặc biệt ngẫu nhiên vào mật khẩu
        password.append(specialChars.charAt(random.nextInt(specialChars.length())));
        
        // Thêm một số ngẫu nhiên vào mật khẩu
        password.append(digits.charAt(random.nextInt(digits.length())));
        
        // Tạo các ký tự còn lại cho mật khẩu
        for (int i = 3; i < 6; i++) {
            String allChars = uppercaseChars + specialChars + digits;
            password.append(allChars.charAt(random.nextInt(allChars.length())));
        }
        return password.toString();
    }
    
    //Send password
    public void sendPassword(String toEmail, String pass) {
        final String fromEmail = "g2baking@gmail.com";
        final String password = "oujk khnc aimq ebgn";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject("Password to login in");
            message.setText("Your password is: " + pass + ".");

            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        //String password = generatePassword();
        //System.out.println("Mật khẩu mới: " + password);
    }
}
