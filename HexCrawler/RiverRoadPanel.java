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
   
   public RiverRoadPanel(MainPanel parent)
   {
      super();
      parentPanel = parent;
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
   }

   
   public void actionPerformed(ActionEvent ae)
   {
      if(ae.getSource() instanceof JCheckBox)
      {

      }
   }
}