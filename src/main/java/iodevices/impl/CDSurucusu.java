package main.java.iodevices.impl;

import main.java.iodevices.abstracts.ICDSurucusu;
import main.java.os.abstracts.IProcess;

public class CDSurucusu implements ICDSurucusu {
    private boolean _availableProcess = true;
    private int _currentProcessId = -1;
    private int cdSurucusuId;

    public CDSurucusu(int cdSurucusuId) {
        this.cdSurucusuId = cdSurucusuId;
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
