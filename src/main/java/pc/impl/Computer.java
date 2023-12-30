package main.java.pc.impl;

import main.java.pc.abstracts.IComputer;

public class Computer implements IComputer {
    boolean isOn;

    public Computer() {
        isOn=false;
    }

    @Override
    public void turnOn() {
        isOn=true;
    }

    @Override
    public void turnOff() {
        isOn=false;
    }
}
