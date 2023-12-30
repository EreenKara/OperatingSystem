package main.java;

import main.java.pc.abstracts.IComputer;
import main.java.pc.impl.Computer;
import main.java.utility.abstracts.IReadText;

public class User implements IUser{

    private final IReadText readText;

    private final IComputer computer;

    public User(IReadText readText) {
        this.readText = readText;
        this.computer = new Computer();
    }

    @Override
    public void startComputer() {
        computer.turnOn();
    }

    @Override
    public void shutDownComputer() {
        computer.turnOff();
    }

    @Override
    public void update() {
    }

    @Override
    public int getSira() {
        return 0;
    }
}
