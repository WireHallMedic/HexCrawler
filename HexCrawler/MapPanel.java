package HexCrawler;

import java.awt.image.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MapPanel extends JPanel implements HexCrawlerConstants, MouseListener
{
   private double scale = 100.0;
   private MapOfHexes mapOfHexes;
   
   public MapPanel(MapOfHexes map)
   {
      super();
      setBackground(Color.BLACK);
      mapOfHexes = map;
      
      addMouseListener(this);
   }
   
   public void mouseClicked(MouseEvent me)
   {
      double mouseLocX = me.getX() / scale;
      double mouseLocY = me.getY() / scale;
      int lastX = -1;
      int lastY = -1;
      double lastDist = 1000000.0;
      for(int x = 0; x < mapOfHexes.getWidth(); x++)
      for(int y = 0; y < mapOfHexes.getHeight(); y++)
      {
         double newX = mouseLocX - (getXInset(x, y) + (HEX_WIDTH / 2));
         double newY = mouseLocY - (getYInset(x, y) + (HEX_HEIGHT / 2));
         double newDist = (newX * newX) + (newY * newY);
         if(newDist < lastDist && newDist < (R_LONG * R_LONG))
         {
            lastX = x;
            lastY = y;
            lastDist = newDist;
            System.out.println(x + ", " + y);
         }
      }
   }
   public void mousePressed(MouseEvent me){}
   public void mouseReleased(MouseEvent me){}
   public void mouseEntered(MouseEvent me){}
   public void mouseExited(MouseEvent me){}
   
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
   
   public void setScale()
   {
      double xMult = getWidth() / (((mapOfHexes.getWidth() + .5) * HEX_WIDTH));
      double yMult = getHeight() / ((mapOfHexes.getHeight() * (R_LONG * 1.5) + (R_LONG / 2)));
      scale = Math.min(xMult, yMult);
   }
   
   @Override
   public void paint(Graphics g)
   {
      super.paint(g);
      Graphics2D g2d = (Graphics2D)g;
      int w = mapOfHexes.getWidth();
      int h = mapOfHexes.getHeight();
      for(int x = 0; x < w; x++)
      for(int y = 0; y < h; y++)
         paintHex(g2d, mapOfHexes.getTile(x, y), x, y);
   }
   
   private void paintHex(Graphics2D g2d, MapHex tile, int xPos, int yPos)
   {
      double[] xPoints = indentX(xPos, yPos, HEX_POINTS_X);
      double[] yPoints = indentY(xPos, yPos, HEX_POINTS_Y);
      xPoints = scale(scale, xPoints);
      yPoints = scale(scale, yPoints);
      // draw background
      g2d.setColor(tile.getBackground());
      g2d.fillPolygon(doubleToInt(xPoints), doubleToInt(yPoints), xPoints.length);
      // draw border
      g2d.setColor(Color.BLACK);
      g2d.drawPolygon(doubleToInt(xPoints), doubleToInt(yPoints), xPoints.length);
      // draw image
      if(tile.getImage() != null)
      {
         BufferedImage img = tile.getScaledImage(scale);
         double xOrigin = getXInset(xPos, yPos) + R_SHORT - (SQUARE_SIDE / 2);
         double yOrigin = getYInset(xPos, yPos) + R_LONG - (SQUARE_SIDE / 2);
         g2d.drawImage(img, (int)(xOrigin * scale), (int)(yOrigin * scale), null);
      }
   }
}