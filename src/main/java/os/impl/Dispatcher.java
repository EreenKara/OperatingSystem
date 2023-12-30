package main.java.os.impl;

import main.java.os.abstracts.IDispatcher;
import main.java.os.abstracts.IProcess;
import main.java.pc.abstracts.ICPU;
import main.java.pc.abstracts.IRAM;

public class Dispatcher implements IDispatcher {

    private ICPU cpu;
    private IRAM ram;

    @Override
    public IProcess ContextSwitch(IProcess yeniProcess) {
        return null;
    }

    private void executeProcess()
    {

    }

    private void uploadProcess(){

    }
    private void saveProcessState(){

    }

    private void endProcess(){

    }

}
