package main.java.utility.impl;

import main.java.utility.abstracts.IReadText;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadText implements IReadText {

    private final List<String[]> data=new ArrayList<>();
    @Override
    public void read(String path) {
        try(BufferedReader bufferedReader=new BufferedReader(new FileReader(path))){
            String line;
            while((line=bufferedReader.readLine())!=null)
            {
                String[] numbers=line.split(", ");
                data.add(numbers);
            }
        }catch(IOException e)
        {
            System.out.println("Error while reading file! Message: "+e.getMessage());
        }
    }

    @Override
    public List<String[]> getListText() {
        return data;
    }
}
