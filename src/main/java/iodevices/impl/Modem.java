package main.java.iodevices.impl;

import main.java.iodevices.abstracts.IModem;
import main.java.os.abstracts.IProcess;

public class Modem implements IModem {
    private boolean _availableProcess = true;
    private int _currentProcessId = -1;
    private int modemId;

    public Modem(int modemId) {
        this.modemId = modemId;
    }

    @Override
    public boolean checkStatus() {
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
