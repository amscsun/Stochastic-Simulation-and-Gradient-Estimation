// this is for the input of an integer

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.*;

import javax.swing.JFrame;
import javax.swing.JTextField;

// since it extends JPanel, it can be treated like JPanel for larger structures
public class inputSequenceInt extends JPanel{
  private JLabel inputInstruction;
  private JTextField inputTextField;
  private JButton confirmButton;
  private JButton reset;

  // this class is specifying the input we hope to get from this sequence
  public int intInput;

  // confirmation of double input
  private class confirmButtonHandlerInt implements ActionListener{
    public void actionPerformed(ActionEvent e){
      // fill in the event for the confirmation button
      String s = inputTextField.getText().replaceAll("\\s+","");
      System.out.println(s);
      boolean v;
      try{
        Integer.parseInt(s);
        v = true;
      } catch(NumberFormatException en){
        v = false;
      }

      if(v){
        intInput = Integer.parseInt(s);
        inputTextField.setEditable(false);
        System.out.println("Integer is "+ intInput);
        confirmButton.setEnabled(false);
      }
      if(!v){
        inputTextField.setText("Not an Integer, Please re-enter");
      }
      //System.exit(0);
    }
  }
  // the reset button handler
  private class resetButtonHandler implements ActionListener{
    public void actionPerformed(ActionEvent ev){
      inputTextField.setText("");
      inputTextField.setEditable(true);
      confirmButton.setEnabled(true);
    }
  }

  // now, construct a panel with double input
  public inputSequenceInt(String buttonLabel, String button ){
    // construct a label for the input
    setBorder(BorderFactory.createLineBorder(Color.blue));

    this.inputInstruction = new JLabel(buttonLabel,JLabel.LEFT);
    add(inputInstruction);

    // construct the input textbox
    this.inputTextField = new JTextField("   ",10);
    add(inputTextField);

    //construct the confirmation button
    this.confirmButton = new JButton(button);
    confirmButtonHandlerInt confirmed = new confirmButtonHandlerInt();
    confirmButton.addActionListener(confirmed);
    add(confirmButton);

    // construct the reset button
    this.reset = new JButton("reset");
    resetButtonHandler rt = new resetButtonHandler();
    reset.addActionListener(rt);
    add(reset);

  }

  // the test function for this input sequence
  public static void main(String[] args){
    System.out.println("Test of our Input Sequence Panel");
    JFrame jTest = new JFrame("Input Sequence Class Test");
    jTest.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    jTest.setLayout(new GridLayout(0,1));
    inputSequenceInt i1 = new inputSequenceInt("Arrival Rate:","confirm");
    inputSequenceInt i2 = new inputSequenceInt("Service Rate:","Confirm");
    jTest.add(i1);
    jTest.add(i2);
    jTest.setVisible(true);
    jTest.setSize(500,500);
    jTest.setLocation(100,100);

  }

}
