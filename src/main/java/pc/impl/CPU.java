package main.java.pc.impl;

import main.java.iodevices.abstracts.*;
import main.java.os.abstracts.IPCB;
import main.java.os.abstracts.IProcess;
import main.java.pc.abstracts.ICPU;
import main.java.pc.abstracts.IDisplay;
import main.java.pc.abstracts.IRAM;
import main.java.utility.enums.State;
import main.java.utility.impl.Publisher;

public class CPU implements ICPU {
    private IProcess process;
    private short counter;
    private final IRAM ram;
    private final IDisplay display;

    public CPU(IRAM ram, IDisplay display) {
        this.process=null;
        this.counter=0;
        this.ram = ram;
        this.display = display;
        Publisher.attach(this);
    }

    public void setProcess(IProcess process) {
        if(process!=null)
        {
            display.print("PID: "+process.getProcessId()+" is set to execute");
        }
        this.process = process;
    }

    @Override
    public short getCounter() {
        return counter;
    }

    // kendi içerisine atanmış olan proces'i çalıştıracak.
    private void Execute() {
        if (process == null) return;

        IPCB pcb = ram.search(process.getProcessId());
        pcb.setState(State.RUNNING);

        int cdDriveCounter=0;
        int modemCounter=0;
        int printerCounter=0;
        int scannerCounter=0;
        for(IIODevice iioDevice: pcb.getIoDevices())
        {
            if(iioDevice instanceof IModem)
            {
                modemCounter++;
            }
            else if(iioDevice instanceof IPrinter)
            {
                printerCounter++;
            }
            else if(iioDevice instanceof IScanner)
            {
                scannerCounter++;
            }
            else if(iioDevice instanceof ICDDrive)
            {
                cdDriveCounter++;
            }
        }
        display.print(String.format("\u001B[38;5;%sm%d %d %d %d %s %d %d %d %d %s\u001B[0m%n",pcb.getProcessColor(), pcb.getProcessId(), pcb.getArrivingTime(), process.getPriority(),
                pcb.getEstimatedTime(),process.getProcessProperties()[3], printerCounter,
                scannerCounter,modemCounter,cdDriveCounter,pcb.getState().name())
        );

        pcb.setWorkingTime(pcb.getWorkingTime()+1);
        if(pcb.getWorkingTime()==pcb.getEstimatedTime())
        {
            pcb.setState(State.TERMINATED);
            display.print(String.format("\u001B[38;5;%sm%d %d %d %d %s %d %d %d %d %s\u001B[0m%n",pcb.getProcessColor(), pcb.getProcessId(), pcb.getArrivingTime(), process.getPriority(),
                    pcb.getEstimatedTime(),process.getProcessProperties()[3], printerCounter,
                    scannerCounter,modemCounter,cdDriveCounter,pcb.getState().name())
            );
        }
    }
    @Override
    public IProcess getProcessInExecution() {
        if(process==null)
            return null;
        IPCB pcb=ram.search(process.getProcessId());
        if(pcb==null||pcb.getState()==State.TERMINATED)
            return null;
        return process;
    }

    @Override
    public void update() {
        counter= (short) (process==null?counter+1:0);
        Execute();
    }

    @Override
    public int getSequenceNumber() {
        return 3;
    }
}
