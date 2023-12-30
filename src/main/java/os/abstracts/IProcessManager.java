package main.java.os.abstracts;

public interface IProcessManager {
    IProcess createProcess(String[] requirements);
    boolean allocateDevicesAndRam(IProcess process);
}
