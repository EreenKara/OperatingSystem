package main.java.os.impl;

import main.java.os.abstracts.IDispatcher;
import main.java.os.abstracts.IPCB;
import main.java.os.abstracts.IProcess;
import main.java.pc.abstracts.ICPU;
import main.java.pc.abstracts.IRAM;
import main.java.utility.enums.State;

public class Dispatcher implements IDispatcher {

    private ICPU cpu;

    @Override
    public IProcess ContextSwitch(IProcess newProcess) {
        IProcess prevExecutedProcess= cpu.getProcessInExecution();
        uploadProcess(newProcess);
        return prevExecutedProcess;
    }

    private void uploadProcess(IProcess process){
//        IPCB pcb=ram.search(process.getProcessId());
//        if(pcb==null){
//            return;
//        }
//        ram.allocate();
        cpu.setProcess(process);
    }

    private void saveProcessState(){
    }

    private void endProcess(){

    }

}
