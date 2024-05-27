package entity;

public class Employee {
    private int e_id ;
    private String e_name;
    private int position;
    private String e_email;
    private String e_phone;
    private String e_address;
    private boolean gender;
   
    private String DOB;

    public Employee() {
    }

    public Employee(int e_id, String e_name, int position, String e_email, String e_phone, String e_address, boolean gender, String DOB) {
        this.e_id = e_id;
        this.e_name = e_name;
        this.position = position;
        this.e_email = e_email;
        this.e_phone = e_phone;
        this.e_address = e_address;
        this.gender = gender;
        
        this.DOB = DOB;
    }

    public int getE_id() {
        return e_id;
    }

    public void setE_id(int e_id) {
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

    public String getE_phone() {
        return e_phone;
    }

    public void setE_phone(String e_phone) {
        this.e_phone = e_phone;
    }

    public String getE_address() {
        return e_address;
    }

    public void setE_address(String e_address) {
        this.e_address = e_address;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }


    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    @Override
    public String toString() {
        return "Employee{" + "e_id=" + e_id + ", e_name=" + e_name + ", position=" + position + ", e_email=" + e_email + ", e_phone=" + e_phone + ", e_address=" + e_address + ", gender=" + gender + ", DOB=" + DOB + '}';
    }

   
}
