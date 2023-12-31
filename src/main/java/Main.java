package main.java;

import main.java.pc.abstracts.IComputer;
import main.java.utility.impl.ComputerFactory;

public class Main {

    public static void main(String[] args) {
        IComputer computer=new ComputerFactory().createComputer();
        IUser user=new User(computer);
        user.startComputer();

    }

}



