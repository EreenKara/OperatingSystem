package main.java.pc.abstracts;

import main.java.os.abstracts.IPCB;
import main.java.pc.impl.Status;

import java.util.Map;

public interface IRAM {
    Map<Integer,Integer> allocate(int memorySize );
    void addPCB(IPCB pcb);
    IPCB search(int processID);
    Status checkStatus(int memorySize);
    Map<Integer,IPCB> getPCBList();
    void deAllocate(Map<Integer,Integer> map);
}
