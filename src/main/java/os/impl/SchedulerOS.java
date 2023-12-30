package main.java.os.impl;

import main.java.os.abstracts.IProcess;
import main.java.os.abstracts.ISchedulerOS;

public class SchedulerOS implements ISchedulerOS {
    @Override
    public IProcess getProcess() {
        return null;
    }

    @Override
    public void scheduleProcess(IProcess process) {

    }

    @Override
    public void update() {

    }

    @Override
    public int getSira() {
        return 0;
    }
}
