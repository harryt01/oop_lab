package hust.soict.dsai.garbage;

import java.nio.file.Files;
import java.nio.file.Paths;

public class NoGarbage {
    public static void main(String[] args) {
        String filename = "lab03/OtherProjects/src/hust/soict/dsai/garbage/nggyu.txt"; // Replace with the path to a large file
        byte[] inputBytes = {0};
        long startTime, endTime;

        try {
            inputBytes = Files.readAllBytes(Paths.get(filename));
        } catch (Exception e) {
            System.err.println("File not found: " + e.getMessage());
            return;
        }

        startTime = System.currentTimeMillis();
        StringBuilder outputBuilder = new StringBuilder();

        for (byte b : inputBytes) {
            outputBuilder.append((char) b); // Efficient concatenation
        }

        String outputString = outputBuilder.toString();
        endTime = System.currentTimeMillis();
        System.out.println("time taken with StringBuilder: " + (endTime - startTime) + " ms");
    }
}
