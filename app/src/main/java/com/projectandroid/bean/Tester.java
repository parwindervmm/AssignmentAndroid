package com.projectandroid.bean;

public class Tester extends Employee {
    private int nbBugs;

    public Tester() {
    }

    public Tester(String name, int age, int birthYear, double monthlySalary, double rate, int nbBugs) {
        super(name, age, birthYear, monthlySalary, rate);
        this.nbBugs = nbBugs;
    }

    public int getNbBugs() {
        return nbBugs;
    }

    public void setNbBugs(int nbBugs) {
        this.nbBugs = nbBugs;
    }
}
