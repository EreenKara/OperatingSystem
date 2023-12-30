package main.java.iodevices.abstracts;

public interface IIODevice {
    boolean checkStatus();
    boolean allocate();
    // Geriye process'in unique idsi dönecek
    int checkWhichProcessUsingTheDevice();



}
