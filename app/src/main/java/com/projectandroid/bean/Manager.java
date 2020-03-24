package com.projectandroid.bean;

public class Manager extends Employee {
    private int nbClients;

    public Manager() {
    }

    public Manager(String name, int age, int birthYear, double monthlySalary, double rate, int nbClients) {
        super(name, age, birthYear, monthlySalary, rate);
        this.nbClients = nbClients;
    }

    public int getNbClients() {
        return nbClients;
    }

    public void setNbClients(int nbClients) {
        this.nbClients = nbClients;
    }
}
