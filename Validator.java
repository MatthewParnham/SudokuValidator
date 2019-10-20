import java.io.*;
import java.util.*;

public class Validator extends Thread {

  public String mode;
  public List<List<String>> records;

  public Validator() {
    mode = "rows";
    records = null;
  }
  public Validator(List<List<String>> r, String m) {
    mode = m;
    records = r;
  }

  public void run() {

    if(mode.equals("rows")) {
      for(int i = 0; i < 9; i++) {
        List<String> row = getRow(records,i,0);
        String dupe = validateList(row);
        if (!dupe.equals("0")) {
          int firstIdx = row.indexOf(dupe);
          int secondIdx = row.lastIndexOf(dupe);
          String missing = getMissing(row);
          if(validateList(getColumn(records,i,firstIdx)).equals("0")) {
            System.out.println("In row " + String.valueOf(i + 1) + ", column " + String.valueOf(secondIdx + 1) + ", the " + dupe + " should be a " + missing + ".");
            //records.get(i).set(secondIdx, missing);
          }
          else {
            System.out.println("In row " + String.valueOf(i + 1) + ", column " + String.valueOf(firstIdx + 1) + ", the " + dupe + " should be a " + missing + ".");
            //records.get(i).set(firstIdx, missing);
          }


        }
      }
    }


    else if(mode.equals("columns")) {
      for(int i = 0; i < 9; i++) {
        List<String> column = getColumn(records,0,i);
        String dupe = validateList(column);
        if (!dupe.equals("0")) {
          int firstIdx = column.indexOf(dupe);
          int secondIdx = column.lastIndexOf(dupe);
          String missing = getMissing(column);
          if(validateList(getRow(records,firstIdx,i)).equals("0")) {
            System.out.println("In row " + String.valueOf(secondIdx + 1) + ", column " + String.valueOf(i + 1) + ", the " + dupe + " should be a " + missing + ".");
            //records.get(secondIdx).set(i, missing);
          }
          else {
            System.out.println("In row " + String.valueOf(firstIdx + 1) + ", column " + String.valueOf(i + 1) + ", the " + dupe + " should be a " + missing + ".");
            //records.get(firstIdx).set(i, missing);
          }


        }
      }
    }


    else if(mode.equals("cubes")) {
      for(int i = 0; i < 3; i++) {
        for(int j = 0; j < 3; j++) {
          List<String> cube = getCube(records,i*3,j*3);
          String dupe = validateList(cube);
          if (!dupe.equals("0")) {
            //System.out.println("DEBUG");
            int firstIdx = cube.indexOf(dupe);
            int firstRow,firstCol;
            switch(firstIdx):
              case "0":
                firstRow = 0;
                firstCol = 0;
                break;
              case "1":
                firstRow = 1;
                firstCol = 0;
                break;
              case "2":
            int secondIdx = cube.lastIndexOf(dupe);
            String missing = getMissing(cube);
            if(!validateList(getColumn(records,i,secondIdx)).equals("0") || !validateList(getRow(records,i,secondIdx)).equals("0")) {
              System.out.println("In row " + String.valueOf(i + 1) + ", column " + String.valueOf(secondIdx + 1) + ", the " + dupe + " should be a " + missing + ".");

            }
            else {
              System.out.println("In row " + String.valueOf(i + 1) + ", column " + String.valueOf(firstIdx + 1) + ", the " + dupe + " should be a " + missing + ".");

            }


          }
        }
      }
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
