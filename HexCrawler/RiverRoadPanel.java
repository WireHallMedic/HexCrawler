package HexCrawler;

import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;

public class RiverRoadPanel extends JPanel implements HexCrawlerConstants, ActionListener
{
   private JRadioButton selectRB;
   private JRadioButton drawRB;
   private MainPanel parentPanel;
   private JPanel pathPanel;
   private JButton clearB;
   private JButton doneB;
   private JRadioButton roadRB;
   private JRadioButton riverRB;
   private LinearPath curPath;
   
   public boolean drawMode(){return drawRB.isSelected();}
   public LinearPath getCurPath(){return curPath;}
   
   public RiverRoadPanel(MainPanel parent)
   {
      super();
      parentPanel = parent;
      curPath = null;
      setLayout(new GridLayout(6, 1));
      JPanel anonPanel = new JPanel();
      anonPanel.setLayout(new GridLayout(1, 2));
      selectRB = new JRadioButton("Select Road/River");
      selectRB.setSelected(true);
      drawRB = new JRadioButton("Draw Road/River");
      anonPanel.add(selectRB);
      anonPanel.add(drawRB);
      add(anonPanel);
      ButtonGroup anonGroup = new ButtonGroup();
      anonGroup.add(selectRB);
      anonGroup.add(drawRB);
      
      pathPanel = new JPanel();
      pathPanel.setLayout(new GridLayout(1, 4));
      roadRB = new JRadioButton("Road");
      riverRB = new JRadioButton("River");
      clearB = new JButton("Clear");
      doneB = new JButton("Done");
      roadRB.setSelected(true);
      anonGroup = new ButtonGroup();
      anonGroup.add(roadRB);
      anonGroup.add(riverRB);
      pathPanel.add(roadRB);
      pathPanel.add(riverRB);
      pathPanel.add(clearB);
      pathPanel.add(doneB);
      add(pathPanel);
      
      clearB.addActionListener(this);
      doneB.addActionListener(this);
      riverRB.addActionListener(this);
      roadRB.addActionListener(this);
      drawRB.addActionListener(this);
   }

   
   public void actionPerformed(ActionEvent ae)
   {
      if(ae.getSource() == clearB)
      {
         if(curPath != null)
         {
            parentPanel.getMap().removePath(curPath);
            curPath = null;
         }
      }
      else if(ae.getSource() == doneB)
      {
         curPath = null;
         parentPanel.getMap().cleanPaths();
         selectRB.setSelected(true);
      }
      else if(ae.getSource() == roadRB)
      {
         if(curPath != null)
         {
            curPath.setColor(ROAD_COLOR);
         }
      }
      else if(ae.getSource() == riverRB)
      {
         if(curPath != null)
         {
            curPath.setColor(WATER_COLOR);
         }
      }
      // start drawing new path
      else if(ae.getSource() == drawRB)
      {
         curPath = new LinearPath();
         parentPanel.getMap().addPath(curPath);
         if(roadRB.isSelected())
            curPath.setColor(ROAD_COLOR);
         else
            curPath.setColor(WATER_COLOR);
      }
      else if(ae.getSource() == selectRB)
      {
         doneB.doClick();
      }
      parentPanel.repaintMapPanel();
      repaint();
   }
   
   
   public void setCurPath(LinearPath p)
   {
      curPath = p;
      if(curPath == null || curPath.getColor().getRGB() == ROAD_COLOR.getRGB())
         roadRB.setSelected(true);
      else
         riverRB.setSelected(true);
   }
   
   @Override
   public void paint(Graphics g)
   {
      super.paint(g);
      if(curPath == null)
         pathPanel.setVisible(false);
      else
         pathPanel.setVisible(true);
   }
}