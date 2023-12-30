package main.java.os.abstracts;

public interface IProcessManager {
    IProcess createProcess(String[] requirements);
    IProcess allocateDevicesAndRam(IProcess process);
}
