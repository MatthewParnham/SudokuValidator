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
        return val.getKey();
      }
    }
    return "0";
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
    } catch(Exception e) {

    }

    int rows = records.size();
    int cols = records.get(0).size();
/*
    printGrid(records);
    System.out.println();
    printList(getRow(records,0,0));
    printList(getColumn(records,0,0));
    printList(getCube(records,0,0));
    System.out.println(validateList(getRow(records,0,0)));
    //printList(records);
    // records.row().col() so updown().leftright()
    //System.out.println(records.get(1).get(0));
*/
    printGrid(records);
    //main loop
    for(int i = 0; i < 9; i++) {
      List<String> column = getColumn(records,0,i);
      String dupe = validateList(column);
      //System.out.println(dupe);
      if (!dupe.equals("0")) {
        int idx = column.indexOf(dupe);
        System.out.println(validateList(getRow(records,idx,i)));
        System.out.println(validateList(getCube(records,idx,i)));
        idx = column.lastIndexOf(dupe);
        System.out.println(validateList(getRow(records,idx,i)));
        System.out.println(validateList(getCube(records,idx,i)));
      }
    }


    //validate rows
    //loop through rows
      //validate each row

    //validate cols
    //loop through cols
      //validate each col

    //validate cubes
    //loop through cubes
      //validate each cube



  }

}
