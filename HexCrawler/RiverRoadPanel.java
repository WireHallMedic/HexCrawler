package HexCrawler;

import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;

public class RiverRoadPanel extends JPanel implements HexCrawlerConstants, ActionListener
{
   private JCheckBox[] drawCBList;
   private JRadioButton[] roadRBList;
   private JRadioButton[] riverRBList;
   private JButton[] clearBList;
   private MainPanel parentPanel;
   
   public RiverRoadPanel(MainPanel parent)
   {
      super();
      drawCBList = new JCheckBox[MAX_LINEAR_PATHS];
      roadRBList = new JRadioButton[MAX_LINEAR_PATHS];
      riverRBList = new JRadioButton[MAX_LINEAR_PATHS];
      clearBList = new JButton[MAX_LINEAR_PATHS];
      setLayout(new GridLayout(MAX_LINEAR_PATHS, 1));
      for(int i = 0; i < MAX_LINEAR_PATHS; i++)
         createSubpanel(i);
   }
   
   private void createSubpanel(int i)
   {
      JPanel anonPanel = new JPanel();
      anonPanel.setLayout(new GridLayout(1, 4));
      drawCBList[i] = new JCheckBox("Draw");
      roadRBList[i] = new JRadioButton("Road");
      riverRBList[i] = new JRadioButton("River");
      clearBList[i] = new JButton("Clear");
      ButtonGroup anonGroup = new ButtonGroup();
      anonGroup.add(roadRBList[i]);
      anonGroup.add(riverRBList[i]);
      drawCBList[i].addActionListener(this);
      roadRBList[i].addActionListener(this);
      riverRBList[i].addActionListener(this);
      clearBList[i].addActionListener(this);
      anonPanel.add(drawCBList[i]);
      anonPanel.add(roadRBList[i]);
      anonPanel.add(riverRBList[i]);
      anonPanel.add(clearBList[i]);
      roadRBList[i].setSelected(true);
      add(anonPanel);
   }
   
   public void actionPerformed(ActionEvent ae)
   {
      if(ae.getSource() instanceof JCheckBox)
      {
         for(JCheckBox cb : drawCBList)
         {
            if(cb != ae.getSource())
               cb.setSelected(false);
         }
      }
   }
}