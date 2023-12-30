package main.java.pc.impl;

import main.java.os.abstracts.IPCB;
import main.java.os.abstracts.IProcess;
import main.java.pc.abstracts.ICPU;
import main.java.pc.abstracts.IDisplay;
import main.java.pc.abstracts.IRAM;
import main.java.utility.enums.State;

public class CPU implements ICPU {
    private IProcess process;
    private short counter;
    private IRAM ram;
    private IDisplay display;

    public CPU() {

    }

    public CPU(IProcess process, short counter, IRAM ram, IDisplay display) {
        this.process = process;
        this.counter = counter;
        this.ram = ram;
        this.display = display;
    }

    public void setProcess(IProcess process) {
        this.process = process;
    }

    // kendi içerisine atanmış olan proces'i çalıştıracak.
    private void Execute() {
        if (process == null) return;

        IPCB pcb = ram.search(process.getProcessId());
        pcb.setState(State.RUNNING);

        if(pcb.getWorkingTime()==pcb.getEstimatedTime())
        {
            pcb.setState(State.TERMINATED);
        }
//        display.
        pcb.setWorkingTime(pcb.getWorkingTime()+1);
    }

    @Override
    public IProcess getProcessInExecution() {
        return process;
    }

    @Override
    public void update() {
        Execute();
    }

    @Override
    public int getSira() {
        return 0;
    }
}
