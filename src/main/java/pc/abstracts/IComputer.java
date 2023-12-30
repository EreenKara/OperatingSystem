package main.java.pc.abstracts;

import main.java.iodevices.abstracts.IModem;
import main.java.os.abstracts.IOS;

public interface IComputer {
    void turnOn();
    void turnOff();
    IOS getOS();
    boolean loadOperatingSystem();



}
