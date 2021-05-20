package com.jumia.demo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Customer {

    public Customer() {
    }

    @Getter
    @Setter
    @Id
    private int id;
    private String name;
    @Getter
    @Setter
    private String phone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
