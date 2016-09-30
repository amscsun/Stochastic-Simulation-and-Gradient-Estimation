/* this is a class for generating necessary random variables  */
import java.lang.Math;

public class randTimes{
  double randNumber;
  public randTimes(double r){
    this.randNumber = r;
  }
  public randTimes(){
    this.randNumber = Math.random();
  }

  public double expRandTimes(double rate){
    return -1/rate*Math.log(randNumber);
  }
  // for erlang distribution, common random number can not be implemented
  public double erlRandTimes(double rate, int n){
    double e = 0.0;
    for(int i=0;i<n;i++){
      e = e -1/rate*Math.log(Math.random());
    }
    return e;
  }

  public double uniRandTimes(double length){
    return randNumber*length;
  }

  public static  void main(String[] args){

    double r = Math.random();
    randTimes rtime = new randTimes(r);
    System.out.println(r);
    System.out.println("Exponential Random Variate: "+ rtime.expRandTimes(0.5) );
    System.out.println("Erlang Times :" + rtime.erlRandTimes(0.5,2));
    System.out.println("Uniform Time: "+ rtime.uniRandTimes(1));

  }

}
