package main.java.utility.impl;

import main.java.utility.abstracts.IObserver;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Publisher{
    private final static List<IObserver> observers = new LinkedList<>();

    public static void attach(IObserver observer) {
        observers.add(observer);
        sortObserversByPriority();
    }

    private static void sortObserversByPriority() {
        observers.sort(Comparator.comparingInt(IObserver::getSequenceNumber));
    }

    public static void ourNotify() {
        observers.forEach(observer-> observer.update());
    }
}
