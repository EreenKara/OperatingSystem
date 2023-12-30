package main.java.os.abstracts;

import main.java.utility.enums.State;

public interface IProcess {
    String[] getProcessProperties() ;

    void setProcessProperties(String[] processProperties);
    int getProcessId();

    void setProcessId(int processId);

    int getPriority();

    void setPriority(int priority);
}
