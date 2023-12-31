package main.java.os.impl;

import main.java.os.abstracts.IProcess;
import main.java.os.abstracts.IRealTimeQueue;

import java.util.LinkedList;
import java.util.Queue;

public class RealTimeQueue implements IRealTimeQueue {
    private final Queue<IProcess> priority0;

    public RealTimeQueue() {
        this.priority0 = new LinkedList<>();
    }

    @Override
    public void add(IProcess process) {
        priority0.add(process);
    }

    @Override
    public IProcess get() {
        if(priority0.isEmpty())
            return null;
        return priority0.remove();
    }
}
