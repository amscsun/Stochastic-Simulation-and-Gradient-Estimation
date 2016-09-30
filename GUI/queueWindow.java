import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JFrame;

public class queueWindow extends JFrame{

  // components for building the GUI
  private inputSequenceDouble arrivalPane;
  private inputSequenceDouble servicePane;
  private inputSequenceInt capacityPane;
  private inputSequenceInt serverNumPane;
  private menuSequence termiPane;
  private inputSequenceInt replicationNumPane;

  // components for collecting the inputs and setting up the simulation
  private queueSimSetup queueInputs;

  /*  this chunk of code is dealing with reading in all the inputs and start the simulation program   */
  private class startQueueSimHandler implements ActionListener{
    public void actionPerformed(ActionEvent et){

      queueSimSetup queueInputs = new queueSimSetup();

      queueInputs.setarrivalrate(arrivalPane.doubleInput);
      queueInputs.setserviceRate(servicePane.doubleInput);
      queueInputs.setqueueCapacity(capacityPane.intInput);
      queueInputs.setserverNum(serverNumPane.intInput);
      queueInputs.setReplicationNum(replicationNumPane.intInput);
      queueInputs.setqueueRule("FCFS"); // this is to be edited. For now, we just use FCFS

      // reading in termination information seems a little bit harder
      if(termiPane.selection.getSelectedIndex() == 0){
        queueInputs.setTermiNum(Integer.parseInt(termiPane.numeric.getText()));
      }
      if(termiPane.selection.getSelectedIndex() == 1){
        queueInputs.setTermiTime(Double.parseDouble(termiPane.numeric.getText()));
      }

      /****** This is the main queueing simulation program   ***********/
      queueSim job = new queueSim(queueInputs);
      job.queueSimProgram();

      // the simulation program is triggered in this start simulation button

    }
  }


  public queueWindow(){
    setTitle("Setting Up the Queueing Simulation");

    // construct the input panel
    JPanel qPane = new JPanel();
    qPane.setLayout(new BoxLayout(qPane, BoxLayout.PAGE_AXIS));
    this.arrivalPane = new inputSequenceDouble("Arrival Rate","Confirm");
    qPane.add(arrivalPane);
    this.servicePane = new inputSequenceDouble("Service Rate","Confirm");
    qPane.add(servicePane);
    this.capacityPane = new inputSequenceInt("Queue Capacity","Confirm");
    qPane.add(capacityPane);
    this.serverNumPane = new inputSequenceInt("Number of Servers","Confirm");
    qPane.add(serverNumPane);
    this.replicationNumPane = new inputSequenceInt("Number of Simulation Replications:","Confirm");
    qPane.add(replicationNumPane);

    String[] s = {"Customer Number","Simulation Time"};
    this.termiPane = new menuSequence("Termination Methods: ", s);
    qPane.add(termiPane);

    // the button to collect all the result, and start the Simulation
    JButton startSim = new JButton("Start Simulation");
    startQueueSimHandler sh = new startQueueSimHandler();
    startSim.addActionListener(sh);

    qPane.add(startSim);

    add(qPane);
    setSize(800,700);
    setVisible(true);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
  }

  public static void main(String[] args){
    queueWindow w = new queueWindow();
  }

}
