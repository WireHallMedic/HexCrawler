package HexCrawler;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ProgramControlPanel extends JPanel implements ActionListener
{
   private JButton newB;
   private JButton saveB;
   private JButton loadB;
   private MainPanel parentPanel;
   
   public ProgramControlPanel(MainPanel parent)
   {
      super();
      parentPanel = parent;
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
      if(ae.getSource() == newB)
         parentPanel.newMap(getNewMapSize());
      else if(ae.getSource() == saveB)
         parentPanel.save();
      else if(ae.getSource() == loadB)
         parentPanel.load();
   }
   
   private int[] getNewMapSize()
   {
      int[] returnVals = {7, 7};
      String width = JOptionPane.showInputDialog("Width for new map:");
      String height= JOptionPane.showInputDialog("Height for new map");
      try
      {
         returnVals[0] = Integer.parseInt(width);
         returnVals[1] = Integer.parseInt(height);
      }
      catch(Exception ex){}
      return returnVals;
   }
}