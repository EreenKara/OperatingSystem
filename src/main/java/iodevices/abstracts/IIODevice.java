package main.java.iodevices.abstracts;

public interface IIODevice {
    public boolean checkStatus();
    public boolean allocate();
    // Geriye process'in unique idsi dönecek
    public int checkWhichProcessUsingTheDevice();



}
