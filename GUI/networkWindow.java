// this is the overall GUI for setting up our network. This is a big big file reading in too much information

// the basic information: number of nodes, and edges and corresponding probabilities
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JFrame;

public class networkWindow extends JFrame{

  private JPanel overAll;

  private JPanel networkPane;  // using gridlayout, containing node input(num, rates), edge input, and simulation input
  private JPanel nodePane; // using flowlayout, an input sequence
  private JPanel nodeRatePane;
  private JPanel edgePane; // using flowlayout, an input sequence
  private JPanel queuePane; // not used
  private networkDisp disFrame;  // use a borderlayout, seems ok

  private JTextField numNodes;
  private JButton numNodesConfirmButton;
  private JButton addEdge;
  private JButton networkFinish;

  private JLabel nodeCount;
  private JTextField nodeRateCount;
  private JTextField nodeRate;
  private JButton nodeRateConfirm;

  private JPanel t;
  private JTextField termiT;

  // this part is for creating the edge speficication for the network
  private JTextField startingNode;
  private JTextField endingNode;
  private JTextField transProb;
  private JButton addConfirm;

  private int nodesNum;

  // this is the final, start simulation buttons
  private JPanel starts;
  private JButton startSim;

  // this is reading in all the edge speficications, and then display the information
  // as well as remove the current input panel
 private class addConfirmhandler implements ActionListener{
    // reading in the nodes specification
    public void actionPerformed(ActionEvent e3){

      // check both nodes inputs to be integers
      boolean v1, v2, v3;
      try{
        Integer.parseInt(startingNode.getText());
        int n1 = Integer.parseInt(startingNode.getText());
        if(n1>nodesNum){
          startingNode.setText("Not Valid Node Index");
          v1 = false;
        }
        else{
            v1 = true;
        }
      }catch(NumberFormatException en){
        startingNode.setText("Not an Integer");
        v1 = false;
      }

      try{
        Integer.parseInt(endingNode.getText());
        int n2 = Integer.parseInt(endingNode.getText());
        if(n2 > nodesNum){
          endingNode.setText("Not Valid Node Index");
          v2 =false;
        }
        else{
          v2 = true;
        }
      }catch(NumberFormatException ex){
        startingNode.setText("Not an Integer");
        v2 = false;
      }

      try{
        Double.parseDouble(transProb.getText());
        if(Double.parseDouble(transProb.getText()) <= 1 & Double.parseDouble(transProb.getText())>=0 ) v3 =true;
        else{
          v3 = false;
          transProb.setText("Not a Valid Probability");
        };
      }catch(NumberFormatException ex2){
        transProb.setText("Not Valid Probability");
        v3 =false;
      }

      if(v1&v2&v3){
        /*System.out.println("Node "+Integer.parseInt(startingNode.getText()) +" to "
        + "Node "+Integer.parseInt(endingNode.getText())+ " With Probability "+
        Double.parseDouble(transProb.getText()) );*/
        disFrame.newDisp("Starting Node: "+ Integer.parseInt(startingNode.getText())
        + "       Ending Node: "+ Integer.parseInt(endingNode.getText())
        + "       Transition Probability: "+Double.parseDouble(transProb.getText()),2 );

        startingNode.setText("");endingNode.setText("");transProb.setText("");
      }

    }
  }

  // node rate input handler. Checking valid inputs and print out the information
  private class nodeCCHandler implements ActionListener{
    public void actionPerformed(ActionEvent es){
      boolean v1,v2;
      int nd = 0 ;
      double nr = 0;
      try{
          Integer.parseInt(nodeRateCount.getText());
          nd = Integer.parseInt(nodeRateCount.getText());
          if(nd>nodesNum) {
            v1 = false;
            nodeRateCount.setText("Not Valid Node");
          }
          else{
            v1 = true;
            nodeRateCount.setText("");
          }
      }catch(NumberFormatException e){
        v1 = false;
        nodeRateCount.setText("Not an Integer");
      }

      try{
        Double.parseDouble(nodeRate.getText());
        nr = Double.parseDouble(nodeRate.getText());
        v2 = true;
      }catch(NumberFormatException ec){
        v2 = false;
        nodeRate.setText("Not a Number");
      }

      if(v1&v2){
        disFrame.newDisp("Service Rate for Node "+nd+" = "+nr,1);
        nodeRateCount.setText("");
        nodeRate.setText("");
      }

    }
  }


/*  constructor for the network set up window */


