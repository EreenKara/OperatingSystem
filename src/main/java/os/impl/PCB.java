package main.java.os.impl;

import main.java.iodevices.abstracts.IIODevice;
import main.java.os.abstracts.IPCB;
import main.java.utility.enums.State;

import java.util.Dictionary;
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
    private int estimatedWorkingTime;
    private int arrivingTime;
    private Dictionary<Integer, Integer> memoryOccupiedPageTable;

    public int getEstimatedWorkingTime() {
        return estimatedWorkingTime;
    }

    public void setEstimatedWorkingTime(int estimatedWorkingTime) {
        this.estimatedWorkingTime = estimatedWorkingTime;
    }

    public int getArrivingTime() {
        return arrivingTime;
    }

    public void setArrivingTime(int arrivingTime) {
        this.arrivingTime = arrivingTime;
    }



    public PCB(int processId, State state, int programCounter,
    		int workingTime, String processColor, List<IIODevice> ioDevices,
    		Dictionary<Integer, Integer> memoryOccupiedPageTable)
    {
        this.processId = processId;
        this.state = state;
        this.programCounter = programCounter;
        this.workingTime = workingTime;
        this.processColor = processColor;
        this.ioDevices = ioDevices;
        this.memoryOccupiedPageTable = memoryOccupiedPageTable;
    }

    public int getProcessId() {
        return processId;
    }

    public void setProcessId(int processId) {
        this.processId = processId;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public int getProgramCounter() {
        return programCounter;
    }

    public void setProgramCounter(int programCounter) {
        this.programCounter = programCounter;
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

    public void setProcessColor(String processColor) {
        this.processColor = processColor;
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
