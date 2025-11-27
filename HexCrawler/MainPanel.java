package HexCrawler;

import javax.swing.*;

public class MainPanel extends JPanel
{
   private MapPanel mapPanel;
   private JPanel programControlPanel;
   private JPanel mapControlPanel;
   
   public MainPanel()
   {
      super();
      mapPanel = new MapPanel();
      programControlPanel = new JPanel();
      mapControlPanel = new JPanel();
      
      add(mapPanel);
      add(programControlPanel);
      add(mapControlPanel);
   }
}