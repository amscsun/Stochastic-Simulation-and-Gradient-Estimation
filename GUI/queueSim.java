// this class is the overall starting point for the simulation program.
// It reads in all the initial set up, and start running the simulation


public class queueSim{
  // a member for starting the simulation
  private queueSimSetup qs;

  public queueSim(queueSimSetup qs){
    this.qs = qs;
  }

  public void queueSimProgram(){
    // a test for starting the simulation program. Making sure we have got all necessary set up
    System.out.println(qs.toString());
  }

  public static void main(String[] args){
    queueSimSetup start = new queueSimSetup();
    start.serverNum = 1;
    start.queueCapacity =1;
    start.serviceRate =2;
    start.arrivalRate =1;
    start.queueRule = "SPT";
    start.termiTime = 0;
    start.termiNum = 100;
    queueSim job = new queueSim(start);
    job.queueSimProgram();
}
}
