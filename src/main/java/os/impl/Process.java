package main.java.os.impl;

import main.java.os.abstracts.IProcess;

public class Process implements IProcess {

    private String[] processProperties;
    private int processId;
    private int priority;

    public Process() {
    }

    public Process(String[] processProperties, int processId, int priority) {
        this.processProperties = processProperties;
        this.processId = processId;
        this.priority = priority;
    }

    public String[] getProcessProperties() {
        return processProperties;
    }

    public void setProcessProperties(String[] processProperties) {
        this.processProperties = processProperties;
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
