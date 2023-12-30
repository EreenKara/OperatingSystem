package main.java.pc.impl;

import main.java.iodevices.abstracts.IModem;
import main.java.iodevices.abstracts.IPrinter;
import main.java.iodevices.abstracts.IScanner;
import main.java.os.abstracts.IOS;
import main.java.os.impl.OS;
import main.java.pc.abstracts.ICPU;
import main.java.pc.abstracts.IComputer;
import main.java.pc.abstracts.IDisplay;
import main.java.pc.abstracts.IRAM;

import java.util.List;

public class Computer implements IComputer {
    boolean isOn;

    private IOS operatingSystem;
    private IRAM ram;
    private List<IPrinter> printers;
    private List<IScanner> scanners;
    private List<IModem> modems;
    private IDisplay display;
    private ICPU cpu;

    public Computer() {
        isOn=false;
        this.operatingSystem = new OS();
        this.cpu = new CPU();
        this.display = new Display();
    }

    @Override
    public void turnOn() {
        isOn=true;
    }

    @Override
    public void turnOff() {
        isOn=false;
    }

    @Override
    public IOS getOS() {
        return this.operatingSystem;
    }

    @Override
    public boolean loadOperatingSystem() {
        return false;
    }


}
