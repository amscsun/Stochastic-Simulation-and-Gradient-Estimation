//this class is for collecting all customers departed, then get all the nformation necessary from here

import java.util.*;

public class departedCustomer{
  public ArrayList<Customer> dc;

  // once a customer departs, it is added to this ArrayList
  public void add( Customer c ){
    dc.add(c);
  }

  // get a function for calculating the gradient estimator
  public void gradient(){

  }

  // get a function to get the system time of all customer
  public void totalTime(){

  }

  // get queueing time (waited in queue)
  public void qTime(){

  }

  public departedCustomer(){
    this.dc = new ArrayList<Customer>();
  }

  public static void main(String[] args){
    departedCustomer d = new departedCustomer();

    for(int i=0;i<4;i++){
      Customer c = new Customer(Math.random(),Math.random());
      d.add(c);
    }

    System.out.println(d.dc);

  }

  }
