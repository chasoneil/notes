package com.chason.prototype.shallow;

import com.chason.prototype.Person;

/**
 * 浅克隆对象
 */
public class ShallowObject implements Cloneable {

    private Person person;

    public ShallowObject () {
        System.out.println("构造方法被调用");
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Person getPerson() {
        return person;
    }

    @Override
    public ShallowObject clone() throws CloneNotSupportedException {
        return (ShallowObject) super.clone();
    }
}
