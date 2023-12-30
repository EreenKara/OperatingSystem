package main.java.os.abstracts;

import main.java.iodevices.abstracts.IIODevice;
import main.java.utility.enums.State;

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

    public String getProcessColor();

    public void setProcessColor(String processColor);

    public List<IIODevice> getIoDevices();

    public void setIoDevices(List<IIODevice> ioDevices);

    public Map<Integer, Integer> getMemoryOccupiedPageTable();

    public void setMemoryOccupiedPageTable(Map<Integer, Integer> memoryOccupiedPageTable) ;

}
