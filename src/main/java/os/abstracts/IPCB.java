package main.java.os.abstracts;

import main.java.iodevices.abstracts.IIODevice;
import main.java.utility.enums.State;

import java.util.Dictionary;
import java.util.List;
import java.util.Map;

public interface IPCB {
    public int getProcessId();

    public void setProcessId(int processId);

    public State getState();

    public void setState(State state);

    public int getProgramCounter();

    public void setProgramCounter(int programCounter);

    public int getWorkingTime();

    public void setWorkingTime(int workingTime);

    int getEstimatedTime();

    void setEstimatedTime(int estimatedTime);
    public String getProcessColor();

    public void setProcessColor(String processColor);

    public List<IIODevice> getIoDevices();

    public void setIoDevices(List<IIODevice> ioDevices);

    public Dictionary<Integer, Integer> getMemoryOccupiedPageTable();

    public void setMemoryOccupiedPageTable(Dictionary<Integer, Integer> memoryOccupiedPageTable) ;

    public int getArrivingTime();

    public void setArrivingTime(int arrivingTime) ;

}
