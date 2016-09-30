// this class is for specifying the behavior of servers
import java.util.*;

public class server{
  public ArrayList<Customer> servingCustomer;
  public int serverNum;   // number of servers
  public int cusNum;       // number of customers being served

  public server(int n){
    this.servingCustomer = new ArrayList<Customer>();
    this.serverNum = n;
    this.cusNum = 0;
  }

  public double departureTime(){
    if(servingCustomer.isEmpty()){
      return Double.POSITIVE_INFINITY;
    }
    else{
      Customer c = servingCustomer.get(0);
      return c.getDepartTime();
    }
  }

  public void sort(){
    Collections.sort(servingCustomer, new departComparator());
  }

  // sort the customers being served by their departure time. So in departure, always remove the first customer
  private class departComparator implements Comparator<Customer>{
    public int compare(Customer c1, Customer c2){
      if(c1.getDepartTime() == c2.getDepartTime()) return 0;
      else if(c1.getDepartTime() > c2.getDepartTime()) return 1;
      else return -1;
    }
  }

  // every arriving customer should first be added to the servers. If no available, then added to queues
  public boolean add(Customer c, double t){
    if(cusNum < serverNum){
      c.enterServer(t);
      servingCustomer.add(c);
      cusNum = cusNum+1;
      sort();    // once a new customer is added, sorting is conducted immediately
      return true;
    }
    else{
      return false;
    }
  }

    //  departures happens at one of the servers. Remark: if one leaves queue, another should be added if possible
  public Customer remove(){
    cusNum = cusNum - 1;
    Customer c = servingCustomer.get(0);
    servingCustomer.remove(0);
    return c;
  }

  public static void main(String[] args){
    server s = new server(3); // 3 servers under work

    // we test if "excessive customers" is rejected by the servers
    // also, test if the customers are correctly sorted by their departure time. And the first to depart is removed
    for(int i=0;i<5;i++){
      Customer c = new Customer(Math.random(),Math.random());
      if(s.add(c,i*0.1)){
        System.out.println("Customer entered at "+ i*0.1);
      }
      else{
        System.out.println("Customer # "+i+" is not added");
      }
    }
    System.out.println(s.servingCustomer);
    s.remove();
    System.out.println(s.servingCustomer);

    // test if removing a customer really clear up space for new customers
    for(int i=0;i<3;i++){
      Customer c = new Customer(Math.random(),Math.random());
      if(s.add(c,i*0.2)){
        System.out.println("Customer is added at "+i*0.2);
      }
      else{
        System.out.println("Customer "+ i + " is not added");
      }
    }
    }
}
