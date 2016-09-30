// this is a class for getting all the necessary times of the System
import java.util.*;

public class sysTime{

// c1 should be the next customer to enter the System
// c2 should be the customer closest to departing

  public double currentTime;
  public int currentEvent;
  public double nextArrival;
  public double nextDeparture;

  public int eventId; // -1 for departure, +1 for Arrival

  public sysTime(Customer c){
    // the first event is always the arrival fo the first customer into the System
    this.currentTime = c.getinterArrivalTime();
    this.nextArrival = Double.POSITIVE_INFINITY;
    this.nextDeparture = c.getDepartTime();
    this.currentEvent = 1;
  }

  // this is deciding the next event, based on the current state of the System
  public int event(ArrayList<Customer> c, server s, int eventId){
    // if an arrival happens, and all updates are done accordingly
    if(eventId == 1){
      if(c.isEmpty()){
        nextArrival = Double.POSITIVE_INFINITY;
      }else{
        Customer c1 = c.get(0);
        nextArrival = currentTime + c1.getinterArrivalTime();
        nextDeparture = s.departureTime();
      }
    }
    // if a departure happens, then
    if(eventId == -1){
      nextDeparture = s.departureTime();
    }
    if(nextArrival > nextDeparture){
      currentTime = nextDeparture;
      return -1;
    }
    if(nextArrival < nextDeparture){
      currentTime = nextArrival;
      return 1;
    }
    else return 1;
  }

  // update is performed at the event function. Therefore, no separate update function is necessary
  /*public void arrivalUpdate(){
    currentTime = nextArrival;
  }

  public void departureUpdate(){
    currentTime = nextDeparture;
  }*/

  public String toString(){
    return "CurrentTime: "+ currentTime + "\n"
    + "Current Event: "+ currentEvent + "\n"
    + "netx arrival time: "+ nextArrival + "\n"
    +"next departure time: "+ nextDeparture + "\n";
  }

  public static void main(String[] args){
    // simulate the first customer in a simulation
    Customer c0 = new Customer(Math.random(),Math.random());
    // add this customer to server station with two servers
    server s = new server(2);
    c0.enterServer(c0.getinterArrivalTime());
    s.add(c0,1);
    sysTime clock = new sysTime(c0);
    System.out.println(c0);
    System.out.println(clock);
  }


}
