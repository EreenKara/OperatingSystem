package main.java.iodevices.impl;

import main.java.iodevices.abstracts.IScanner;
import main.java.os.abstracts.IProcess;

public class Scanner implements IScanner {
    private boolean availableDevice = true;
    private int _currentProcessId = -1;
    private int scannerId;

    public Scanner(int scannerId) {
        this.scannerId = scannerId;
    }

    @Override
    public boolean checkStatus() {
        return availableDevice;
    }
    @Override
    public boolean deAllocate() {
        availableDevice=true;
        _currentProcessId=-1;
        return true;
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
    public int checkWhichProcessUsingTheDevice() {
        return _currentProcessId;
    }
}
