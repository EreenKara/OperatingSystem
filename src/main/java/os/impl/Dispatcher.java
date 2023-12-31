package main.java.os.impl;

import main.java.os.abstracts.IDispatcher;
import main.java.os.abstracts.IPCB;
import main.java.os.abstracts.IProcess;
import main.java.pc.abstracts.ICPU;
import main.java.pc.abstracts.IRAM;
import main.java.pc.impl.CPU;
import main.java.utility.enums.State;

public class Dispatcher implements IDispatcher {

    private final ICPU cpu;

    public Dispatcher(ICPU cpu) {
        this.cpu = cpu;
    }

    @Override
    public IProcess ContextSwitch(IProcess newProcess) {
        IProcess prevExecutedProcess= cpu.getProcessInExecution();
        uploadProcess(newProcess);
        return prevExecutedProcess;
    }

    private void uploadProcess(IProcess process){
        cpu.setProcess(process);
    }

    public boolean isProcessInFCFS()
    {
        IProcess process= cpu.getProcessInExecution();
        if(process==null)return false;
        return process.getPriority()==0;
    }

    private void saveProcessState(){
    }

    private void endProcess(){

    }

}
