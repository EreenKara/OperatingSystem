package main.java;

import main.java.pc.abstracts.IComputer;
import main.java.pc.impl.Computer;
import main.java.utility.abstracts.IReadText;
import main.java.utility.impl.OurTime;
import main.java.utility.impl.Publisher;

import java.util.List;

public class User implements IUser {

    private final IReadText readText;

    private final IComputer computer;

    public User(IReadText readText) {
        this.readText = readText;
        this.computer = new Computer();
    }

    @Override
    public void startComputer() {
        computer.turnOn();
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
        return 1;
    }

    private void checkProcess() {
        List<String[]> processList = this.getProcessListByArrivalTime(this.readText.getListText());
        processList.forEach(this::sendRequestToOS);
    }

    private List<String[]> getProcessListByArrivalTime(List<String[]> processList) {
        return (List<String[]>) processList.stream().filter(process -> process[0] == Long.toString(OurTime.elapsedTime));
    }

    private void sendRequestToOS(String[] process) {
        computer.getOS().requestForProcessCreate(process);
    }
}
