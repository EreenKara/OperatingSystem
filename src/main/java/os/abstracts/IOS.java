package main.java.os.abstracts;

import main.java.utility.abstracts.IObserver;

public interface IOS extends IObserver {
    void requestForProcessCreate(String[] process);
    boolean shouldTurnOff();

    void shutDown();
}
