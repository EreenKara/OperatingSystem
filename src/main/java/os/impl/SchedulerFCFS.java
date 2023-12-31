package main.java.os.impl;

import main.java.os.abstracts.IProcess;
import main.java.os.abstracts.IRealTimeQueue;
import main.java.os.abstracts.ISchedulerFCFS;

public class SchedulerFCFS implements ISchedulerFCFS {

    private final IRealTimeQueue realTimeQueue;
    public SchedulerFCFS(IRealTimeQueue realTimeQueue) {
        this.realTimeQueue = realTimeQueue;
    }
    @Override
    public IProcess getProcess() {
        return realTimeQueue.get();
    }

    @Override
    public void scheduleProcess(IProcess process) {
        realTimeQueue.add(process);
    }
}
