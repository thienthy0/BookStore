package entity;

import java.util.Date;

/**
 *
 * @author HP
 */
public class Account {
    private int account_id,role_id;
    private String first_name, last_name, phone, email, password, account_image, address,description,DateOFBirth;
    private Boolean gender;

    public Account() {
    }

    public Account(int account_id, int role_id, String first_name, String last_name, String phone, String email, String password, String account_image, String address, String description, String DateOFBirth, Boolean gender) {
        this.account_id = account_id;
        this.role_id = role_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.account_image = account_image;
        this.address = address;
        this.description = description;
        this.DateOFBirth = DateOFBirth;
        this.gender = gender;
    }

    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccount_image() {
        return account_image;
    }

    public void setAccount_image(String account_image) {
        this.account_image = account_image;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateOFBirth() {
        return DateOFBirth;
    }

    public void setDateOFBirth(String DateOFBirth) {
        this.DateOFBirth = DateOFBirth;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Account{" + "account_id=" + account_id + ", role_id=" + role_id + ", first_name=" + first_name + ", last_name=" + last_name + ", phone=" + phone + ", email=" + email + ", password=" + password + ", account_image=" + account_image + ", address=" + address + ", description=" + description + ", DateOFBirth=" + DateOFBirth + ", gender=" + gender + '}';
    }

}
