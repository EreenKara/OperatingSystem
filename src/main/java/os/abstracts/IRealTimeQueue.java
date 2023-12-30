package main.java.os.abstracts;

import java.util.Queue;

public interface IRealTimeQueue {
    void add(IProcess process);
    IProcess get();
}
