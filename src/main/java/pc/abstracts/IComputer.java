package main.java.pc.abstracts;

import main.java.os.abstracts.IOS;
import main.java.utility.abstracts.IObserver;

public interface IComputer extends IObserver {
    void turnOn();
    void turnOff();
    IOS getOS();
    void loadOperatingSystem();



}
