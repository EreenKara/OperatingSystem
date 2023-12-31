package main.java.os.abstracts;

public interface IUserQueue {
    void add(IProcess process);
    IProcess get();
    boolean isEmpty();
}
