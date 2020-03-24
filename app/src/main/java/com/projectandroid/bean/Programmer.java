package com.projectandroid.bean;

public class Programmer extends Employee {
    private int nbProjects;

    public Programmer(String name, int age, int birthYear, double monthlySalary, double rate, Car car, int nbProjects) {
        super(name, age, birthYear, monthlySalary, rate, car);
        this.nbProjects = nbProjects;
    }

    public int getNbProjects() {
        return nbProjects;
    }

    public void setNbProjects(int nbProjects) {
        this.nbProjects = nbProjects;
    }
}
