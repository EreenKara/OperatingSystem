package main.java.os.impl;

import main.java.os.abstracts.IDispatcher;
import main.java.os.abstracts.IProcess;
import main.java.pc.abstracts.ICPU;

public class Dispatcher implements IDispatcher {

    private final ICPU cpu;

    public Dispatcher(ICPU cpu) {
        this.cpu = cpu;
    }

    @Override
    public IProcess ContextSwitch(IProcess newProcess) {
        IProcess prevExecutedProcess= cpu.getProcessInExecution();
        if(newProcess==null&&prevExecutedProcess!=null)return null;
        uploadProcess(newProcess);
        return prevExecutedProcess;
    }

    private void uploadProcess(IProcess process){
        cpu.setProcess(process);
    }

    public boolean isProcessInFCFS() {
        IProcess process = cpu.getProcessInExecution();
        if (process == null) return false;
        return process.getPriority() == 0;
    }
}
