package main.java.utility.impl;

import main.java.iodevices.abstracts.ICDDrive;
import main.java.iodevices.abstracts.IModem;
import main.java.iodevices.abstracts.IPrinter;
import main.java.iodevices.abstracts.IScanner;
import main.java.iodevices.impl.CDDrive;
import main.java.iodevices.impl.Modem;
import main.java.iodevices.impl.Printer;
import main.java.iodevices.impl.Scanner;
import main.java.pc.abstracts.ICPU;
import main.java.pc.abstracts.IComputer;
import main.java.pc.abstracts.IDisplay;
import main.java.pc.abstracts.IRAM;
import main.java.pc.impl.CPU;
import main.java.pc.impl.Computer;
import main.java.pc.impl.Display;
import main.java.pc.impl.Ram;
import main.java.utility.abstracts.IComputerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ComputerFactory implements IComputerFactory {
    @Override
    public IComputer createComputer() {
        IRAM ram=new Ram(1024);
        IDisplay display=new Display();
        List<IPrinter> printers= Arrays.asList(new Printer(1),new Printer(2));
        List<IScanner> scanners= Arrays.asList(new Scanner(1));
        List<ICDDrive> cdDrivers= Arrays.asList(new CDDrive(1),new CDDrive(2));
        List<IModem> modems=Arrays.asList(new Modem(1));
        return new Computer(ram,
                printers,
                scanners,
                modems,
                cdDrivers,
                display,
                new CPU(ram,display));
    }
}
