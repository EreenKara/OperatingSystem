package main.java.os.abstracts;

import java.util.Queue;

public interface IWaitingQueue {

    void enqueue(IProcess process);
    IProcess dequeue();

    Queue<IProcess> getQueue();
}
