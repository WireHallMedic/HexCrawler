package HexCrawler;

import javax.swing.*;
import java.awt.event.*;

public class MainPanel extends JPanel implements ComponentListener, HexCrawlerConstants
{
   private MapOfHexes hexMap;
   private MapPanel mapPanel;
   private JPanel programControlPanel;
   private MapControlPanel mapControlPanel;
   private double MAP_PANEL_WIDTH = .75;
   private double MAP_PANEL_HEIGHT = .9;
   private int terrainIndex; // what happens when you left click
   private int bigPoIIndex;
   private int smallPoIIndex;
   public static boolean explorationMode = false;
   
   public void setTerrainIndex(int ci){terrainIndex = ci;}
   public void setBigPoIIndex(int pi){bigPoIIndex = pi;}
   public void setSmallPoIIndex(int pi){smallPoIIndex = pi;}
   
   public MainPanel()
   {
      super();
      hexMap = MapOfHexes.mock();
      terrainIndex = 0;
      bigPoIIndex = 0;
      smallPoIIndex = 0;
      setLayout(null);
      mapPanel = new MapPanel(hexMap, this);
      programControlPanel = new JPanel();
      mapControlPanel = new MapControlPanel(this);
      
      add(mapPanel);
      add(programControlPanel);
      add(mapControlPanel);
      
      addComponentListener(this);
      setVisible(true);
      arrangeElements();
   }
   
   public void tileClicked(int x, int y, boolean leftClick)
   {
      int iconIndex = 0;
      if(mapControlPanel.terrainMode())
      {
         if(leftClick)
            iconIndex = terrainIndex;
         hexMap.getTile(x, y).set(Terrain.values()[iconIndex]);
      }
      else if(mapControlPanel.bigPoIMode())
      {
         if(leftClick)
            iconIndex = bigPoIIndex;
         hexMap.getTile(x, y).setBigImage(PointOfInterest.values()[iconIndex]);
      }
      else if(mapControlPanel.smallPoIMode())
      {
         if(leftClick)
            iconIndex = smallPoIIndex;
         hexMap.getTile(x, y).setSmallImage(PointOfInterest.values()[iconIndex]);
      }
      mapPanel.repaint();
   }
   
   public void arrangeElements()
   {
      int width = getWidth();
      int height = getHeight();
      int mapPanelWidth = (int)(width * MAP_PANEL_WIDTH);
      int mapPanelHeight = (int)(height * MAP_PANEL_HEIGHT);
      mapPanel.setSize(mapPanelWidth, mapPanelHeight);
      programControlPanel.setSize(width, height - mapPanelHeight);
      mapControlPanel.setSize(width - mapPanelWidth, height - programControlPanel.getHeight());
      mapPanel.setLocation(0, 0);
      programControlPanel.setLocation(0, mapPanelHeight);
      mapControlPanel.setLocation(mapPanelWidth, 0);
      
      mapControlPanel.arrangeElements();
      mapControlPanel.setIcons();
   }
   
   public void componentHidden(ComponentEvent e){}
   public void componentMoved(ComponentEvent e){}
   public void componentShown(ComponentEvent e){}
   public void componentResized(ComponentEvent e)
   {
      arrangeElements();
      mapPanel.setScale();
      repaint();
   }
}