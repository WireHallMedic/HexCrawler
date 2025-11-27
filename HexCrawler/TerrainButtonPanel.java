package HexCrawler;

import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;

public class TerrainButtonPanel extends JPanel implements HexCrawlerConstants, ComponentListener, ActionListener
{
   private JButton[] buttonArray;
   private MainPanel parentPanel;
   
   public TerrainButtonPanel(MainPanel panel)
   {
      super();
      parentPanel = panel;
      int rows = Terrain.values().length / 2;
      if(Terrain.values().length % 2 == 1)
         rows++;
      setLayout(new GridLayout(rows, 2));
      buttonArray = new JButton[Terrain.values().length];
      for(int i = 0; i < buttonArray.length; i++)
      {
         buttonArray[i] = getButton(Terrain.values()[i]);
         add(buttonArray[i]);
      }
      addComponentListener(this);
      setVisible(true);
      setIcons();
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
      for(int i = 0; i < buttonArray.length; i++)
      {
         Terrain t = Terrain.values()[i];
         BufferedImage img = ImagePalette.getImage(t.imageName);
         if(img != null)
         {
            img = ImagePalette.scaleToPixelSize(img, buttonArray[i].getHeight());
            if(img != null)
               buttonArray[i].setIcon(new ImageIcon(img));
         }
      }
      repaint();
   }   
   
   public void componentHidden(ComponentEvent e){}
   public void componentMoved(ComponentEvent e){}
   public void componentShown(ComponentEvent e){}
   public void componentResized(ComponentEvent e)
   {
      setIcons();
      repaint();
   }
   
   public void setVisible(boolean v)
   {
      super.setVisible(v);
      setIcons();
      repaint();
   }
   
   public void actionPerformed(ActionEvent ae)
   {
      for(int i = 0; i < buttonArray.length; i++)
         if(ae.getSource() == buttonArray[i])
            parentPanel.setTerrainIndex(i);
   }
}