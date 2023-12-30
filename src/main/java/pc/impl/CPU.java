package main.java.pc.impl;

import main.java.os.abstracts.IProcess;
import main.java.pc.abstracts.ICPU;
import main.java.pc.abstracts.IDisplay;
import main.java.pc.abstracts.IRAM;

public class CPU implements ICPU{
    private IProcess process;
    private short counter;
    private IRAM ram;
    private IDisplay display;

    public CPU(IProcess process, short counter, IRAM ram, IDisplay display) {
        this.process = process;
        this.counter = counter;
        this.ram = ram;
        this.display = display;
    }

    @Override
    public void Execute(IProcess process) {

    }

    @Override
    public IProcess getProcessinExecuting() {
        return null;
    }

    @Override
    public void update() {

    }

    @Override
    public int getSira() {
        return 0;
    }
}
