package main.java;

import main.java.pc.abstracts.IComputer;
import main.java.pc.impl.Computer;
import main.java.utility.abstracts.IReadText;
import main.java.utility.impl.OurTime;
import main.java.utility.impl.Publisher;
import main.java.utility.impl.ReadText;

import java.util.List;

public class User implements IUser {

    private final IReadText readText;

    private final IComputer computer;

    public User(IComputer computer) {
        this.readText = new ReadText();
        readText.read("C:\\Users\\ilyas\\School\\Isletim Sistemleri\\Odev\\giris.txt");
        this.computer = computer;
        Publisher.attach(this);
    }

    @Override
    public void startComputer() {
        computer.turnOn();
        computer.loadOperatingSystem();
    }

    @Override
    public void shutDownComputer() {
        computer.turnOff();
    }

    @Override
    public void update() {
        this.checkProcess();
    }

    @Override
    public int getSequenceNumber() {
        return 0;
    }

    private void checkProcess() {
        List<String[]> processList = this.getProcessListByArrivalTime(this.readText.getListText());
        processList.forEach(this::sendRequestToOS);
    }

    private List<String[]> getProcessListByArrivalTime(List<String[]> processList) {
        return processList.stream().filter(process -> process[0].equals(Long.toString(OurTime.elapsedTime))).toList();
    }

    private void sendRequestToOS(String[] process) {
        computer.getOS().requestForProcessCreate(process);
    }
}
