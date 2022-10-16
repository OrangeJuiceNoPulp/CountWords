import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.Map.Entry;

public class CountWords {
    public static void main(String[] args) throws Exception {

        TreeMap<String, Integer> wordCount = new TreeMap<>();

        String inputFilename = "";
        try {
        inputFilename = args[0];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("\nNo input file specified!");
            System.exit(1);
        }
        String outputFilename;
        try {
            outputFilename = args[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            outputFilename = "output.txt";
        }
        File file = new File(inputFilename);

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String word = scanner.nextLine();
                word = word.toLowerCase();

                if (wordCount.containsKey(word)) {
                    wordCount.put(word, wordCount.get(word) + 1);
                } else {
                    wordCount.put(word, 1);
                }

            }
            scanner.close();
        }

        catch (FileNotFoundException e) {
            System.out.println("\nThe input file was not found!");
            //e.printStackTrace();
            System.exit(1);
        }

        try (FileWriter fileWriter = new FileWriter(outputFilename)) {
            for (Entry<String, Integer> entry : wordCount.entrySet()) {
                fileWriter.write(entry.getKey() + ": " + entry.getValue() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Encountered an IOException.");
            e.printStackTrace();
        }

    }
}
