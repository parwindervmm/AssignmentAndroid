package com.projectandroid.bean;

import androidx.annotation.NonNull;

import com.projectandroid.Constants;

public class Programmer extends Employee {
    private int nbProjects;

    public Programmer(String name, String id, int age, double income, double rate, Vehicle vehicle, int nbProjects) {
        super(name, id, age, income, rate, vehicle);
        this.nbProjects = nbProjects;
    }

    public int getNbProjects() {
        return nbProjects;
    }

    public void setNbProjects(int nbProjects) {
        this.nbProjects = nbProjects;
    }

    @Override
    public void setAnnualIncome() {
        double income = 0;
        income += 12 * (getRate() / 100.0) * getIncome();
        income += Constants.GAIN_FACTOR_PROJECTS * getNbProjects();
        setIncome(income);
    }


    @NonNull
    @Override
    public String toString() {

        return "Name: " + getName() + ",a Programmer\n" +
                "Age: " + getAge() + "\n" +
                "Employee has a " + (getVehicle() instanceof Car ? "car" : "motorcycle") + "\n" +
                " -Model: " + getVehicle().getMake() + "\n" +
                " -Plate: " + getVehicle().getPlate() + "\n" +
                " -Color: " + getVehicle().getColor() + "\n" +
                " -Type: " + (getVehicle() instanceof Car ? ((Car) getVehicle()).getType() : ((Motorcycle) getVehicle()).isSidecar() ? "with a sidecar" : "without a sidecar") + "\n" +
                "Occupation rate: " + getRate() + "%\n" +
                "Annual Income: $ " + getIncome() + "\n" +
                "He/She has completed " + getNbProjects() + " projects";
    }
}
