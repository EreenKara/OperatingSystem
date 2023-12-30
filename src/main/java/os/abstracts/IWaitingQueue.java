package main.java.os.abstracts;

public interface IWaitingQueue {

    void enqueue(IProcess process);
    IProcess dequeue();
}
