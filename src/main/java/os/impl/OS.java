package main.java.os.impl;

import main.java.iodevices.abstracts.ICDDrive;
import main.java.iodevices.abstracts.IModem;
import main.java.iodevices.abstracts.IPrinter;
import main.java.iodevices.abstracts.IScanner;
import main.java.os.abstracts.*;
import main.java.pc.abstracts.ICPU;
import main.java.pc.abstracts.IDisplay;
import main.java.pc.abstracts.IRAM;
import main.java.utility.abstracts.IOurTime;
import main.java.utility.enums.State;

import java.util.List;

public class OS implements IOS {
    /*
     *
     * */
    private IDisplay display;
    private IRAM ram;
    private ICPU cpu;
    private IRealTimeQueue realTimeQueue;
    private IUserQueue userQueue;
    private ISchedulerRR schedulerRR;
    private ISchedulerFCFS schedulerFCFS;
    private ISchedulerGeneral schedulerGeneral;
    private IDispatcher dispatcher;
    private IProcessManager processManager;
    private IOurTime ourTime;
    private List<IPrinter> printers;//2
    private List<IScanner> scanners;//1
    private List<IModem> modems;//1
    private List<ICDDrive> cdDrives;//2
    private IWaitingQueue waitingQueue;


    @Override
    public void update() {
        this.checkWaitingQueueProcesses();
    }

    @Override
    public int getSequenceNumber() {
        return 0;
    }

    @Override
    public void requestForProcessCreate(String[] process) {
        IProcess createdProcess = this.processManager.createProcess(process);
        if (createdProcess != null) {
            this.sendApproiateQueue(createdProcess);
        } else {
            display.print(process[0] + "\tHATA - Gerçek-zamanlı proses (64MB) tan daha fazla bellek talep ediyor - proses silindi.");
        }
    }

    private void sendApproiateQueue(IProcess process) {
        if (ram.search(process.getProcessId()).getState() == State.WAITING) {
            this.waitingQueue.enqueue(process);
        } else {
            this.schedulerGeneral.scheduleProcess(process);
        }
    }

    private void checkWaitingQueueProcesses() {
        for (IProcess process : this.waitingQueue.getQueue()) {
            IProcess updatedProcess = this.processManager.allocateDevicesAndRam(process);
            if(ram.search(updatedProcess.getProcessId()).getState() == State.READY) {
                this.schedulerGeneral.scheduleProcess(updatedProcess);
            }
        }
    }
}
