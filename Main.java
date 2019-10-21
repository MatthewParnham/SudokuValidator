import java.io.*;
import java.util.*;

public class Main {

  public static void printGrid(List<List<String>> in) {
        for (List<String> list : in) {
          for (String elem : list) {
            System.out.print(elem + " ");
          }
          System.out.print("\n");
        }
  }
  public static void printList(List<String> list) {
    for(String elem : list) {
      System.out.print(elem + " ");
    }
    System.out.print("\n");
  }

  public static void main(String[] args) {

    //read in file
    String fName = args[0];
    //System.out.println(fName);

    List<List<String>> records = new ArrayList<>();
    try (BufferedReader br = new BufferedReader(new FileReader(fName))) {
        String line;
        while ((line = br.readLine()) != null) {
            String[] values = line.split(",");
            records.add(Arrays.asList(values));
        }
    } catch(Exception e) {}

    printGrid(records);
    //main loop

    //check columns
    Validator cols = new Validator(records, "columns");
    cols.start();
    //check rows
    Validator rows = new Validator(records, "rows");
    rows.start();

    //check cubes
    //Validator cubes = new Validator(records, "cubes");
    //cubes.start();

    try {
      cols.join();
      rows.join();
      //cubes.join();
    } catch(Exception e) {}

    System.out.println();
    System.out.println("Solved Puzzle:");
    printGrid(records);
  }
}
