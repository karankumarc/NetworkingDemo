package com.techpalle.karan.networkingdemo.model;

/**
 * Created by ADMIN on 9/24/2016.
 */
public class Contact {
    private String name, email, phone;

    public Contact(String phone, String email, String name) {
        this.phone = phone;
        this.email = email;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }
}
