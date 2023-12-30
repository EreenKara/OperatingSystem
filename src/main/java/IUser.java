package main.java;

import main.java.utility.abstracts.IObserver;

public interface IUser extends IObserver {
    void startComputer();
    void shutDownComputer();
}
