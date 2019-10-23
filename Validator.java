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
        //System.out.println("DUPE: " + dupe);
        if (!dupe.equals("0")) {
          for (int j = 0; j < dupe.length(); j++) {
            String letter = dupe.substring(j,j+1);
            int firstIdx = row.indexOf(letter);
            int secondIdx = row.lastIndexOf(letter);
            String missing = getMissing(row);
            if(validateList(getColumn(records,i,firstIdx)).equals("0")) {
              System.out.println("RIn row " + String.valueOf(i + 1) + ", column " + String.valueOf(secondIdx + 1) + ", the " + letter + " should be a " + missing + ".");
              //records.get(i).set(secondIdx, missing);
            }
            else {
              System.out.println("RIn row " + String.valueOf(i + 1) + ", column " + String.valueOf(firstIdx + 1) + ", the " + letter + " should be a " + missing + ".");
              //records.get(i).set(firstIdx, missing);
            }
          }
        }
      }
    }


    else if(mode.equals("columns")) {
      for(int i = 0; i < 9; i++) {
        List<String> column = getColumn(records,0,i);
        String dupe = validateList(column);
        if (!dupe.equals("0")) {
          for (int j = 0; j < dupe.length(); j++) {
            String letter = dupe.substring(j,j+1);
            int firstIdx = column.indexOf(letter);
            int secondIdx = column.lastIndexOf(letter);
            String missing = getMissing(column);
            if(validateList(getRow(records,firstIdx,i)).equals("0")) {
              System.out.println("CIn row " + String.valueOf(secondIdx + 1) + ", column " + String.valueOf(i + 1) + ", the " + letter + " should be a " + missing + ".");
              //records.get(secondIdx).set(i, missing);
            }
            else {
              System.out.println("CIn row " + String.valueOf(firstIdx + 1) + ", column " + String.valueOf(i + 1) + ", the " + letter + " should be a " + missing + ".");
              //records.get(firstIdx).set(i, missing);
            }
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
            //System.out.println("i : " + i*3 + " and j: " + j*3);
            int firstIdx = cube.indexOf(dupe);
            //System.out.println(firstIdx);
            int firstRow,firstCol;
            switch(firstIdx) {
              case 0:
                firstRow = 0;
                firstCol = 0;
                break;
              case 1:
                firstRow = 0;
                firstCol = 1;
                break;
              case 2:
                firstRow = 0;
                firstCol = 2;
                break;
              case 3:
                firstRow = 1;
                firstCol = 0;
                break;
              case 4:
                firstRow = 1;
                firstCol = 1;
                break;
              case 5:
                firstRow = 1;
                firstCol = 2;
                break;
              case 6:
                firstRow = 2;
                firstCol = 0;
                break;
              case 7:
                firstRow = 2;
                firstCol = 1;
                break;
              case 8:
                firstRow = 2;
                firstCol = 2;
                break;
              default:
                firstRow = 0;
                firstCol = 0;
                break;
              }
            int secondIdx = cube.lastIndexOf(dupe);
            int secondRow,secondCol;
            switch(firstIdx) {
              case 0:
                secondRow = 0;
                secondCol = 0;
                break;
              case 1:
                secondRow = 0;
                secondCol = 1;
                break;
              case 2:
                secondRow = 0;
                secondCol = 2;
                break;
              case 3:
                secondRow = 1;
                secondCol = 0;
                break;
              case 4:
                secondRow = 1;
                secondCol = 1;
                break;
              case 5:
                secondRow = 1;
                secondCol = 2;
                break;
              case 6:
                secondRow = 2;
                secondCol = 0;
                break;
              case 7:
                secondRow = 2;
                secondCol = 1;
                break;
              case 8:
                secondRow = 2;
                secondCol = 2;
                break;
              default:
                secondRow = 0;
                secondCol = 0;
                break;
              }
            String missing = getMissing(cube);
            if(!validateList(getColumn(records,secondRow,secondCol)).equals("0") || !validateList(getRow(records,secondRow,secondCol)).equals("0")) {
              System.out.println("In row " + String.valueOf((i*3)+ secondRow + 1) + ", column " + String.valueOf((j*3)+ secondCol + 1) + ", the " + dupe + " should be a " + missing + ".");

            }
            else {
              System.out.println("In row " + String.valueOf((i*3)+ firstRow + 1) + ", column " + String.valueOf((j*3)+ firstCol + 1) + ", the " + dupe + " should be a " + missing + ".");

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
