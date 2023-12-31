package main.java;

import main.java.pc.abstracts.IComputer;
import main.java.utility.impl.ComputerFactory;
import main.java.utility.impl.OurTime;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        IComputer computer=new ComputerFactory().createComputer();
        IUser user=new User(computer);
        user.startComputer();

//        Queue<Integer> tet= new LinkedList<>();
//        tet.add(1);
//        tet.add(2);
//        Iterator<Integer> iterator = tet.iterator();
//        while (iterator.hasNext()) {
//            Integer element = iterator.next();
//            System.out.println(element);
//        }
//        // Generate random color
//        while(true){
//        String randomColor = generateRandomColor();
//        // Use ANSI escape codes to set the font color in the console
//        String test="\033[38;5;" + randomColor + "mHello, Random Colored Text!\033[0m";
//        System.out.println(test);}

    }

}



