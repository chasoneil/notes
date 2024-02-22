package com.chason.prototype.deep;

import com.chason.prototype.Person;
import com.chason.prototype.shallow.ShallowObject;

import java.io.Serializable;

public class DeepObject implements Cloneable, Serializable {

    private Person person;

    public DeepObject (Person person) {
        this.person = person;
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
