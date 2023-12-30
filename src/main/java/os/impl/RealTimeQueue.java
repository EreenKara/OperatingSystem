package main.java.os.impl;

import main.java.os.abstracts.IProcess;
import main.java.os.abstracts.IRealTimeQueue;

import java.util.Queue;

public class RealTimeQueue implements IRealTimeQueue {
    private final Queue<IProcess> priority0;

    public RealTimeQueue(Queue<IProcess> realtimeQueue) {
        this.priority0 = realtimeQueue;
    }
    @Override
    public void add(IProcess process) {
        priority0.add(process);
    }

    @Override
    public IProcess get() {
        return priority0.poll();
    }
}
