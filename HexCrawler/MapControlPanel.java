package HexCrawler;

import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;

public class MapControlPanel extends JPanel implements HexCrawlerConstants, ComponentListener, ActionListener
{
   private JButton[] terrainButtonArray;
   private MainPanel parentPanel;
   private JPanel radioPanel;
   private JRadioButton terrainRB;
   private JRadioButton bigPoIRB;
   private JRadioButton smallPoIRB;
   private JPanel terrainButtonPanel;
   private int rows;
   
   public MapControlPanel(MainPanel panel)
   {
      super();
      parentPanel = panel;
      setLayout(null);
      rows = (Terrain.values().length / 2) + 1;
      if(Terrain.values().length % 2 == 1)
         rows++;
      
      radioPanel = new JPanel();
      radioPanel.setLayout(new GridLayout(1, 3));
      terrainRB = new JRadioButton("Terrain");
      bigPoIRB = new JRadioButton("Big Icon");
      smallPoIRB = new JRadioButton("Small Icon");
      ButtonGroup bg = new ButtonGroup();
      bg.add(terrainRB);
      bg.add(bigPoIRB);
      bg.add(smallPoIRB);
      terrainRB.setSelected(true);
      radioPanel.add(terrainRB);
      radioPanel.add(bigPoIRB);
      radioPanel.add(smallPoIRB);
      radioPanel.setVisible(true);
      add(radioPanel);
      
      terrainButtonPanel = new JPanel();
      terrainButtonPanel.setLayout(new GridLayout(rows - 1, 2));
      terrainButtonArray = new JButton[Terrain.values().length];
      for(int i = 0; i < terrainButtonArray.length; i++)
      {
         terrainButtonArray[i] = getButton(Terrain.values()[i]);
         terrainButtonPanel.add(terrainButtonArray[i]);
      }
      terrainButtonPanel.setVisible(true);
      add(terrainButtonPanel);
      
      addComponentListener(this);
      setVisible(true);
   }
   
   private JButton getButton(Terrain t)
   {
      JButton button = new JButton();
      button.setBackground(t.background);
      button.addActionListener(this);
      return button;
   }
   
   public void setIcons()
   {
      for(int i = 0; i < terrainButtonArray.length; i++)
      {
         Terrain t = Terrain.values()[i];
         BufferedImage img = ImagePalette.getImage(t.imageName);
         if(img != null)
         {
            img = ImagePalette.scaleToPixelSize(img, getHeight() / rows);
            if(img != null)
               terrainButtonArray[i].setIcon(new ImageIcon(img));
         }
      }
      repaint();
   }   
   
   public void componentHidden(ComponentEvent e){}
   public void componentMoved(ComponentEvent e){}
   public void componentShown(ComponentEvent e){}
   public void componentResized(ComponentEvent e)
   {
      arrangeElements();
      setIcons();
   }
   
   public void actionPerformed(ActionEvent ae)
   {
      for(int i = 0; i < terrainButtonArray.length; i++)
         if(ae.getSource() == terrainButtonArray[i])
            parentPanel.setTerrainIndex(i);
   }
   
   public void arrangeElements()
   {
      int radioPanelHeight = getHeight() / rows;
      radioPanel.setSize(getWidth(), radioPanelHeight);
      radioPanel.setLocation(0, 0);
      terrainButtonPanel.setSize(getWidth(), getHeight() - radioPanelHeight);
      terrainButtonPanel.setLocation(0, radioPanelHeight);
   }
}