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

    List<List<String>> records = new ArrayList<>();
    try (BufferedReader br = new BufferedReader(new FileReader(fName))) {
        String line;
        while ((line = br.readLine()) != null) {
            String[] values = line.split(",");
            records.add(Arrays.asList(values));
        }
    } catch(Exception e) {}

    System.out.println("\nFile: " + fName);
    System.out.println("Original Puzzle");
    printGrid(records);
    System.out.println();
    //main loop

    //check columns
    for(int i = 0; i < 9; i++) {
      List<String> column = getColumn(records,0,i);
      String dupe = validateList(column);
      if (!dupe.equals("0")) {
        for (int j = 0; j < dupe.length(); j++) {
          String letter = dupe.substring(j,j+1);
          int firstIdx = column.indexOf(letter);
          int secondIdx = column.lastIndexOf(letter);
          String missing = getMissing(column);
          Validator firstRow = new Validator(records, "row", firstIdx, i);
          firstRow.start();
          Validator firstCube = new Validator(records, "cube", firstIdx, i);
          firstCube.start();
          Validator secondRow = new Validator(records, "row", secondIdx, i);
          secondRow.start();
          Validator secondCube = new Validator(records, "cube", secondIdx, i);
          secondCube.start();
          try {
            firstRow.join();
            firstCube.join();
            secondRow.join();
            secondCube.join();
          }catch(Exception e) {}

          if(!firstRow.isValid || !firstCube.isValid) {
            System.out.println("In row " + String.valueOf(firstIdx + 1) + ", column " + String.valueOf(i + 1) + ", the " + letter + " should be a " + missing + ".");
            records.get(firstIdx).set(i, missing);
          }
          else if (!secondRow.isValid || !secondCube.isValid) {
            System.out.println("In row " + String.valueOf(secondIdx + 1) + ", column " + String.valueOf(i + 1) + ", the " + letter + " should be a " + missing + ".");
            records.get(secondIdx).set(i, missing);
          }
        }
      }
    }


    //check rows
    for(int i = 0; i < 9; i++) {
      List<String> row = getRow(records,i,0);
      String dupe = validateList(row);
      if (!dupe.equals("0")) {
        for (int j = 0; j < dupe.length(); j++) {
          String letter = dupe.substring(j,j+1);
          int firstIdx = row.indexOf(letter);
          int secondIdx = row.lastIndexOf(letter);
          String missing = getMissing(row);
          //System.out.println("Letter: " + letter + "\nfirstIdx: " + firstIdx + "\nsecondIdx: " + secondIdx + "\nmissing: " + missing);
          Validator firstCol = new Validator(records, "column", i, firstIdx);
          firstCol.start();
          Validator firstCube = new Validator(records, "cube", i, firstIdx);
          firstCube.start();
          Validator secondCol = new Validator(records, "column", i, secondIdx);
          secondCol.start();
          Validator secondCube = new Validator(records, "cube", i, secondIdx);
          secondCube.start();
          try {
            firstCol.join();
            firstCube.join();
            secondCol.join();
            secondCube.join();
          }catch(Exception e) {}
          if(!firstCol.isValid || !firstCube.isValid) {
            System.out.println("In row " + String.valueOf(i + 1) + ", column " + String.valueOf(firstIdx + 1) + ", the " + letter + " should be a " + missing + ".");
            records.get(i).set(firstIdx, missing);
          }
          else if(!secondCol.isValid || !secondCube.isValid) {
            System.out.println("In row " + String.valueOf(i + 1) + ", column " + String.valueOf(secondIdx + 1) + ", the " + letter + " should be a " + missing + ".");
            records.get(i).set(secondIdx, missing);
          }
        }
      }
    }

    System.out.println();
    System.out.println("Solved Puzzle:");
    printGrid(records);
  }

  public static List<String> getRow(List<List<String>> list, int row, int col) {
    List<String> result = new ArrayList<>();
    for(int i = 0; i < 9; i++) {
      result.add(list.get(row).get(i));
    }
    return result;
  }
  public static List<String> getColumn(List<List<String>> list, int row, int col) {
    List<String> result = new ArrayList<>();
    for(int i = 0; i < 9; i++) {
      result.add(list.get(i).get(col));
    }
    return result;
  }
  public static List<String> getCube(List<List<String>> list, int row, int col) {
    List<String> result = new ArrayList<>();
    if(row < 3) {
      if(col < 3) {
        for(int i = 0; i < 3; i++) {
          for(int j = 0; j < 3; j++) {
            result.add(list.get(i).get(j));
          }
        }
        return result;
      }
      else if(col < 6) {
        for(int i = 0; i < 3; i++) {
          for(int j = 3; j < 6; j++) {
            result.add(list.get(i).get(j));
          }
        }
        return result;
      }
      else { //col < 9
        for(int i = 0; i < 3; i++) {
          for(int j = 6; j < 9; j++) {
            result.add(list.get(i).get(j));
          }
        }
        return result;
      }
    }
    else if(row < 6) {
      if(col < 3) {
        for(int i = 3; i < 6; i++) {
          for(int j = 0; j < 3; j++) {
            result.add(list.get(i).get(j));
          }
        }
        return result;
      }
      else if(col < 6) {
        for(int i = 3; i < 6; i++) {
          for(int j = 3; j < 6; j++) {
            result.add(list.get(i).get(j));
          }
        }
        return result;
      }
      else { //col < 9
        for(int i = 3; i < 6; i++) {
          for(int j = 6; j < 9; j++) {
            result.add(list.get(i).get(j));
          }
        }
        return result;
      }
    }
    else { //row < 9
      if(col < 3) {
        for(int i = 6; i < 9; i++) {
          for(int j = 0; j < 3; j++) {
            result.add(list.get(i).get(j));
          }
        }
        return result;
      }
      else if(col < 6) {
        for(int i = 6; i < 9; i++) {
          for(int j = 3; j < 6; j++) {
            result.add(list.get(i).get(j));
          }
        }
        return result;
      }
      else { //col < 9
        for(int i = 6; i < 9; i++) {
          for(int j = 6; j < 9; j++) {
            result.add(list.get(i).get(j));
          }
        }
        return result;
      }
    }
  }

  public static String validateList(List<String> list) { //returns number that is duplicated. returns 0 if valid
    // hashmap to store the frequency of element
    Map<String, Integer> hm = new HashMap<String, Integer>();
    String result = "";

    for (String i : list) {
      Integer j = hm.get(i);
      hm.put(i, (j == null) ? 1 : j + 1);
    }

    // displaying the occurrence of elements in the arraylist
    for (Map.Entry<String, Integer> val : hm.entrySet()) {
      //System.out.println("Element " + val.getKey() + " "
        //               + "occurs"
          //             + ": " + val.getValue() + " times");
      if(val.getValue() > 1) {
        result += val.getKey();
      }
    }
    if(result.equals("")) {
      return "0";
    }
    else {
      return result;
    }
  }
  public static String getMissing(List<String> list) { //returns number that is duplicated. returns 0 if valid
    // hashmap to store the frequency of element
    Map<String, Integer> hm = new HashMap<String, Integer>();
    for(int i = 1; i < 10; i++) {
      hm.put(String.valueOf(i),0);
    }
    for (String i : list) {
      Integer j = hm.get(i);
      hm.put(i, (j == null) ? 1 : j + 1);
    }

    // displaying the occurrence of elements in the arraylist
    for (Map.Entry<String, Integer> val : hm.entrySet()) {
      //System.out.println("Element " + val.getKey() + " "
        //               + "occurs"
          //             + ": " + val.getValue() + " times");
      if(val.getValue() == 0) {
        return val.getKey();
      }
    }
    return "0";
  }
}
