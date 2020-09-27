package com.vnborx.demo01;

public class Tenant {
    public static void main(String[] args) {
        Landlord landlord = new Landlord();
        Proxy proxy = new Proxy(landlord);
        proxy.rent();
    }
}
