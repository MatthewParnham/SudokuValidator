import java.io.*;
import java.util.*;

public class Validator extends Thread {

  public String mode;
  public List<List<String>> records;
  public int row,col;
  public boolean isValid;


  public Validator() {
    mode = "row";
    records = null;
    row = 0;
    col = 0;
  }
  public Validator(List<List<String>> r, String m, int row, int col) {
    this.mode = m;
    this.records = r;
    this.row = row;
    this.col = col;
  }

  public void run() {

    if(mode.equals("row")) {
      String dupes = validateList(getRow(records,row,col));
      isValid = dupes.equals("0");
    }


    else if(mode.equals("column")) {
      String dupes = validateList(getColumn(records,row,col));
      isValid = dupes.equals("0");
    }


    else if(mode.equals("cube")) {
      String dupes = validateList(getCube(records,row,col));
      isValid = dupes.equals("0");
    }
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
