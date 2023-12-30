package main.java.os.impl;

import main.java.os.abstracts.IProcess;
import main.java.os.abstracts.IProcessManager;
import main.java.pc.abstracts.IRAM;

public class ProcessManager implements IProcessManager {

    private final IRAM ram;
    private int pid;
    public ProcessManager(IRAM ram) {
        this.pid=0;
        this.ram = ram;
    }

    @Override
    public IProcess createProcess(String[] requirements) {
        return null;
    }

    @Override
    public boolean allocateDevicesAndRam(IProcess process) {
        return false;
    }

    private int getUniqueProcessId()
    {
        return pid++;
    }

}
