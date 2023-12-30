package main.java.pc.impl;

import main.java.pc.abstracts.IDisplay;

public class Display implements IDisplay {
    @Override
    public void print(String text) {
        System.out.println(text);
    }
}
