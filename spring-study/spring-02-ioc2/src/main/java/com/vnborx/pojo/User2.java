package com.vnborx.pojo;

public class User2 {
    private String name;

    public User2 () {
        System.out.println("No-args constructor of class User2.");
    }

    public User2 (String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void show() {
        System.out.println("User2's name is " + name + ".");
    }
}
