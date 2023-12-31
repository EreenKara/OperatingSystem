package main.java.os.abstracts;

import main.java.iodevices.abstracts.IIODevice;
import main.java.utility.enums.State;

import java.util.List;
import java.util.Map;

public interface IPCB {
    int getProcessId();
    State getState();

    void setState(State state);

    int getWorkingTime();

    void setWorkingTime(int workingTime);

    int getEstimatedTime();

    String getProcessColor();

    List<IIODevice> getIoDevices();

    void setIoDevices(List<IIODevice> ioDevices);

    Map<Integer, Integer> getMemoryOccupiedPageTable();

    void setMemoryOccupiedPageTable(Map<Integer, Integer> memoryOccupiedPageTable) ;

    int getArrivingTime();

}
