/*  this class is for the construction of specific combo box handlers.
ideally, they should pop out new windows according to selection in the
combobox   */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.*;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;

// this class is specifically set for termination set up. Makes it much easier to write
public class terminationSelectionResponse extends JPanel implements ActionListener{

  public JComboBox<String> jb;
  public terminationSelectionResponse(JComboBox<String> b){
    this.jb = b;
  }

  // define a constructor which gives null values to the input

  public void actionPerformed(ActionEvent et){
    // first, disable the initial combobox which made the choice
    jb.setEnabled(false);
    System.out.println(jb.getSelectedIndex());
    // then, according to different choise, pop out different information
    if(jb.getSelectedIndex() == 0){
        inputSequenceDouble tW1 = new inputSequenceDouble("Max Time:","Confirm");
        add(tW1);
    }
    if(jb.getSelectedIndex() == 1){
        inputSequenceInt tW1 = new inputSequenceInt("Max Num:","Confirm");
        add(tW1);
    }

  }
}
