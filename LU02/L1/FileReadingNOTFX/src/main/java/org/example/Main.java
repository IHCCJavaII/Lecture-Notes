package org.example;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static void main() {
        File file = new File("src/main/resources/smoothies.txt");

        try {
            //scanner attempts to open the file
            Scanner scanner = new Scanner(file);

            //only one line of text at a time
//            String firstLine = scanner.nextLine();
//            IO.println(firstLine);
//
//            String secondLine = scanner.nextLine();
//            IO.println(secondLine);

            //loop though files
            //Reminder ArrayList uses more memory
            ArrayList<String> smoothies = new ArrayList<>();

            scanner.useDelimiter(",");
            while (scanner.hasNext()) {
                String smoothie = scanner.next();
//                IO.println(smoothie);
                smoothies.add(smoothie);
            }

            smoothies.forEach(IO::println);

            //Lets format our data


            scanner.close();
        } catch (FileNotFoundException e){
            IO.println("File not found");
        }

    }
}
