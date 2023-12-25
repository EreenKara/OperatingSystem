package main.java.pc.abstracts;

import main.java.os.abstracts.IPCB;

import java.util.Dictionary;

public interface IRAM {
    public Dictionary<Integer,Integer> allocate(int memory);
    public boolean addPCB(IPCB pcb);
    public IPCB search(int processID);
}
