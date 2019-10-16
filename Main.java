import java.io.*;

public class Main {

  public static void main(String[] args) {

    //read in file
    String fName = args[0];
    String thisLine;
    int count=0;
    FileInputStream fis;
    DataInputStream myInput;
    try {
      fis = new FileInputStream(fName);
      myInput = new DataInputStream(fis);
      if(myInput == null) {
        System.out.println("File Not Found");
        System.exit(0);
      }
    } catch (FileNotFoundException e) {
      System.out.println("File Not Found");
      System.exit(0);
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
