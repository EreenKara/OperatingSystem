package main.java.os.impl;

import main.java.os.abstracts.*;
import main.java.utility.enums.State;
import main.java.utility.impl.Publisher;

public class SchedulerOS implements ISchedulerOS {

    private final ISchedulerRR schedulerRR;
    private final ISchedulerFCFS schedulerFCFS;
    private final IDispatcher dispatcher;

    public SchedulerOS(ISchedulerRR schedulerRR, ISchedulerFCFS schedulerFCFS, IDispatcher dispatcher) {
        this.schedulerRR = schedulerRR;
        this.schedulerFCFS = schedulerFCFS;
        this.dispatcher = dispatcher;
        Publisher.attach(this);
    }

    @Override
    public IProcess getProcess() {
        IProcess process;
        IProcess prevExecutedProcess;
        if(dispatcher.isProcessInFCFS())
            return null;
        if ((process = schedulerFCFS.getProcess()) != null) {
            prevExecutedProcess = dispatcher.ContextSwitch(process);
        } else if ((process = schedulerRR.getProcess()) != null) {
            prevExecutedProcess = dispatcher.ContextSwitch(process);
            updatePriorityAndState(prevExecutedProcess);
            schedulerRR.scheduleProcess(prevExecutedProcess);
            return process;
        }
        else{
            dispatcher.ContextSwitch(null);
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
        return 2;
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
