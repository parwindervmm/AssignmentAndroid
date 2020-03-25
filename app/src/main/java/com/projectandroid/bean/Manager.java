package com.projectandroid.bean;

import androidx.annotation.NonNull;

import com.projectandroid.Constants;

public class Manager extends Employee {
    private int nbClients;

    public Manager(String name, String id, int age, double income, double rate, Vehicle vehicle, int nbClients) {
        super(name, id, age, income, rate, vehicle);
        this.nbClients = nbClients;
    }

    public int getNbClients() {
        return nbClients;
    }

    public void setNbClients(int nbClients) {
        this.nbClients = nbClients;
    }

    @Override
    public void setAnnualIncome() {
        double income = 0;
        income += 12 * (getRate() / 100.0) * getIncome();
        income += Constants.GAIN_FACTOR_CLIENT * getNbClients();
        setIncome(income);
    }

    @NonNull
    @Override
    public String toString() {

        return "Name: " + getName() + ",a Manager\n" +
                "Age: " + getAge() + "\n" +
                "Employee has a " + (getVehicle() instanceof Car ? "car" : "motorcycle") + "\n" +
                " -Model: " + getVehicle().getMake() + "\n" +
                " -Plate: " + getVehicle().getPlate() + "\n" +
                " -Color: " + getVehicle().getColor() + "\n" +
                " -Type: " + (getVehicle() instanceof Car ? ((Car) getVehicle()).getType() : ((Motorcycle) getVehicle()).isSidecar() ? "with a sidecar" : "without a sidecar") + "\n" +
                "Occupation rate: " + getRate() + "%\n" +
                "Annual Income: $ " + getIncome() + "\n" +
                "He/She has brought " + getNbClients() + " new clients";
    }
}
