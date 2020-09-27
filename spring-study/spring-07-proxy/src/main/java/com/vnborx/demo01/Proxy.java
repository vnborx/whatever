package com.vnborx.demo01;

public class Proxy implements Rent {
    private Landlord landlord;

    public Proxy() {
    }

    public Proxy(Landlord landlord) {
        this.landlord = landlord;
    }

    public void rent() {
        landlord.rent();
        seeHouse();
        signContract();
        charge();
    }

    public void seeHouse() {
        System.out.println("Show the house to the guests.");
    }

    public void charge() {
        System.out.println("Charge the agency fee.");
    }

    public void signContract() {
        System.out.println("Sign a lease contract.");
    }
}
