package com.example.slathletecare.model;

import java.io.Serializable;

public class accountRegister implements Serializable {
    private String username;
    private String password;
    private String phone;
    private String gender;
    private String email;
    private String otp;

    public accountRegister(String username, String phone, String password, String gender,String email,String otp) {
        this.username = username;
        this.phone = phone;
        this.password = password;
        this.email=email;
        this.gender=gender;
        this.otp=otp;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



}
