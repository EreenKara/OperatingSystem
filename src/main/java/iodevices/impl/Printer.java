package main.java.iodevices.impl;

import main.java.iodevices.abstracts.IPrinter;
import main.java.os.abstracts.IProcess;

public class Printer implements IPrinter {
    private int _currentProcessId = -1;
    private boolean availableDevice = true;
    private int printerId;

    public Printer(int printerId)
    {
        this.printerId = printerId;
    }

    @Override
    public boolean checkStatus() {  //
        return availableDevice;
    }

    @Override
    public boolean allocate(IProcess process) {
        if(checkStatus())
        {
            _currentProcessId = process.getProcessId();
            availableDevice = false;
            return true;
        }
        return false;
    }
    @Override
    public boolean deAllocate() {
        availableDevice=true;
        _currentProcessId=-1;
        return true;
    }
    @Override
    public int checkWhichProcessUsingTheDevice() {
        return _currentProcessId;
    }
}
