package main.java.os.abstracts;


// bu sınıf sadece kalıtım vermek için var implemente edilmeyecek.
public interface ISchedulerGeneral {
    IProcess getProcess(); // yani queue'lardan process getir.
    void scheduleProcess(IProcess process);
}
