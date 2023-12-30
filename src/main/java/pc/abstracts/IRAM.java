package main.java.pc.abstracts;

import main.java.os.abstracts.IPCB;
import main.java.pc.impl.Status;

import java.util.Dictionary;

public interface IRAM {
    Dictionary<Integer,Integer> allocate(int memorysize );
    boolean addPCB(IPCB pcb);
    IPCB search(int processID);
    public Status checkStatus(int memorysize);
}
