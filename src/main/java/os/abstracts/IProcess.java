package main.java.os.abstracts;

import main.java.utility.enums.State;

public interface IProcess {
    int getProcessId();
    int getPriority();
    void setPriority(int priority);
}
