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
   private JRadioButton riverRoadRB;
   private JRadioButton explorationRB;
   private JPanel terrainPanel;
   private JPanel bigPoIPanel;
   private JPanel smallPoIPanel;
   private JPanel riverRoadPanel;
   private JPanel explorationPanel;
   private JPanel[] panelArray;
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
      radioPanel.setLayout(new GridLayout(2, 3));
      terrainRB = new JRadioButton("Terrain");
      bigPoIRB = new JRadioButton("Big Icon");
      smallPoIRB = new JRadioButton("Small Icon");
      riverRoadRB = new JRadioButton("River/Road");
      explorationRB = new JRadioButton("Exploration");
      ButtonGroup bg = new ButtonGroup();
      bg.add(terrainRB);
      bg.add(riverRoadRB);
      bg.add(bigPoIRB);
      bg.add(explorationRB);
      bg.add(smallPoIRB);
      terrainRB.addActionListener(this);
      bigPoIRB.addActionListener(this);
      smallPoIRB.addActionListener(this);
      riverRoadRB.addActionListener(this);
      explorationRB.addActionListener(this);
      terrainRB.setSelected(true);
      radioPanel.add(terrainRB);
      radioPanel.add(bigPoIRB);
      radioPanel.add(smallPoIRB);
      radioPanel.add(riverRoadRB);
      radioPanel.add(explorationRB);
      radioPanel.setVisible(true);
      add(radioPanel);
      
      terrainPanel = new JPanel();
      terrainPanel.setLayout(new GridLayout(rows - 1, 2));
      terrainButtonArray = new JButton[Terrain.values().length];
      for(int i = 0; i < terrainButtonArray.length; i++)
      {
         terrainButtonArray[i] = getButton(Terrain.values()[i]);
         terrainPanel.add(terrainButtonArray[i]);
      }
      terrainPanel.setVisible(true);
      add(terrainPanel);
      
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
      
      riverRoadPanel = new JPanel();
      riverRoadPanel.setLayout(new GridLayout(rows - 1, 1));
      riverRoadPanel.add(new JLabel("Not yet implemented"));
      riverRoadPanel.setVisible(false);
      add(riverRoadPanel);
      
      explorationPanel = new JPanel();
      explorationPanel.setLayout(new GridLayout(rows - 1, 1));
      explorationPanel.add(new JLabel("Left-click to see"));
      explorationPanel.add(new JLabel("Right-click to explore"));
      explorationPanel.add(new JLabel("Shift-click to obscure"));
      explorationPanel.setVisible(false);
      add(explorationPanel);
      
      panelArray = new JPanel[5];
      panelArray[0] = terrainPanel;
      panelArray[1] = bigPoIPanel;
      panelArray[2] = smallPoIPanel;
      panelArray[3] = riverRoadPanel;
      panelArray[4] = explorationPanel;
      
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
      if(ae.getSource() instanceof JRadioButton)
      {
         parentPanel.explorationMode = false;
         for(JPanel p : panelArray)
            p.setVisible(false);
         if(ae.getSource() == terrainRB)
         {
            terrainPanel.setVisible(true);
         }
         if(ae.getSource() == smallPoIRB)
         {
            smallPoIPanel.setVisible(true);
         }
         if(ae.getSource() == bigPoIRB)
         {
            bigPoIPanel.setVisible(true);
         }
         if(ae.getSource() == riverRoadRB)
         {
            riverRoadPanel.setVisible(true);
         }
         if(ae.getSource() == explorationRB)
         {
            explorationPanel.setVisible(true);
            parentPanel.explorationMode = true;
         }
         parentPanel.repaint();
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
      for(JPanel p : panelArray)
      {
         p.setSize(getWidth(), getHeight() - radioPanelHeight);
         p.setLocation(0, radioPanelHeight);
      }
   }
}