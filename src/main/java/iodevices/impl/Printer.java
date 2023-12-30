package main.java.iodevices.impl;

import main.java.iodevices.abstracts.IPrinter;
import main.java.os.abstracts.IProcess;

public class Printer implements IPrinter {
    private int _currentProcessId = -1;
    private boolean _availableProcess = true;
    private int printerId;

    public Printer(int printerId)
    {
        this.printerId = printerId;
    }

    @Override
    public boolean checkStatus() {  //
        return _availableProcess;
    }

    @Override
    public boolean allocate(IProcess process) {
        if(checkStatus())
        {
            _currentProcessId = process.getProcessId();
            _availableProcess = false;
            return true;
        }
        return false;
    }

    @Override
    public int checkWhichProcessUsingTheDevice() {
        return _currentProcessId;
    }
}
