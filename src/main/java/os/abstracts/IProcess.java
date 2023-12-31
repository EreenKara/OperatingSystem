package main.java.os.abstracts;


public interface IProcess {
    String[] getProcessProperties() ;

    int getProcessId();

    int getPriority();

    void setPriority(int priority);
}
