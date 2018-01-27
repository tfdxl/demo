package com.example.entity;

import java.io.Serializable;

public class Menu implements Serializable {

    private String name;
    private int index;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "name='" + name + '\'' +
                ", index=" + index +
                '}';
    }

    public Menu(String name, int index) {
        this.name = name;
        this.index = index;
    }
}
