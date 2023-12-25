package main.java.os.abstracts;

public interface IProcessManager {
    public IProcess createProcess(String[] requirements);
    public boolean allocateDevicesAndRam(IProcess process);
}
