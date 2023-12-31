package main.java.pc.abstracts;

import main.java.os.abstracts.IPCB;
import main.java.pc.impl.Status;

import java.util.Dictionary;
import java.util.Map;

public interface IRAM {
    Map<Integer,Integer> allocate(int memorysize );
    boolean addPCB(IPCB pcb);
    IPCB search(int processID);
    public Status checkStatus(int memorysize);
    Map<Integer,IPCB> getPCBList();
    public void deAllocate(Map<Integer,Integer> liste);
}
