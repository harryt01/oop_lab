package hust.soict.dsai.garbage;

import java.nio.file.Files;
import java.nio.file.Paths;

public class GarbageCreator {
    public static void main(String[] args) {
        String filename = "OtherProjects/src/hust/soict/dsai/garbage/nggyu.txt"; //test.exe is the name or path to an executable file byte[] inputBytes = { 0 };
        byte[] inputBytes = {0};
        long startTime, endTime;

        try {
            inputBytes = Files.readAllBytes(Paths.get(filename));
        } catch (Exception e) {
            System.err.println("File not found: " + e.getMessage());
            return;
        }

        startTime = System.currentTimeMillis();
        String outputString = "";

        for (byte b : inputBytes) {
            outputString += (char) b;
        }
        endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);
    }
    
}
