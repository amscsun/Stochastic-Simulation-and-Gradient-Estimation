// this class is for running the simulation from the GUI input
import java.util.*;
import java.io.*;

public class runSim{
  // a single constructor function which will read all the inputs and starts the simulation
  public runSim(queueSimSetup setUp){

    // set up directory and file for writing the Output
    state record = new state();

    // simulate replications
    for(int i=1;i<setUp.replicationNum+1;i++ ){
      // prepare the output files
      record.addFile(i);
      record.writeString("This is the "+i+"th simulation ");
      record.writeString("-------------------------------");
      record.writeString("N(t)         Time         Event");
      record.writeString("-------------------------------");

      // create all the customers for a single run
      ArrayList<Customer> c = new ArrayList<Customer>();
      for(int j=0;j<termiNum+100;j++){
        randTimes r = new randTimes(Math.random());
        Customer c1 = new Customer(r.expRandTimes(setUp.arrivalRate),r.expRandTimes(setUp.serviceRate));
      }
      // create queue, server, and departed customer container
      WaitQueue q = new WaitQueue(1000);
      server s = new server(1);
      departedCustomer d = new departedCustomer();
      // initialize the simulation with the first arrival
      sysTime clock = new sysTime(c.get(0));
      s.add(c.get(0),clock.currentTime);
      c.remove(0);
      int eventId = 1;

      // this is the actual simulation program
      while(c.size()>1){

        if(eventId == 1 ){
          record.arrivalUpdate(clock.currentTime);
          Customer c1 = c.get(0);
          c.remove(0);
          if(!s.add(c1,clock.currentTime)){
            if(!q.add(c1,clock.currentTime)){
              c1.rejected(clock.currentTime);
              d.add(c1);
            }
          }
        }

        if(eventId == -1){
          record.departureUpdate(clock.currentTime);
          d.add(s.remove());
          if(!q.isEmpty()){
            s.add(q.enterQueue(),clock.currentTime);
          }
        }

        eventId = clock.event(c,s,eventId);

      }
      //finish recoding the result
      record.close();

    }
    // above are the simulation program

  }

  public static void main(String[] args){

    queueSimSetup start = new queueSimSetup();
    start.serverNum = 1;
    start.queueCapacity =10;
    start.serviceRate =2;
    start.arrivalRate =2;
    start.queueRule = "SPT";
    start.termiTime = 0;
    start.termiNum = 100;
    start.replicationNum = 10;

    runSim job = runSim(start);

  }



}
