package com.sumit.mycrud.model;

import android.widget.EditText;

import java.io.Serializable;

public class Employee implements Serializable {
    private int emp_id;
    private String emp_name, emp_dob, emp_gender,emp_pass, emp_status,emp_mob_no,emp_address;
    private double emp_sal,emp_bonus;

    public String getEmp_status() {
        return emp_status;
    }

    public String getEmp_mob_no() {
        return emp_mob_no;
    }

    public void setEmp_mob_no(String emp_mob_no) {
        this.emp_mob_no = emp_mob_no;
    }

    public String getEmp_address() {
        return emp_address;
    }

    public void setEmp_address(String emp_address) {
        this.emp_address = emp_address;
    }

    public double getEmp_bonus() {
        return emp_bonus;
    }

    public void setEmp_bonus(double emp_bonus) {
        this.emp_bonus = emp_bonus;
    }

    public void setEmp_status(String emp_status) {
        this.emp_status = emp_status;
    }

    public int getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(int emp_id) {
        this.emp_id = emp_id;
    }

    public String getEmp_name() {
        return emp_name;
    }

    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }

    public String getEmp_dob() {
        return emp_dob;
    }

    public void setEmp_dob(String emp_dob) {
        this.emp_dob = emp_dob;
    }

    public String getEmp_gender() {
        return emp_gender;
    }

    public void setEmp_gender(String emp_gender) {
        this.emp_gender = emp_gender;
    }

    public String getEmp_password() {
        return emp_pass;
    }

    public void setEmp_password(String emp_password) {
        this.emp_pass = emp_password;
    }

    public double getSalary() {
        return emp_sal;
    }

    public void setSalary(Double salary) {
        this.emp_sal = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "emp_id=" + emp_id +
                ", emp_name='" + emp_name + '\'' +
                ", emp_dob='" + emp_dob + '\'' +
                ", emp_gender='" + emp_gender + '\'' +
                ", emp_password='" + emp_pass + '\'' +
                ", salary=" + emp_sal +
                '}';
    }
}
