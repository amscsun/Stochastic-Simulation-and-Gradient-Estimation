// this is for the testing of the classes
import java.util.*;
import java.io.*;
import java.io.File;
import java.io.IOException;

public class test{
  public static void main(String[] args) throws IOException{

    File test = new File("output");
    test.mkdir();

    File result = new File(test,"result.text");
    result.createNewFile();

    FileWriter fw = new FileWriter(result);

    for(int i=0;i<10;i++){
      fw.write("Hello World!\n");
    }
    fw.close();

  }
}
