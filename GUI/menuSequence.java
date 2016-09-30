/*  this is creating a panel for selecting the termination type   */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.*;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;

public class menuSequence extends JPanel{
  public JLabel lab;
  public JButton confirm;
  public JButton reset;
  public JComboBox<String> selection;
  public JTextField numeric;   // later, we would need the value from this textbox

  /*  diable these methods. Because the inputs needs to be used directly from queueWindow
  public String itemInput;
  public double termiTime;
  public int termiCusNum;
  */

  /* read the input from the box
  private class boxHandler implements ActionListener{
    public void actionPerformed(ActionEvent eb){
      itemInput =(String) selection.getSelectedItem();
    }
  }*/

  private class resetButtonHandler implements ActionListener{
    public void actionPerformed(ActionEvent et){
      selection.setEnabled(true);
      numeric.setEditable(true);
    }
  }

  private class confirmButtonHandler implements ActionListener{
    public void actionPerformed(ActionEvent ev){
      if(selection.getSelectedIndex() == 0){
        String s = numeric.getText();
        try{
          Double.parseDouble(s);
          //termiTime = Double.parseDouble(s);
        } catch(NumberFormatException en){
          numeric.setText("Not a Number");
        }
      }

      if(selection.getSelectedIndex() == 1){
        String s =numeric.getText();
        try{
          Integer.parseInt(s);
          //termiCusNum = Integer.parseInt(s);
        }catch(NumberFormatException en){
          numeric.setText("Not a Number");
        }
      }
      selection.setEnabled(false);
      numeric.setEditable(false);
    }
  }

  public menuSequence(String instruct, String[] s){

    this.lab = new JLabel(instruct,JLabel.LEFT);
    add(lab);
    this.selection = new JComboBox<String>(s);
    //boxHandler ch = new boxHandler();
    //selection.addActionListener(ch);
    add(selection);
    this.numeric = new JTextField("",10);
    add(numeric);
    this.confirm = new JButton("confirm");
    confirmButtonHandler cb = new confirmButtonHandler();
    confirm.addActionListener(cb);
    add(confirm);


    // this won't do. When the object is created, all the numbers are given null values
    this.reset = new JButton("reset");
    resetButtonHandler r = new resetButtonHandler();
    reset.addActionListener(r);
    add(reset);
  }

  // define the actions for this selection menus

  public static void main(String[] args){
    JFrame jTest = new JFrame("Menu Bar Test");
    jTest.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    jTest.setLayout(new GridLayout(0,1));
    String[] s = {"Customer Number","Simulation Time"};
    menuSequence m = new menuSequence("Termination Method: ",s);
    jTest.add(m);
    jTest.setSize(500,500);
    jTest.setVisible(true);
  }

}
