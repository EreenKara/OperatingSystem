package main.java;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        Queue<Integer> tet= new LinkedList<>();
        tet.add(1);
        tet.add(2);
        Iterator<Integer> iterator = tet.iterator();
        while (iterator.hasNext()) {
            Integer element = iterator.next();
            System.out.println(element);
        }
        // Generate random color
        while(true){
        String randomColor = generateRandomColor();
        // Use ANSI escape codes to set the font color in the console
        String test="\033[38;5;" + randomColor + "mHello, Random Colored Text!\033[0m";
        System.out.println(test);}

    }
    private static String generateRandomColor() {
        Random random = new Random();
        // ANSI color codes range from 0 to 255
        int colorCode = random.nextInt(256);
        return Integer.toString(colorCode);
    }

}



