package com.vnborx.demo02;

public class UserServiceProxy implements UserService {
    private UserServiceImpl userService;
    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }

    public void create() {
        log("create");
        userService.create();
    }

    public void read() {
        log("read");
        userService.read();
    }

    public void update() {
        log("update");
        userService.update();
    }

    public void delete() {
        log("delete");
        userService.delete();
    }

    public void log(String msg) {
        System.out.println("[log] Have used a " + msg + " method.");
    }
}
