package main.java.iodevices.impl;

import main.java.iodevices.abstracts.IModem;
import main.java.os.abstracts.IProcess;

public class Modem implements IModem {
    private boolean availableDevice = true;
    private int _currentProcessId = -1;
    private final int modemId;

    public Modem(int modemId) {
        this.modemId = modemId;
    }

    @Override
    public boolean checkStatus() {
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
    public void deAllocate() {
        availableDevice=true;
        _currentProcessId=-1;
    }
    @Override
    public int checkWhichProcessUsingTheDevice() {
        return _currentProcessId;
    }
}
