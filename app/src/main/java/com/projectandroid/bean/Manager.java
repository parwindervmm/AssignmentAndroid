package com.projectandroid.bean;

public class Manager extends Employee {
    private int nbClients;

    public Manager(String name, int age, int birthYear, double monthlySalary, double rate, Car car, int nbClients) {
        super(name, age, birthYear, monthlySalary, rate, car);
        this.nbClients = nbClients;
    }

    public int getNbClients() {
        return nbClients;
    }

    public void setNbClients(int nbClients) {
        this.nbClients = nbClients;
    }
}
