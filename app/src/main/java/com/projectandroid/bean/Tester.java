package com.projectandroid.bean;

import androidx.annotation.NonNull;

import com.projectandroid.Constants;

public class Tester extends Employee {
    private int nbBugs;

    public Tester(String name, String id, int age, double income, double rate, Vehicle vehicle, int nbBugs) {
        super(name, id, age, income, rate, vehicle);
        this.nbBugs = nbBugs;
    }

    public int getNbBugs() {
        return nbBugs;
    }

    public void setNbBugs(int nbBugs) {
        this.nbBugs = nbBugs;
    }

    @Override
    public void setAnnualIncome() {
        double income = 0;
        income += 12 * (getRate() / 100.0) * getIncome();
        income += Constants.GAIN_FACTOR_ERROR * getNbBugs();
        setIncome(income);
    }


    @NonNull
    @Override
    public String toString() {

        return "Name: " + getName() + ",a Tester\n" +
                "Age: " + getAge() + "\n" +
                "Employee has a " + (getVehicle() instanceof Car ? "car" : "motorcycle") + "\n" +
                " -Model: " + getVehicle().getMake() + "\n" +
                " -Plate: " + getVehicle().getPlate() + "\n" +
                " -Color: " + getVehicle().getColor() + "\n" +
                " -Type: " + (getVehicle() instanceof Car ? ((Car) getVehicle()).getType() : ((Motorcycle) getVehicle()).isSidecar() ? "with a sidecar" : "without a sidecar") + "\n" +
                "Occupation rate: " + getRate() + "%\n" +
                "Annual Income: $ " + getIncome() + "\n" +
                "He/She has corrected " + getNbBugs() + " bugs";
    }
}
