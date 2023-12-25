package main.java.os.abstracts;

public interface ISchedulerGeneral {
    IProcess getProcess(); // yani queue'lardan process getir.
    void scheduleProcess(IProcess process);
}
