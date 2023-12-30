package main.java.utility.impl;

import main.java.utility.abstracts.IObserver;

import java.util.LinkedList;
import java.util.List;

public class Publisher{
    private static List<IObserver> observers = new LinkedList<IObserver>();

    public static void attach(IObserver observer) {
        observers.add(observer);
    }

    public static void ourNotify() {

        observers.forEach(x->x.update());

    }
}
