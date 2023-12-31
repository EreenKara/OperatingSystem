package main.java.os.abstracts;

public interface IRealTimeQueue {
    void add(IProcess process);
    IProcess get();
}
