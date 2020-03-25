package com.projectandroid.bean;

public class Car extends Vehicle {
    private String type;


    public Car(String make, String plate, String color, String type) {
        super(make, plate, color);
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
