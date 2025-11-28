package HexCrawler;

import java.util.*;
import java.awt.*;

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
}