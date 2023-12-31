package main.java.pc.abstracts;

import main.java.os.abstracts.IOS;

public interface IComputer {
    void turnOn();
    void turnOff();
    IOS getOS();
    void loadOperatingSystem();



}
