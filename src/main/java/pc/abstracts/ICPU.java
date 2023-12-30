package main.java.pc.abstracts;

import main.java.os.abstracts.IProcess;
import main.java.utility.abstracts.IObserver;

public interface ICPU extends IObserver {
    void Execute(IProcess process);
    IProcess getProcessinExecuting();
}
