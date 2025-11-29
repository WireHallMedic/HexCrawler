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
      int strokeSize = Math.max(1, (int)(ROAD_THICKNESS * scale));
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
   
   // test points are unscaled
   public boolean contains(double testX, double testY)
   {
      for(int i = 0; i < size() - 1; i++)
      {
         if(segmentContains(i, testX, testY))
            return true;
      }
      return false;
   }
   
   private boolean segmentContains(int startingPoint, double testX, double testY)
   {
      double x1 = pointList.elementAt(startingPoint)[0];
      double y1 = pointList.elementAt(startingPoint)[1];
      double x2 = pointList.elementAt(startingPoint + 1)[0];
      double y2 = pointList.elementAt(startingPoint + 1)[1];
      if(x1 == x2)
         return verticallyContains(x1, y1, x2, y2, testX, testY);
      else if(x1 == x2)
         return horizontallyContains(x1, y1, x2, y2, testX, testY);
      else
         return diagonallyContains(x1, y1, x2, y2, testX, testY);
   }
   
   // test when slope is infinite
   private boolean verticallyContains(double x1, double y1, double x2, double y2, double xt, double yt)
   {
      return yt <= Math.max(y1, y2) && yt >= Math.min(y1, y2);
   }
   
   // test when slope is zero
   private boolean horizontallyContains(double x1, double y1, double x2, double y2, double xt, double yt)
   {
      return xt <= Math.max(x1, x2) && xt >= Math.min(x1, x2);
   }
   
   private boolean diagonallyContains(double x1, double y1, double x2, double y2, double xt, double yt)
   {
      // check if in bounding box first
      if(xt < Math.min(x1, x2) || 
         xt > Math.max(x1, x2) ||
         yt < Math.min(y1, y2) ||
         yt > Math.max(y1, y2))
         return false;
      double slope = (y2 - y1) / (x2 - x1);
      double intercept = y1 - (slope * x1);
      double yAtTestX = (slope * xt) + intercept;
      return Math.abs(yAtTestX - yt) <= ROAD_THICKNESS;
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