  public networkWindow(){
      setTitle("Setting Up the Network");
      setDefaultCloseOperation(DISPOSE_ON_CLOSE);

      // overall set up window and display window
      this.overAll = new JPanel(new GridLayout(0,2) );
      this.networkPane = new JPanel(new GridLayout(0,1));
      this.disFrame = new networkDisp();

      // panel for node number input
      this.nodePane = new JPanel(new FlowLayout());
      nodePane.setBorder(BorderFactory.createLineBorder(Color.green));
      nodePane.add(new JLabel("Put in Number of Nodes:"));
      this.nodesNum = 0;
      this.numNodes = new JTextField("",10);
      nodePane.add(numNodes);
      // button for confirm the input
      this.numNodesConfirmButton = new JButton("Confirm");
      nodePane.add(numNodesConfirmButton);
      JButton numNodesReset = new JButton("Reset");
      numNodesReset.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e1){
          numNodes.setText("");
          numNodes.setEnabled(true);
        };
      });
      nodePane.add(numNodesReset);
      networkPane.add(nodePane);

      // panel for service rate at each node input
      this.nodeCount = new JLabel("Put in Service Rates for node: ");
      this.nodeRate = new JTextField(" Input Rate ",10);
      this.nodeRateCount = new JTextField("Input Node",5);
      this.nodeRateConfirm = new JButton("Confirm & Continue");
      this.nodeRatePane = new JPanel(new FlowLayout());
      nodeRatePane.setBorder(BorderFactory.createLineBorder(Color.green));
      nodeCCHandler ncc = new nodeCCHandler();
      nodeRateConfirm.addActionListener(ncc);

      numNodesConfirmButton.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent et){
          try{
            Integer.parseInt(numNodes.getText());
            nodesNum = Integer.parseInt(numNodes.getText());
            numNodes.setEnabled(false);
            nodeRatePane.add(nodeCount);
            nodeRatePane.add(nodeRateCount);
            nodeRatePane.add(nodeRate);
            nodeRatePane.add(nodeRateConfirm);
            nodeRatePane.validate();
            validate();

            disFrame.newDisp("Number of Nodes: "+Integer.parseInt(numNodes.getText()),0);
          }catch(NumberFormatException ex){
            numNodes.setText("Not an Integer");
          }
        }
      });
      networkPane.add(nodeRatePane);

      // the edit edge button has its own panel
      this.addEdge = new JButton("Edit Edges");
      JPanel eb = new JPanel(new FlowLayout());
      eb.setBorder(BorderFactory.createLineBorder(Color.green));
      eb.add(addEdge);
      networkPane.add(eb);

      // panel for edge set up input
      this.edgePane = new JPanel(new FlowLayout());
      edgePane.setBorder(BorderFactory.createLineBorder(Color.green));
      networkPane.add(edgePane);
      this.startingNode = new JTextField("",10);
      this.endingNode = new JTextField("",10);
      this.transProb = new JTextField("",10);

      this.addConfirm = new JButton("Confirm Edge");
      addConfirmhandler ac = new addConfirmhandler();
      addConfirm.addActionListener(ac);
      this.networkFinish = new JButton("Finish Edge Set Up");


      // a button for iteratively adding in the edges

      addEdge.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e2){
          edgePane.add(new JLabel("startingNode"));
          edgePane.add(startingNode);
          edgePane.add(new JLabel("endingNode"));
          edgePane.add(endingNode);
          edgePane.add(new JLabel("Transition Probability"));
          edgePane.add(transProb);
          edgePane.add(addConfirm);
          edgePane.add(networkFinish);
          networkPane.add(edgePane);
          networkPane.revalidate();
          validate();
        }
      });

      // terminating condition panel
      this.t = new JPanel(new FlowLayout());
      t.setBorder(BorderFactory.createLineBorder(Color.green));
      t.add(new JLabel("Termination Time:"));
      this.termiT = new JTextField("",7);
      JButton termiTconfirm = new JButton("Confirm");
      JButton treset =  new JButton("Reset");
      t.add(termiT); t.add(termiTconfirm); t.add(treset);
      termiTconfirm.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent ess){
          try{
            Double.parseDouble(termiT.getText());
            termiT.setEditable(false);
          }catch(NumberFormatException en){
            termiT.setText("Not a Number");
          }
        }
      });
      treset.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent exx){
          termiT.setText("");
          termiT.setEditable(true);
        }
      });

      // only add the termination time set up after setting up the network
      networkFinish.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent esx){
          networkPane.add(t);
          networkPane.revalidate();
          validate();
        }
      });



      // the startSim button should collect all inputs and start the simulation
      this.starts = new JPanel(new FlowLayout());
      starts.setBorder(BorderFactory.createLineBorder(Color.green));
      this.startSim = new JButton("Start simulation");
      starts.add(startSim);

      // only add the start simulation button after fisnish set up
      termiTconfirm.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent evc){
          networkPane.add(starts);
          networkPane.revalidate();
          validate();
        }
      });

      startSim.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent eff){
          System.out.println("Our simulation has now begun");
        }
      });



      // the last panel for setting up simulation standards and start the simulation
      networkPane.setBorder(BorderFactory.createLineBorder(Color.red));
      disFrame.setBorder(BorderFactory.createLineBorder(Color.red));
      overAll.add(networkPane);
      overAll.add(disFrame);

      add(overAll,BorderLayout.CENTER);
      setVisible(true);
      setSize(1000,500);
  }

  public static void main(String[] args){
    networkWindow nw = new networkWindow();
  }

}
