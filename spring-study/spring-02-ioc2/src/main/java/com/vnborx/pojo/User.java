package com.vnborx.pojo;

public class User {
    private String name;

    public User () {
        System.out.println("No-args constructor of class User.");
    }

    public User (String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void show() {
        System.out.println("Name is " + name + ".");
    }
}
