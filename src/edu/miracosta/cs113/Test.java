package edu.miracosta.cs113;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.function.BiFunction;

public class Test
{
    public static void main(String[]args)
    {
        BigInteger largeNumber = new BigInteger();
        try {
        File file = new File("src/edu/miracosta/cs113/Largesum.txt");
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        StringBuffer stringBuffer = new StringBuffer();
        String line;
        while ((line = bufferedReader.readLine()) != null)
        {
            largeNumber.add(line);
        }
        fileReader.close();
    } catch (IOException e)
        {
        e.printStackTrace();
    }

    System.out.println(largeNumber);


    }
}
