// this class is for getting the necessary information for a customers
import java.util.*;


public class Customer{

  // set numbers to be private so that it won't be modified once defined.
  private double serviceTime;
  private double interArrivalTime;
  private double enterqueueTime;
  private double enterserverTime;
  private double departTime;

  // define all functions needed to modify, track customer state according to different events.
  public double getserviceTime(){
    return serviceTime;
  }

  public double getinterArrivalTime(){
    return interArrivalTime;
  }

  public double getDepartTime(){
    return departTime;
  }

  public String toString(){
    return "service time: "+serviceTime+"\n"+"Inter Arrival Time: "+ interArrivalTime+"\n"
    +"Departure Time: " + departTime +"\n" ;
  }

  public void enterQueue(double t){
    enterqueueTime = t;
  }

  public void enterServer(double t){
    enterserverTime = t;
    departTime = t + serviceTime;
  }

  public void rejected(double t){
    enterqueueTime = t;
    departTime = t;
  }

  public double getSystemTime(){
    if(enterqueueTime > 0){
      return departTime - enterqueueTime;
    }
    if(enterqueueTime == 0 ){
      return departTime - enterserverTime;
    }
    else{
      return 0;
    }
  }

  public double getQueueTime(){
    if(enterqueueTime>0){
      return enterserverTime - enterqueueTime;
    }
    if(enterqueueTime == 0){
      return 0;
    }
    else{
      return 0;
    }
  }

  public Customer(double t1, double t2){
    this.serviceTime =t1;
    this.interArrivalTime = t2;
    this.enterqueueTime = 0;
  }

  public static void main(String[] args){
    Customer c1 = new Customer(0.1,0.2);
    Customer c2 = new Customer(0.3,0.4);

    ArrayList<Customer> q = new ArrayList<Customer>();
    q.add(c1);
    q.add(c2);
    System.out.println(q);
  }

}
