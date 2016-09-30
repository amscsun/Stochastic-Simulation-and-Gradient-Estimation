/* this is a list of customers, namely, the queue. Both FCFS and service time priority queue are enabled */
import java.util.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.lang.*;


public class WaitQueue{

  private ArrayList<Customer> waitingCustomers;
  private int queueCapacity;
  private int cusNum;

  public WaitQueue(int n){
      this.waitingCustomers = new ArrayList<Customer>();
      this.cusNum = 0;
      this.queueCapacity = n;
  }

  public boolean isEmpty(){
    if(cusNum>0) return false;
    else return true;
  }

  public Customer enterQueue(){
    cusNum = cusNum -1;
    Customer c = waitingCustomers.get(0);
    waitingCustomers.remove(0);
    return c;
  }

  public boolean add(Customer c, double t){
    if(cusNum < queueCapacity){
      c.enterQueue(t);
      waitingCustomers.add(c);
      cusNum = cusNum + 1;
      return true;
    }
    else{
      return false;
    }
  }

  // above are the most important functions.
  public void sortByServiceTime(){
    Collections.sort(waitingCustomers, new serviceTimeCompare());
  }

    private class serviceTimeCompare implements Comparator<Customer>{
      public int compare(Customer o1, Customer o2){
        if(o1.getserviceTime() == o2.getserviceTime()) return 0;
        else if(o1.getserviceTime() > o2.getserviceTime()) return 1;
        else return -1;
      }
    }

    public static void main(String args[]){
      // our queue has capacity 3
      WaitQueue q = new WaitQueue(3);
      // test if our queue reject excessive customers
      for(int i=0;i<5;i++){
        Customer c = new Customer(Math.random(),Math.random());
        if(q.add(c,i*0.1)){
          System.out.println("Custoer is added at "+i*0.1);
        }
        else{
          System.out.println("Customer "+i+" is not added to queue");
        }
      }
      q.sortByServiceTime();

      System.out.println(q.waitingCustomers);

    }
}
