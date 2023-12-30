package main.java.os.impl;

import main.java.os.abstracts.IProcess;
import main.java.os.abstracts.ISchedulerRR;

public class SchedulerRR implements ISchedulerRR {
    private final UserQueue userQueue;

    public SchedulerRR() {
        this.userQueue = new UserQueue();
    }

    public SchedulerRR(UserQueue userQueue) {
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
