package HexCrawler;

import java.util.*;
import java.awt.*;
import java.awt.image.*;

public class LinearPath implements HexCrawlerConstants
{
   private Vector<double[]> pointList;
   private Color color;
   
   public int size(){return pointList.size();}
   public double[] getPoint(int i){return pointList.elementAt(i);}
   public Color getColor(){return color;}
   
   public void setColor(Color c){color = c;}
   
   public LinearPath()
   {
      pointList = new Vector<double[]>();
      color = ROAD_COLOR;
   }
   
   public void add(double x, double y)
   {
      double[] newPoint = {x, y};
      pointList.add(newPoint);
   }
   
   public int getXScaled(int i, double scale)
   {
      return (int)(getPoint(i)[0] * scale);
   }
   
   public int getYScaled(int i, double scale)
   {
      return (int)(getPoint(i)[1] * scale);
   }
   
   // setting stroke size must be done on immutable objects
   public void paint(Graphics2D originalG2D, MapPanel mapPanel, double scale)
   {
      BufferedImage newBuffered = new BufferedImage(mapPanel.getWidth(), mapPanel.getHeight(), BufferedImage.TYPE_INT_ARGB);
      Graphics2D g2d = newBuffered.createGraphics();
      int strokeSize = Math.max(1, (int)(.05 * scale));
      g2d.setStroke(new BasicStroke(strokeSize));
      g2d.setColor(color);
      for(int i = 0; i < size() - 1; i++)
      {
         g2d.drawLine(getXScaled(i, scale), getYScaled(i, scale),
                      getXScaled(i + 1, scale), getYScaled(i + 1, scale));
      }
      g2d.dispose();
      originalG2D.drawImage(newBuffered, 0, 0, null);
   }
   
   public static LinearPath mock()
   {
      LinearPath lp = new LinearPath();
      lp.add(.4, .6);
      lp.add(1.25, 3.5);
      lp.add(2.5, 2.0);
      return lp;
   }
}