import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        final String vowels = "aeiouAEIOU";
        final String simpleText = "simple.txt";
        final String nothingGoldCanStay = "verses.txt";

        /** simple.txt :
         * A blue breeze.
         */

        long count = 0;
        try {
            count = Files.lines(Paths.get(simpleText))
                    .flatMap(line -> Arrays.stream(line.split("")))
                    .filter(c -> vowels.contains(c))
                    .count();
        } catch (IOException exception) {
            System.out.println("IO exception has occurred: " + exception.getMessage());
        } finally {
            System.out.println("The vowel count in the text is " + count);
        }
    }
}