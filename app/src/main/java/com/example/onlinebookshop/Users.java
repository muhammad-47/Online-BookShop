package com.example.onlinebookshop;

public class Users {
    String name, Email,Address,Phone;

    public Users() {

    }

    public Users(String name, String email,  String address, String phone) {
        this.name = name;
        this.Email = email;
       this. Address = address;
      this.  Phone = phone;
    }


    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }


}
