package main.java.utility.concrete;

import main.java.utility.abstracts.IObserver;

import java.util.LinkedList;
import java.util.List;

public class Publisher{
    private static List<IObserver> gozlemciler = new LinkedList<IObserver>();

    public static void attach(IObserver observer) {
        gozlemciler.add(observer);
    }

    public static void ourNotify() {
        gozlemciler.forEach(x->x.update());
    }
}
