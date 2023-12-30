package main.java.os.impl;

import main.java.os.abstracts.*;
import main.java.utility.enums.State;

public class SchedulerOS implements ISchedulerOS {

    private final ISchedulerRR schedulerRR;
    private final ISchedulerFCFS schedulerFCFS;
    private final IDispatcher dispatcher;

    public SchedulerOS() {
        schedulerRR = new SchedulerRR();
        schedulerFCFS = new SchedulerFCFS();
        dispatcher = new Dispatcher();
    }

    public SchedulerOS(ISchedulerRR schedulerRR, ISchedulerFCFS schedulerFCFS, IDispatcher dispatcher) {
        this.schedulerRR = new SchedulerRR();
        this.schedulerFCFS = new SchedulerFCFS();
        this.dispatcher = new Dispatcher();
    }

    @Override
    public IProcess getProcess() {
        IProcess process;
        IProcess prevExecutedProcess;
        if ((process = schedulerFCFS.getProcess()) != null) {
            prevExecutedProcess = dispatcher.ContextSwitch(process);
        } else if ((process = schedulerRR.getProcess()) != null) {
            prevExecutedProcess = dispatcher.ContextSwitch(process);
            updatePriorityAndState(prevExecutedProcess);
            schedulerRR.scheduleProcess(prevExecutedProcess);
            return process;
        }
        return null;

    }

    @Override
    public void scheduleProcess(IProcess process) {
        if (process == null) return;
        int priority = process.getPriority();
        if (priority == 0) {
            schedulerFCFS.scheduleProcess(process);
        } else {
            schedulerRR.scheduleProcess(process);
        }
    }

    @Override
    public void update() {
        getProcess();
    }

    @Override
    public int getSequenceNumber() {
        return 1;
    }

    private void updatePriorityAndState(IProcess process) {
        if(process==null)return;
        int processPriority = process.getPriority();
        if (processPriority == 3) {
            return;
        }
        process.setPriority(processPriority + 1);
    }
}
