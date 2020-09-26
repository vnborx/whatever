package com.vnborx.pojo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.lang.Nullable;

public class Person {
    private String name;
    @Autowired
    @Qualifier(value = "catTest")
    private Cat cat;
    @Autowired
    private Dog dog;

//    public Person (@Nullable String name) {
//        this.name = name;
//    }

    public String getName() {
        return name;
    }

    public Cat getCat() {
        return cat;
    }

    public Dog getDog() {
        return dog;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", cat=" + cat +
                ", dog=" + dog +
                '}';
    }
}
