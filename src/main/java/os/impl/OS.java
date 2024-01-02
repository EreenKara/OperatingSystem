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
import main.java.utility.impl.Publisher;

import java.util.ArrayList;
import java.util.List;

public class OS implements IOS {
    /*
     *
     * */
    private final IDisplay display;
    private final IRAM ram;
    private final ICPU cpu;
    private final IRealTimeQueue realTimeQueue;
    private final IUserQueue userQueue;
    private final ISchedulerRR schedulerRR;
    private final ISchedulerFCFS schedulerFCFS;
    private final ISchedulerGeneral schedulerGeneral;
    private final IDispatcher dispatcher;
    private final IProcessManager processManager;
    private final IOurTime ourTime;
    private final List<IPrinter> printers;//2
    private final List<IScanner> scanners;//1
    private final List<IModem> modems;//1
    private final List<ICDDrive> cdDrivers;//2
    private final IWaitingQueue waitingQueue;

    public OS(IDisplay display,
              IRAM ram,
              ICPU cpu,
              IRealTimeQueue realTimeQueue,
              IUserQueue userQueue,
              ISchedulerRR schedulerRR,
              ISchedulerFCFS schedulerFCFS,
              IDispatcher dispatcher,
              IProcessManager processManager,
              ISchedulerGeneral schedulerGeneral,
              IOurTime ourTime,
              List<IPrinter> printers,
              List<IScanner> scanners,
              List<IModem> modems,
              List<ICDDrive> cdDrivers,
              IWaitingQueue waitingQueue) {
        this.display = display;
        this.ram = ram;
        this.cpu = cpu;
        this.realTimeQueue = realTimeQueue;
        this.userQueue = userQueue;
        this.schedulerRR = schedulerRR;
        this.schedulerFCFS = schedulerFCFS;
        this.dispatcher = dispatcher;
        this.processManager = processManager;
        this.schedulerGeneral=schedulerGeneral;
        this.ourTime = ourTime;
        this.printers = printers;
        this.scanners = scanners;
        this.modems = modems;
        this.cdDrivers = cdDrivers;
        this.waitingQueue = waitingQueue;
        Publisher.attach(this);
    }


    @Override
    public void update() {
        this.checkWaitingQueueProcesses();
    }

    @Override
    public int getSequenceNumber() {
        return 4;
    }

    @Override
    public void requestForProcessCreate(String[] process) {
        IProcess createdProcess = this.processManager.createProcess(process);
        if (createdProcess != null) {
            this.sendAppropriateQueue(createdProcess);
        }
    }

    @Override
    public boolean shouldTurnOff() {
        return userQueue.isEmpty()&&realTimeQueue.isEmpty()&&waitingQueue.isEmpty();
    }

    @Override
    public void shutDown() {
        display.print("ALL QUEUES ARE EMPTY AND CPU IS EXECUTING WITHOUT PROCESS SINCE 5 SECONDS. SHUTTED DOWN PC");
        ourTime.stop();
    }

    private void sendAppropriateQueue(IProcess process) {
        if (ram.search(process.getProcessId()).getState() == State.WAITING) {
            this.waitingQueue.enqueue(process);
        } else {
            this.schedulerGeneral.scheduleProcess(process);
        }
    }

    private void checkWaitingQueueProcesses() {
        List<IProcess> processes=new ArrayList<>();
        for (IProcess process : this.waitingQueue.getQueue()) {
            IProcess updatedProcess = this.processManager.allocateDevicesAndRam(process);
            if(ram.search(updatedProcess.getProcessId()).getState() == State.READY) {
                this.schedulerGeneral.scheduleProcess(updatedProcess);
                processes.add(updatedProcess);
            }
        }
        processes.forEach(waitingQueue::dequeue);
    }
}
