package main.java.os.impl;

import main.java.os.abstracts.IProcess;
import main.java.os.abstracts.IWaitingQueue;

import java.util.LinkedList;
import java.util.Queue;

public class WaitingQueue implements IWaitingQueue {
    private final Queue<IProcess> waitingQueue;

    public WaitingQueue() {
        this.waitingQueue = new LinkedList<>();
    }


    @Override
    public void enqueue(IProcess process) {
        waitingQueue.add(process);
    }

    @Override
    public IProcess dequeue() {
        return waitingQueue.remove();
    }

    @Override
    public Queue<IProcess> getQueue() {
        return waitingQueue;
    }
}
