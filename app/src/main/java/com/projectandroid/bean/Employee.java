package com.projectandroid.bean;

public abstract class Employee {
    private String name, id;
    private int age;
    private double income, rate;
    private Vehicle vehicle;

    public Employee(String name, String id, int age, double income, double rate, Vehicle vehicle) {
        this.name = name;
        this.id = id;
        this.age = age;
        this.income = income;
        this.rate = rate;
        this.vehicle = vehicle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public abstract void setAnnualIncome();
}
