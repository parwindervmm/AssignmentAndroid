package com.projectandroid.bean;

public class Vehicle {
    private String make, plate, color;


    public Vehicle(String make, String plate, String color) {
        this.make = make;
        this.plate = plate;
        this.color = color;

    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

}
