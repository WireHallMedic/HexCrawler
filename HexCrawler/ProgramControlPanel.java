package HexCrawler;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ProgramControlPanel extends JPanel implements ActionListener
{
   private JButton newB;
   private JButton saveB;
   private JButton loadB;
   
   public ProgramControlPanel()
   {
      super();
      setLayout(new GridLayout(1, 4));
      newB = new JButton("New Map");
      newB.addActionListener(this);
      add(newB);
      saveB = new JButton("Save");
      saveB.addActionListener(this);
      add(saveB);
      loadB = new JButton("Load");
      loadB.addActionListener(this);
      add(loadB);
   }
   
   public void actionPerformed(ActionEvent ae)
   {
   
   }
}