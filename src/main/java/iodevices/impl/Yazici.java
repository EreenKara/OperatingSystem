package main.java.iodevices.impl;

import main.java.iodevices.abstracts.IYazici;
import main.java.os.abstracts.IProcess;
import main.java.os.impl.ProcessManager;

public class Yazici implements IYazici {
    private int _currentProcessId = -1;
    private boolean _availableProcess = true;
    private int yaziciId;

    public Yazici(int yaziciId) {
        this.yaziciId = yaziciId;
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
