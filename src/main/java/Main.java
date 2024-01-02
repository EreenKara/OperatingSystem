package main.java;

import main.java.pc.abstracts.IComputer;
import main.java.utility.impl.ComputerFactory;

import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.print("Enter Path: ");
        Scanner scanner = new Scanner(System.in);

        boolean isValid;
        String path;
        do{
            path = scanner.nextLine();
            Path enteredPath= FileSystems.getDefault().getPath(path);
            isValid= Files.exists(enteredPath);
            if(!isValid)
                System.out.print("Invalid Path!\nEnter Path: ");
        }while(!isValid);

        IComputer computer=new ComputerFactory().createComputer();
        IUser user=new User(computer,path);
        user.startComputer();

    }

}



