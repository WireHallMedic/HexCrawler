package HexCrawler;

import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;

public class MapControlPanel extends JPanel implements HexCrawlerConstants, ComponentListener, ActionListener
{
   private JButton[] terrainButtonArray;
   private JButton[] bigPoIButtonArray;
   private JButton[] smallPoIButtonArray;
   private MainPanel parentPanel;
   private JPanel radioPanel;
   private JRadioButton terrainRB;
   private JRadioButton bigPoIRB;
   private JRadioButton smallPoIRB;
   private JPanel terrainButtonPanel;
   private JPanel bigPoIPanel;
   private JPanel smallPoIPanel;
   private int rows;
   
   public boolean terrainMode(){return terrainRB.isSelected();}
   public boolean bigPoIMode(){return bigPoIRB.isSelected();}
   public boolean smallPoIMode(){return smallPoIRB.isSelected();}
   
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
      terrainRB.addActionListener(this);
      bigPoIRB.addActionListener(this);
      smallPoIRB.addActionListener(this);
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
      
      bigPoIPanel = new JPanel();
      bigPoIPanel.setLayout(new GridLayout(rows - 1, 2));
      bigPoIButtonArray = new JButton[PointOfInterest.values().length];
      for(int i = 0; i < bigPoIButtonArray.length; i++)
      {
         bigPoIButtonArray[i] = getButton(PointOfInterest.values()[i]);
         bigPoIPanel.add(bigPoIButtonArray[i]);
      }
      bigPoIPanel.setVisible(false);
      add(bigPoIPanel);
      
      smallPoIPanel = new JPanel();
      smallPoIPanel.setLayout(new GridLayout(rows - 1, 2));
      smallPoIButtonArray = new JButton[PointOfInterest.values().length];
      for(int i = 0; i < bigPoIButtonArray.length; i++)
      {
         smallPoIButtonArray[i] = getButton(PointOfInterest.values()[i]);
         smallPoIPanel.add(smallPoIButtonArray[i]);
      }
      smallPoIPanel.setVisible(false);
      add(smallPoIPanel);
      
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
   
   private JButton getButton(PointOfInterest poi)
   {
      JButton button = new JButton();
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
      for(int i = 0; i < bigPoIButtonArray.length; i++)
      {
         PointOfInterest poi = PointOfInterest.values()[i];
         BufferedImage img = ImagePalette.getImage(poi.imageName);
         if(img != null)
         {
            img = ImagePalette.scaleToPixelSize(img, getHeight() / rows);
            if(img != null)
            {
               bigPoIButtonArray[i].setIcon(new ImageIcon(img));
               smallPoIButtonArray[i].setIcon(new ImageIcon(img));
            }
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
      if(ae.getSource() == terrainRB)
      {
         terrainButtonPanel.setVisible(true);
         bigPoIPanel.setVisible(false);
         smallPoIPanel.setVisible(false);
         return;
      }
      if(ae.getSource() == smallPoIRB)
      {
         terrainButtonPanel.setVisible(false);
         bigPoIPanel.setVisible(false);
         smallPoIPanel.setVisible(true);
         return;
      }
      if(ae.getSource() == bigPoIRB)
      {
         terrainButtonPanel.setVisible(false);
         bigPoIPanel.setVisible(true);
         smallPoIPanel.setVisible(false);
         return;
      }
      for(int i = 0; i < terrainButtonArray.length; i++)
      {
         if(ae.getSource() == terrainButtonArray[i])
         {
            parentPanel.setTerrainIndex(i);
            return;
         }
      }
      for(int i = 0; i < bigPoIButtonArray.length; i++)
      {
         if(ae.getSource() == bigPoIButtonArray[i])
         {
            parentPanel.setBigPoIIndex(i);
            return;
         }
      }
      for(int i = 0; i < smallPoIButtonArray.length; i++)
      {
         if(ae.getSource() == smallPoIButtonArray[i])
         {
            parentPanel.setSmallPoIIndex(i);
            return;
         }
      }
   }
   
   public void arrangeElements()
   {
      int radioPanelHeight = getHeight() / rows;
      radioPanel.setSize(getWidth(), radioPanelHeight);
      radioPanel.setLocation(0, 0);
      terrainButtonPanel.setSize(getWidth(), getHeight() - radioPanelHeight);
      terrainButtonPanel.setLocation(0, radioPanelHeight);
      bigPoIPanel.setSize(getWidth(), getHeight() - radioPanelHeight);
      bigPoIPanel.setLocation(0, radioPanelHeight);
      smallPoIPanel.setSize(getWidth(), getHeight() - radioPanelHeight);
      smallPoIPanel.setLocation(0, radioPanelHeight);
   }
}