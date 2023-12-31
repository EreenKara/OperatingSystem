package main.java.os.abstracts;

import java.util.Queue;

public interface IWaitingQueue {

    void enqueue(IProcess process);
    void dequeue(IProcess process);

    Queue<IProcess> getQueue();
}
