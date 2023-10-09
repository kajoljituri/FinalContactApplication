package com.example.finalcontactapp;

public class Contact {
    private String name,nickname,circle,phoneNumber;
    long id;

    public Contact(long id) {
        this.id = id;
    }

    public Contact() {
    }

    public Contact(String name, String nickname, String circle, String phoneNumber) {
        this.name = name;
        this.nickname = nickname;
        this.circle = circle;
        this.phoneNumber = phoneNumber;
    }
    public long getId() {

        return id;
    }

    public void setId(long id)
    {
        this.id = id;
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

    public String getPhoneNumber() {

        return phoneNumber;
    }

    public void setPhoneNumber(String  phoneNumber)
    {

        this.phoneNumber = phoneNumber;
    }

}
