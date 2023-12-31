package main.java.iodevices.abstracts;

import main.java.os.abstracts.IProcess;
import main.java.os.impl.ProcessManager;

public interface IIODevice {
    boolean checkStatus();
    boolean allocate(IProcess process);
    boolean deAllocate();
    // Geriye process'in unique idsi dönecek
    int checkWhichProcessUsingTheDevice();



}
