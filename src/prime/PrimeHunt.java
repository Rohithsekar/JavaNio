package prime;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class PrimeHunt {

    public static final Integer EVEN_PRIME = 2;

    public static final Integer NEITHER_PRIME_NOR_COMPOSITE =1;

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int TERMINAL_NUMBER = scanner.nextInt();


        List<Integer> spawnsOfTheEvenPrime = new ArrayList<>();
        List<Integer> spawnsOfTheOddPrimes = new ArrayList<>();
        List<Integer> hybridSpawns = new ArrayList<>();
        List<Integer> theOddPrimes = new ArrayList<>();

        int evenComposites=0;
        int oddComposites=0;
        int hybridComposites =0;
        int oddPrimes = 0;

        int exponentiationCount = (int) Math.floor(Math.log(TERMINAL_NUMBER) / Math.log(2));

        for (int i = 1; i <= TERMINAL_NUMBER; i++) {
            if (i <= exponentiationCount) {
                int newEvenSpawn = (int) Math.pow(EVEN_PRIME, i);
                spawnsOfTheEvenPrime.add(newEvenSpawn);
                evenComposites++;
                System.out.println("new spawn of the even prime: " + newEvenSpawn);
            }

            if (i % 2 != 0) {
                boolean isPrime = true;
                for (Integer oddPrime : theOddPrimes) {
                    if (i % oddPrime == 0) {
                        spawnsOfTheOddPrimes.add(i);
                        oddComposites++;
                        System.out.println("new spawn of the odd primes: " + i);
                        isPrime = false;
                        break;
                    }
                }

                if (isPrime && i!=1) {
//                    primesToAdd.add(i);
                    theOddPrimes.add(i);
                    oddPrimes++;
                    System.out.println("new odd prime found: " + i);
                }
            } else {
                if(!spawnsOfTheEvenPrime.contains(i)){
                    hybridSpawns.add(i);

                    hybridComposites++;
                }
            }
        }

        System.out.println("There are " + evenComposites + " even Composites" );
        System.out.println("There are "  + oddComposites + " odd Composites ");
        System.out.println("There are "  +  hybridComposites + " hybrid Composites");
        System.out.println("There are " + oddPrimes + " odd primes");
        System.out.println("Total count :" + (evenComposites + oddComposites + hybridComposites + oddPrimes + NEITHER_PRIME_NOR_COMPOSITE));



        // Write the lists to an external file
        // Create a map to store list headers and corresponding lists
        Map<String, List<Integer>> listMap = new TreeMap<>();
        listMap.put("Even Prime Spawns", spawnsOfTheEvenPrime);
        listMap.put("Odd Prime Spawns", spawnsOfTheOddPrimes);
        listMap.put("Hybrid Spawns", hybridSpawns);
        listMap.put("Odd Primes", theOddPrimes);

        // Write lists to a single file with appropriate headers
        writeToFile("output.txt", listMap);

        System.out.println("Lists have been written to the file successfully!");
    }


    private static void writeToFile(String fileName, Map<String, List<Integer>> lists) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Map.Entry<String, List<Integer>> entry : lists.entrySet()) {
                String header = entry.getKey();
                List<Integer> numbers = entry.getValue();

                // Write header to the file
                writer.write(header);
                writer.newLine();

                // Write list elements to the file
                writer.write(Arrays.toString(numbers.toArray()));
                writer.newLine();
                writer.newLine(); // Add an extra newline to separate lists
            }

        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}