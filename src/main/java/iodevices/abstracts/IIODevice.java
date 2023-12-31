package main.java.iodevices.abstracts;

import main.java.os.abstracts.IProcess;

public interface IIODevice {
    boolean checkStatus();
    boolean allocate(IProcess process);
    void deAllocate();
    // Geriye process'in unique idsi dönecek
    int checkWhichProcessUsingTheDevice();



}
