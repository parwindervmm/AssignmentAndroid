package com.projectandroid.bean;

public class Motorcycle extends Vehicle {
    private boolean sidecar;

    public Motorcycle(String make, String plate, String color, boolean sidecar) {
        super(make, plate, color);
        this.sidecar = sidecar;
    }

    public boolean isSidecar() {
        return sidecar;
    }

    public void setSidecar(boolean sidecar) {
        this.sidecar = sidecar;
    }
}
