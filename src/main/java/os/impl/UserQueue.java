package main.java.os.impl;

import main.java.os.abstracts.IProcess;
import main.java.os.abstracts.IUserQueue;

import java.util.LinkedList;
import java.util.Queue;

public class UserQueue implements IUserQueue {
    private final Queue<IProcess> priority1;
    private final Queue<IProcess> priority2;
    private final Queue<IProcess> priority3;

    public UserQueue() {
        this.priority1 = new LinkedList<>();
        this.priority2 = new LinkedList<>();
        this.priority3 = new LinkedList<>();
    }

    @Override
    public void add(IProcess process) {
        switch (process.getPriority()) {
            case 1: {
                priority1.add(process);
                break;
            }
            case 2: {
                priority2.add(process);
                break;
            }
            case 3: {
                priority3.add(process);
                break;
            }
            default: {
                System.out.println("This process priority level doesn't exist.");
            }
        }

    }

    @Override
    public IProcess get() {
        IProcess process=null;
        if (!priority1.isEmpty()) {
            process=priority1.remove();
        }
        else if(!priority2.isEmpty())
        {
            process=priority2.remove();
        }
        else if(!priority3.isEmpty())
        {
            process=priority3.remove();
        }
        return process;
    }

    @Override
    public boolean isEmpty() {
        return priority1.isEmpty()&&priority2.isEmpty()&&priority3.isEmpty();
    }
}
