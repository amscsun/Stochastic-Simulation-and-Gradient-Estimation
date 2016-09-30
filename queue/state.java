// this class is for recording (or writing) the simulation sample path
// each simulation can generate multiple replications. Therefore, we need an arraylist of Files and Filewriters
import java.util.*;
import java.io.*;
import java.lang.Math.*;

public class state{
  private int n;
  private double t;
  private int eventId;
  private File dir;
  private File result;
  private FileWriter fw;

  public void arrivalUpdate(double t1) throws IOException{
    n = n+1;
    t = t1;
    eventId = 1;
    fw.write(n+"   "+t+"   "+eventId+"\n");
  }

  public void writeString(String s) throws IOException{
    fw.write(s+"\n");
  }

  public void departureUpdate(double t2) throws IOException{
    n = n-1;
    t = t2;
    eventId = -1;
    fw.write(n+"   "+t+"   "+eventId+"\n");
  }

  public void close() throws IOException{
    fw.close();
  }

  public void addFile(int i) throws IOException{
    n = 0;
    t = 0;
    eventId = 0;
    result = new File(dir,"r"+i+"txt");
    fw = new FileWriter(result);
  }

  public state() throws IOException{
    // create a directory for writing the results
    this.dir = new File("Output");
    dir.mkdir();
  }

  public static void main(String[] args) throws IOException{
    state record = new state();
    record.addFile(1);
    record.writeString("First test of Output");
    record.arrivalUpdate(Math.random());
    record.arrivalUpdate(Math.random());
    record.departureUpdate(Math.random());
    record.close();
    record.addFile(2);
    record.writeString("Second test of Output");
    record.arrivalUpdate(Math.random());
    record.arrivalUpdate(Math.random());
    record.departureUpdate(Math.random());
    record.close();
  }

}
