package main.java.pc.abstracts;

import main.java.os.abstracts.IPCB;

import java.util.Dictionary;

public interface IRAM {
    Dictionary<Integer,Integer> allocate(int memory);
    boolean addPCB(IPCB pcb);
    IPCB search(int processID);
}
