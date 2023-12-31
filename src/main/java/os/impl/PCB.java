package main.java.os.impl;

import main.java.iodevices.abstracts.IIODevice;
import main.java.os.abstracts.IPCB;
import main.java.utility.enums.State;

import java.util.List;
import java.util.Map;

public class PCB implements IPCB {
    private int processId;
    private State state;
    private int programCounter;
    private int workingTime;
    private int estimatedTime;
    private String processColor;
    private List<IIODevice> ioDevices;
    private int arrivingTime;
    private Map<Integer, Integer> memoryOccupiedPageTable;

    public int getArrivingTime() {
        return arrivingTime;
    }


    public PCB(int processId, State state, int programCounter,
               int workingTime, int estimatedTime, String processColor, List<IIODevice> ioDevices,
               int arrivingTime, Map<Integer, Integer> memoryOccupiedPageTable) {
        this.processId = processId;
        this.state = state;
        this.programCounter = programCounter;
        this.workingTime = workingTime;
        this.estimatedTime = estimatedTime;
        this.processColor = processColor;
        this.ioDevices = ioDevices;
        this.arrivingTime = arrivingTime;
        this.memoryOccupiedPageTable = memoryOccupiedPageTable;
    }

    public int getProcessId() {
        return processId;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public int getWorkingTime() {
        return workingTime;
    }

    public void setWorkingTime(int workingTime) {
        this.workingTime = workingTime;
    }

    public int getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(int estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public String getProcessColor() {
        return processColor;
    }

    public List<IIODevice> getIoDevices() {
        return ioDevices;
    }

    public void setIoDevices(List<IIODevice> ioDevices) {
        this.ioDevices = ioDevices;
    }

    public Map<Integer, Integer> getMemoryOccupiedPageTable() {
        return memoryOccupiedPageTable;
    }

    public void setMemoryOccupiedPageTable(Map<Integer, Integer> memoryOccupiedPageTable) {
        this.memoryOccupiedPageTable = memoryOccupiedPageTable;
    }
}
