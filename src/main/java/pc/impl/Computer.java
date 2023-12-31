package main.java.pc.impl;

import main.java.iodevices.abstracts.ICDDrive;
import main.java.iodevices.abstracts.IModem;
import main.java.iodevices.abstracts.IPrinter;
import main.java.iodevices.abstracts.IScanner;
import main.java.os.abstracts.*;
import main.java.os.impl.*;
import main.java.pc.abstracts.ICPU;
import main.java.pc.abstracts.IComputer;
import main.java.pc.abstracts.IDisplay;
import main.java.pc.abstracts.IRAM;
import main.java.utility.impl.OurTime;
import main.java.utility.impl.Publisher;

import java.util.List;

public class Computer implements IComputer {
    boolean isOn;
    private IOS operatingSystem;
    private final IRAM ram;
    private final List<IPrinter> printers;
    private final List<IScanner> scanners;
    private final List<IModem> modems;
    private final List<ICDDrive> cdDrivers;
    private final IDisplay display;
    private final ICPU cpu;

    public Computer(IRAM ram, List<IPrinter> printers, List<IScanner> scanners, List<IModem> modems, List<ICDDrive> cdDrivers, IDisplay display, ICPU cpu) {
        this.isOn=false;
        this.ram = ram;
        this.printers = printers;
        this.scanners = scanners;
        this.modems = modems;
        this.cdDrivers = cdDrivers;
        this.display = display;
        this.cpu = cpu;
        Publisher.attach(this);
    }

    @Override
    public void turnOn() {
        isOn=true;
    }

    @Override
    public void turnOff() {
        isOn=false;
    }

    @Override
    public IOS getOS() {
        return this.operatingSystem;
    }

    @Override
    public void loadOperatingSystem() {
        IUserQueue userQueue=new UserQueue();
        IRealTimeQueue realTimeQueue=new RealTimeQueue();
        ISchedulerFCFS schedulerFCFS= new SchedulerFCFS(realTimeQueue);
        ISchedulerRR schedulerRR=new SchedulerRR(userQueue);
        IDispatcher dispatcher=new Dispatcher(cpu);
        operatingSystem=new OS(display,
                ram,
                cpu,
                realTimeQueue,
                userQueue,
                schedulerRR,
                schedulerFCFS,
                dispatcher,
                new ProcessManager(ram,printers,scanners,modems,cdDrivers),
                new SchedulerOS(schedulerRR,schedulerFCFS,dispatcher),
                new OurTime(250),
                printers,
                scanners,
                modems,
                cdDrivers,
                new WaitingQueue());
    }

    @Override
    public void update() {
        if(operatingSystem.shouldTurnOff()&&cpu.getCounter()==5 )
        {
            operatingSystem.shutDown();
            turnOff();
        }
    }

    @Override
    public int getSequenceNumber() {
        return 5;
    }
}
