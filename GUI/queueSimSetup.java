/*  this class gathers all the necessary components for starting a m/m/c/c queue simulation  */

public class queueSimSetup{
  public int serverNum;
  public int queueCapacity;
  public int replicationNum;
  public double serviceRate;
  public double arrivalRate;
  public String queueRule;
  public String termiRule;

  public double termiTime = 0.0;
  public int termiNum = 0;

  public queueSimSetup(){};  // the constructor does nothing

  // write a toString method, which will output all the set up for our queue

  public String toString(){
    return "Service Rate: "+serviceRate+"\n"+"Arrival Rate: "+arrivalRate+"\n"
            + "Number of Servers: "+serverNum+"\n"+ "Qeueue Rule :" + queueRule
            +"\n" +"Termination Time: "+ termiTime +"\n"
            +"Termination Number: " + termiNum +"\n"  ;
  }

  public void setTermiRule(String s){
    termiRule = s;
  }

  public void setReplicationNum(int n3){
    replicationNum = n3;
  }

  public void setserverNum(int n1){
    serverNum = n1;
  }

  public void setqueueCapacity(int n2){
    queueCapacity = n2;
  }

  public void setserviceRate(double r1){
    serviceRate = r1;
  }

  public void setarrivalrate(double r2){
    arrivalRate = r2;
  }

  public void setqueueRule(String s){
    queueRule = s;
  }

  public void setTermiTime(double r3){
    termiTime = r3;
  }

  public void setTermiNum(int n3){
    termiNum = n3;
  }

  public static void main(String[] args){
    queueSimSetup s = new queueSimSetup();
    s.setarrivalrate(0.4);
    s.setTermiTime(0.5);
    s.setTermiNum(5);
    System.out.println(s.arrivalRate);
    System.out.println(s.termiTime);
    System.out.println(s.termiNum);
    System.out.println(s.toString());
  }

}
