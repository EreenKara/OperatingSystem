package main.java;

import java.util.Random;

public class Main {

    public static void main(String[] args) {

        // Generate random color
        String randomColor = generateRandomColor();

        // Use ANSI escape codes to set the font color in the console
        System.out.println("\033[38;5;" + randomColor + "mHello, Random Colored Text!\033[0m");

    }
    private static String generateRandomColor() {
        Random random = new Random();
        // ANSI color codes range from 0 to 255
        int colorCode = random.nextInt(256);
        return Integer.toString(colorCode);
    }

}



