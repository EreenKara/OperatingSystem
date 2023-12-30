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
        this.priority1=new LinkedList<>();
        this.priority2=new LinkedList<>();
        this.priority3=new LinkedList<>();
    }

    public UserQueue(Queue<IProcess> priority1, Queue<IProcess> priority2, Queue<IProcess> priority3) {
        this.priority1 = priority1;
        this.priority2 = priority2;
        this.priority3 = priority3;
    }

    @Override
    public void add(IProcess process) {
        switch(process.getProcessId()){
            case 0:{
                priority1.add(process);
                break;
            }
            case 2:{
                priority2.add(process);
                break;
            }
            case 3: {
                priority3.add(process);
                break;
            }
        }

    }

    @Override
    public void get() {

    }
}
