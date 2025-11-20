package HexCrawler;

import java.awt.image.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;

public class MapPanel extends JPanel implements HexCrawlerConstants
{
   public MapPanel()
   {
      super();
   }
   
   private void paintHex(Graphics2D g2d, double scale, int xPos, int yPos)
   {
      double[] xPoints = indentX(xPos, yPos, HEX_POINTS_X);
      double[] yPoints = indentY(xPos, yPos, HEX_POINTS_Y);
      xPoints = scale(scale, xPoints);
      yPoints = scale(scale, yPoints);
      g2d.drawPolygon(doubleToInt(xPoints), doubleToInt(yPoints), xPoints.length);
   }
   
   private double[] indentX(int xPos, int yPos, double[] arr)
   {
      double[] pointArr = new double[arr.length];
      double xInset = getXInset(xPos, yPos);
      for(int i = 0; i < arr.length; i++)
         pointArr[i] = arr[i] + xInset;
      return pointArr;
   }
   
   private double[] indentY(int xPos, int yPos, double[] arr)
   {
      double[] pointArr = new double[arr.length];
      double yInset = getYInset(xPos, yPos);
      for(int i = 0; i < arr.length; i++)
         pointArr[i] = arr[i] + yInset;
      return pointArr;
   }
   
   private double[] scale(double scale, double[] arr)
   {
      double[] pointArr = new double[arr.length];
      for(int i = 0; i < arr.length; i++)
         pointArr[i] = arr[i] * scale;
      return pointArr;
   }
   
   private int[] doubleToInt(double[] doubleArr)
   {
      int[] intArr = new int[doubleArr.length];
      for(int i = 0; i < doubleArr.length; i++)
         intArr[i] = (int)(Math.round(doubleArr[i]));
      return intArr;
   }
   
   private double getXInset(int xPos, int yPos)
   {
      double inset = 0.0;
      if(yPos % 2 == 0)
         inset += EVEN_X_INSET;
      else
         inset += ODD_X_INSET;
      inset += xPos * X_STEP;
      return inset;
   }
   
   private double getYInset(int xPos, int yPos)
   {
      return yPos * Y_STEP;
   }
   
   @Override
   public void paint(Graphics g)
   {
      super.paint(g);
      Graphics2D g2d = (Graphics2D)g;
      for(int x = 0; x < 7; x++)
      for(int y = 0; y < 7; y++)
         paintHex(g2d, 100, x, y);
   }
}