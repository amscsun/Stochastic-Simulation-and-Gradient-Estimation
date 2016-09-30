// this class is for the node of our stochastic network

import java.util.*;
import java.lang.Math;

public class node{

   private class target{
     private int desNode;
     private double prob;
     public target(int n, double p){
       this.desNode = n;
       this.prob = p;
     }
   }
   private int index;
   private ArrayList<target> neighbor;

   public void addTarget(int n, double p){
     target t = new target(n,p);
     neighbor.add(t);
   }

   public int nextNode(){
     // use a random number generator to select the next target
     int des = 0;
     int s = neighbor.size();
     double tpl = 0; double tpu = 0;
     double rand = Math.random();
     // search through the transition probabilities. If none found, then depart
     if(s>0){
       for(int i=0;i<s;i++){
         target tn = (target) neighbor.get(i);
         tpu = tpl + tn.prob;
         if(tpl<= rand & tpu > rand){
           des = tn.desNode;
         }
         tpl = tpu;
       }
     }
     return des;
   }

   public node( int n1){
     this.index = n1;
     this.neighbor = new ArrayList<target>() ;
   }

   // a test function for this method
   public static void main(String[] args){
     // first create a node, with an index
     node nodeTest = new node(1);
     System.out.println("The test Node is indexed 1");
     // add some neighbors to this node, with certain probabilities

     nodeTest.addTarget(2,0.1);System.out.println("Node 2, with Probability 0.1");
     nodeTest.addTarget(3,0.4);System.out.println("Node 3, with Probability 0.4");
     nodeTest.addTarget(4,0.4);System.out.println("Node 4, with Probability 0.4");

     // now, we choose the next destination for 10 times
     for(int i=0;i<10;i++){
       System.out.println("The next Node is "+nodeTest.nextNode());
     }

   }

}
