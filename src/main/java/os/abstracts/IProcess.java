package main.java.os.abstracts;

public interface IProcess {
    public String[] getProcessProperties() ;

    public void setProcessProperties(String[] processProperties);
    int getProcessId();

    void setProcessId(int processId);

    int getPriority();

    void setPriority(int priority);
}
