package com.example.finalcontactapp;

public class Contact {

    String name,nickname,circle;
    int phoneNumber;

    public Contact() {
    }

    public Contact(String name, String nickname, String circle, int phoneNumber) {
        this.name = name;
        this.nickname = nickname;
        this.circle = circle;
        this.phoneNumber = phoneNumber;

    }


    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getnickname() {
        return nickname;
    }

    public void setnickname(String nickname) {
        this.nickname = nickname;
    }

    public String getCircle() {
        return circle;
    }

    public void setCircle(String circle) {
        this.circle = circle;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
