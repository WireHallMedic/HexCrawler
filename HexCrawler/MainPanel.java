package HexCrawler;

import javax.swing.*;
import java.awt.event.*;

public class MainPanel extends JPanel implements ComponentListener, HexCrawlerConstants, KeyListener
{
   private MapOfHexes hexMap;
   private MapPanel mapPanel;
   private ProgramControlPanel programControlPanel;
   private MapControlPanel mapControlPanel;
   private double MAP_PANEL_WIDTH = .75;
   private double MAP_PANEL_HEIGHT = .9;
   private int terrainIndex; // what happens when you left click
   private int bigPoIIndex;
   private int smallPoIIndex;
   public static boolean explorationMode = false;
   private boolean shiftDown = false;
   
   public void setTerrainIndex(int ci){terrainIndex = ci;}
   public void setBigPoIIndex(int pi){bigPoIIndex = pi;}
   public void setSmallPoIIndex(int pi){smallPoIIndex = pi;}
   
   public MapOfHexes getMap(){return hexMap;}
   
   public MainPanel()
   {
      super();
      hexMap = MapOfHexes.mock();
      terrainIndex = 0;
      bigPoIIndex = 0;
      smallPoIIndex = 0;
      setLayout(null);
      mapPanel = new MapPanel(hexMap, this);
      programControlPanel = new ProgramControlPanel(this);
      mapControlPanel = new MapControlPanel(this);
      
      add(mapPanel);
      add(programControlPanel);
      add(mapControlPanel);
      
      addComponentListener(this);
      addKeyListener(this);
      setVisible(true);
      arrangeElements();
   }
   
   public void tileClicked(int x, int y, boolean leftClick, double mouseLocX, double mouseLocY)
   {
      int iconIndex = 0;
      if(explorationMode)
      {
         if(shiftDown)
         {
            hexMap.getTile(x, y).setSeen(false);
            hexMap.getTile(x, y).setExplored(false);
         }
         else
         {
            hexMap.getTile(x, y).setSeen(true);
            if(!leftClick)
               hexMap.getTile(x, y).setExplored(true);
         }
         mapPanel.repaint();
         return;
      }
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
      else if(mapControlPanel.pathMode())
      {
         // draw mode
         if(mapControlPanel.pathDrawMode())
         {
            LinearPath p = mapControlPanel.getCurPath();
            if(p != null)
            {
               p.add(mouseLocX, mouseLocY);
            }
         }
         else // select mode
         {
            LinearPath p = hexMap.getPath(mouseLocX, mouseLocY);
            mapControlPanel.setCurPath(p);
         }
         mapControlPanel.repaint();
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
      repaint();
   }
   
   public void keyTyped(KeyEvent ke){}
   public void keyPressed(KeyEvent ke)
   {
      if(ke.getKeyCode() == KeyEvent.VK_SHIFT)
         shiftDown = true;
   }
   public void keyReleased(KeyEvent ke)
   {
      if(ke.getKeyCode() == KeyEvent.VK_SHIFT)
         shiftDown = false;
   }
   
   public void save()
   {
      FileManager.save(this);
   }
   
   public void load()
   {
      FileManager.load(this);
      mapPanel.repaint();
   }
   
   public void newMap(int[] newSize)
   {
      hexMap = new MapOfHexes(newSize[0], newSize[1]);
      mapPanel.repaint();
   }
   
   public void repaintMapPanel()
   {
      mapPanel.repaint();
   }
}