package com.chason.prototype;

import lombok.Data;

import java.io.Serializable;

@Data
public class Person implements Serializable {

    private String name;

    private int age;

    public Person (String name) {
        this.name = name;
    }

    public void show() {
        System.out.println(this.name);
    }


}
