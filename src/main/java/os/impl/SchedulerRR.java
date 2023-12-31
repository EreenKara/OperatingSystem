package main.java.os.impl;

import main.java.os.abstracts.IProcess;
import main.java.os.abstracts.ISchedulerRR;
import main.java.os.abstracts.IUserQueue;

public class SchedulerRR implements ISchedulerRR {
    private final IUserQueue userQueue;

    public SchedulerRR(IUserQueue userQueue) {
        this.userQueue = userQueue;
    }

    @Override
    public IProcess getProcess() {
        return userQueue.get();
    }

    @Override
    public void scheduleProcess(IProcess process) {
        if(process!=null)
            userQueue.add(process);
    }
}
