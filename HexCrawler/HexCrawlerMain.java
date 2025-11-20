package HexCrawler;

import javax.swing.*;

public class HexCrawlerMain extends JFrame
{
   private MapPanel mapPanel;
   
   public HexCrawlerMain()
   {
      super();
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      
      mapPanel = new MapPanel();
      add(mapPanel);
      
      setSize(800, 800);
      setVisible(true);
      
      repaint();
   }
   
   public static void main(String[] args)
   {
      HexCrawlerMain main = new HexCrawlerMain();
   }
}