package HexCrawler;

import javax.swing.*;
import java.awt.event.*;

public class MainPanel extends JPanel implements ComponentListener
{
   private MapPanel mapPanel;
   private JPanel programControlPanel;
   private JPanel mapControlPanel;
   private double MAP_PANEL_WIDTH = .75;
   private double MAP_PANEL_HEIGHT = .9;
   
   public MainPanel()
   {
      super();
      setLayout(null);
      mapPanel = new MapPanel(MapOfHexes.mock());
      programControlPanel = new JPanel();
      mapControlPanel = new JPanel();
      
      add(mapPanel);
      add(programControlPanel);
      add(mapControlPanel);
      
      arrangeElements();
      addComponentListener(this);
      setVisible(true);
   }
   
   private void arrangeElements()
   {
      int width = getWidth();
      int height = getHeight();
      int mapPanelWidth = (int)(width * MAP_PANEL_WIDTH);
      int mapPanelHeight = (int)(height * MAP_PANEL_HEIGHT);
      mapPanel.setSize(mapPanelWidth, mapPanelHeight);
      programControlPanel.setSize(width, height - mapPanelHeight);
      mapControlPanel.setSize(width - mapPanelHeight, height);
      mapPanel.setLocation(0, 0);
      programControlPanel.setLocation(0, mapPanelHeight);
      mapControlPanel.setLocation(mapPanelWidth, 0);
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