package main.java.os.impl;

import main.java.os.abstracts.IProcess;

public class Process implements IProcess {

    private String processName;
    private int processId;
    private int priority;

    public Process() {
    }

    public Process(String processName, int processId, int priority) {
        this.processName = processName;
        this.processId = processId;
        this.priority = priority;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public int getProcessId() {
        return processId;
    }

    public void setProcessId(int processId) {
        this.processId = processId;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
