import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JFrame;

public class startWindow extends JFrame{

  // pop out a new window
  public class queueButtonPop implements ActionListener{
    public void actionPerformed(ActionEvent e1){
      queueWindow w1 = new queueWindow();
      //System.exit(0);
    }
  }

  // pop out a new window
  public class networkButton implements ActionListener{
    public void actionPerformed(ActionEvent e2){
      networkWindow w = new networkWindow();
    }
  }
  
  public startWindow(){
    // first, perform some set up for the frame
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setTitle("Starting Window for Simulation");
    setVisible(true);
    setSize(800,300);
    setLocation(100,100);

    // add in the two buttons for the starting point
    JPanel startPanel = new JPanel();
    startPanel.setLayout(new BoxLayout(startPanel,BoxLayout.PAGE_AXIS));

    JLabel instruct = new JLabel("Plese Choose one Task");
    instruct.setFont(new Font("Serif",Font.PLAIN,50));
    instruct.setAlignmentX(startPanel.CENTER_ALIGNMENT);

    JButton queue = new JButton("Set Up a Queue");
    queue.setMaximumSize(new Dimension(300,100));
    queue.setAlignmentX(startPanel.CENTER_ALIGNMENT);
    queueButtonPop q = new queueButtonPop();
    queue.addActionListener(q);

    JButton network = new JButton("Set Up a Queueing Network");
    network.setMaximumSize(new Dimension(300,100));
    network.setAlignmentX(startPanel.CENTER_ALIGNMENT);
    networkButton n = new networkButton();
    network.addActionListener(n);

    startPanel.add(instruct,BorderLayout.NORTH);
    startPanel.add(queue,BorderLayout.CENTER);
    startPanel.add(network,BorderLayout.SOUTH);

    startPanel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

    add(startPanel);

  }

  public static void main(String[] args){
    startWindow w = new startWindow();
  }

}
