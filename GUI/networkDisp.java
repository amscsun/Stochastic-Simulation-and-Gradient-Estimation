// this class is for display the network set up

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JFrame;

public class networkDisp extends JPanel{

  private JPanel title;
  private JPanel nodeRate;
  private JPanel edge;

  public void newDisp(String s, int n){
    if(n == 0){
      title.add(new JLabel(s));
      title.validate();
      validate();
    }
    if(n == 1){
      nodeRate.add(new JLabel(s));
      nodeRate.validate();
      validate();
    }
    if(n == 2){
      edge.add(new JLabel(s));
      edge.validate();
      validate();
    }
  }

  // constructor for the display
  public networkDisp(){
    this.title = new JPanel(new GridLayout(0,1));
    title.setBorder(BorderFactory.createLineBorder(Color.black));
    this.nodeRate = new JPanel(new GridLayout(0,1));
    nodeRate.setBorder(BorderFactory.createLineBorder(Color.black));
    this.edge = new JPanel(new GridLayout(0,1));
    edge.setBorder(BorderFactory.createLineBorder(Color.black));
    setLayout(new GridLayout(0,1));
    add(title);
    add(nodeRate);
    add(edge);
    setVisible(true);
  }
}
