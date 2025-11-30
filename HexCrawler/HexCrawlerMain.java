package HexCrawler;

import javax.swing.*;

public class HexCrawlerMain extends JFrame
{
   private MainPanel mainPanel;
   
   public HexCrawlerMain()
   {
      super();
      ImagePalette.load("./res");
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      setTitle("Hex Crawler");
      
      mainPanel = new MainPanel();
      add(mainPanel);
      
      setSize(1100, 800);
      
      mainPanel.arrangeElements();
      setVisible(true);
      repaint();
      mainPanel.grabFocus();
   }
   
   public static void main(String[] args)
   {
      HexCrawlerMain main = new HexCrawlerMain();
   }
}