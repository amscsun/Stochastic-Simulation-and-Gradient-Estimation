// this is for testing the simulation program
import java.util.*;
import java.io.*;

public class simTest{

  public static void main(String[] args) throws IOException{

    int N = 100; double r1 = 3; double r2 = 2;  // setting up the condition for the simulation
    int ns = 1;

    // file for printing out the simulation result
    state record = new state();

    for(int j = 0; j<10;j++){
      // we run 10 simulations in total
      record.addFile(j);
      record.writeString("This is the "+j+"th Simulation");
      record.writeString("------------------------------");
      record.writeString("N(t)         Time        Even ");
      record.writeString("------------------------------");
      // create the file which contains our output

    // first create all the customers
    ArrayList<Customer> c = new ArrayList<Customer>();
    for(int i=0;i<N;i++){
      randTimes r = new randTimes(Math.random());
      Customer c1 = new Customer(r.expRandTimes(r1),r.expRandTimes(r2));
      c.add(c1);
    }
    //System.out.println(c);
    // create queue, server, and departed customers
    WaitQueue q = new WaitQueue(1000);
    server s = new server(1);
    departedCustomer d = new departedCustomer();

    // initialize the simulation
    sysTime clock = new sysTime(c.get(0));
    s.add(c.get(0),clock.currentTime);
    c.remove(0);

    // add the first arrival to our result file
    record.arrivalUpdate(clock.currentTime);

    //System.out.println(c);
    //System.out.println(s.servingCustomer);

    int eventId = 1;  // the first event is for sure arrival to the empty system

    while(c.size()>1){

      if(eventId == 1 ){

        // record an arrival event in our result
        record.arrivalUpdate(clock.currentTime);

        // update the system
        Customer c1 = c.get(0);
        c.remove(0);
        // three trials. Add to server, add to queue or, rejected to go to departed
        if(!s.add(c1,clock.currentTime)){
          if(!q.add(c1,clock.currentTime)){
            c1.rejected(clock.currentTime);
            d.add(c1);
          }
        }
      }

      if(eventId == -1){

        // record a departure event in our result
        record.departureUpdate(clock.currentTime);

        d.add(s.remove());   // remove a customer from servers
        if(!q.isEmpty()){    // if the queue is currently not empty, then one of the custoers enter servers and get served
          s.add(q.enterQueue(), clock.currentTime);
        }
      }

      // now, the system state is being updated, we have to decide the next event
      eventId = clock.event(c,s,eventId);

    }
    // finish the recording of the jth result
    record.close();

  }

  }

}
