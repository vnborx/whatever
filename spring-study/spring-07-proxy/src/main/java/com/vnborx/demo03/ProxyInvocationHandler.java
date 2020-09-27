package com.vnborx.demo03;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyInvocationHandler implements InvocationHandler {

    private Rent rent;

    public void setRent(Rent rent) {
        this.rent = rent;
    }

    public Object getProxy() {
        return Proxy.newProxyInstance(this.getClass().getClassLoader(), rent.getClass().getInterfaces(), this);
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        seeHouse();
        Object result = method.invoke(rent, args);
        charge();
        return result;
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
