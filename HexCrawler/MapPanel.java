package HexCrawler;

import java.awt.image.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MapPanel extends JPanel implements HexCrawlerConstants, MouseListener, MouseMotionListener
{
   private double scale = 100.0;
   private MainPanel parentPanel;
   private static final MapHex OOB_HEX = new  MapHex(Color.BLACK, null);
   private MapToken mapToken;
   private boolean draggingToken;
   
   public MapPanel(MapOfHexes map, MainPanel parent)
   {
      super();
      setBackground(Color.BLACK);
      parentPanel = parent;
      mapToken = new MapToken();
      draggingToken = false;
      
      addMouseListener(this);
      addMouseMotionListener(this);
   }
   
   public void mouseReleased(MouseEvent me)
   {
      if(!draggingToken)
      {
         boolean leftClick = me.getButton() == MouseEvent.BUTTON1;
         double mouseLocX = me.getX() / scale;
         double mouseLocY = me.getY() / scale;
         int lastX = -1;
         int lastY = -1;
         double lastDist = 1000000.0;
         for(int x = 0; x < parentPanel.getMap().getWidth(); x++)
         for(int y = 0; y < parentPanel.getMap().getHeight(); y++)
         {
            double newX = mouseLocX - (getXInset(x, y) + (HEX_WIDTH / 2));
            double newY = mouseLocY - (getYInset(x, y) + (HEX_HEIGHT / 2));
            double newDist = (newX * newX) + (newY * newY);
            if(newDist < lastDist && newDist < (R_LONG * R_LONG))
            {
               lastX = x;
               lastY = y;
               lastDist = newDist;
            }
         }
         if(lastDist < 1000000.0)
            parentPanel.tileClicked(lastX, lastY, leftClick, mouseLocX, mouseLocY);
         else
            parentPanel.tileClicked(-1, -1, leftClick, mouseLocX, mouseLocY);
      }
      draggingToken = false;
   }
   
   
   public void mousePressed(MouseEvent me)
   {
      double mouseLocX = me.getX() / scale;
      double mouseLocY = me.getY() / scale;
      double radius = mapToken.getRadius();
      if(mapToken.isInRadius(mouseLocX, mouseLocY))
      {
         draggingToken = true;
      }
   }
   
   public void mouseDragged(MouseEvent me)
   {
      if(draggingToken)
      {
         double mouseLocX = me.getX() / scale;
         double mouseLocY = me.getY() / scale;
         double radius = mapToken.getRadius();
         mapToken.setLoc(mouseLocX, mouseLocY);
         repaint();
      }
   }
   
   
   public void mouseClicked(MouseEvent me){}
   public void mouseEntered(MouseEvent me){}
   public void mouseExited(MouseEvent me){}
   public void mouseMoved(MouseEvent me){}
   
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
      double xMult = getWidth() / (((parentPanel.getMap().getWidth() + .5) * HEX_WIDTH));
      double yMult = getHeight() / ((parentPanel.getMap().getHeight() * (R_LONG * 1.5) + (R_LONG / 2)));
      scale = Math.min(xMult, yMult);
   }
   
   @Override
   public void paint(Graphics g)
   {
      super.paint(g);
      setScale();
      Graphics2D g2d = (Graphics2D)g;
      int w = parentPanel.getMap().getWidth();
      int h = parentPanel.getMap().getHeight();
      for(int x = 0; x < w; x++)
      for(int y = 0; y < h; y++)
         paintHex(g2d, parentPanel.getMap().getTile(x, y), x, y);
      Vector<LinearPath> pathList = parentPanel.getMap().getPathList();
      if(pathList != null)
         for(LinearPath path : pathList)
         {
            path.paint(g2d, this, scale);
         }
      // paint over roads and rivers
      if(parentPanel.explorationMode)
         for(int x = 0; x < w; x++)
         for(int y = 0; y < h; y++)
            if(!parentPanel.getMap().getTile(x, y).isSeen())
               paintHex(g2d, parentPanel.getMap().getTile(x, y), x, y);
      // paint over OOB paths
      for(int x = 0; x < w; x++)
      {
         paintHex(g2d, OOB_HEX, x, -1);
         paintHex(g2d, OOB_HEX, x, parentPanel.getMap().getHeight());
      }
      for(int y = 0; y < h; y++)
      {
         paintHex(g2d, OOB_HEX, -1, y);
         paintHex(g2d, OOB_HEX, parentPanel.getMap().getWidth(), y);
      }
      // paint token
      if(true)
      {
         int xLoc = (int)((mapToken.getXLoc() - mapToken.getRadius()) * scale);
         int yLoc = (int)((mapToken.getYLoc() - mapToken.getRadius())* scale);
         int diameter = (int)(mapToken.getRadius() * scale * 2);
         g2d.setColor(TOKEN_COLOR);
         g2d.fillOval(xLoc, yLoc, diameter, diameter);
         g2d.setColor(Color.BLACK);
         g2d.drawOval(xLoc, yLoc, diameter, diameter);
      }
   }
   
   private void paintHex(Graphics2D g2d, MapHex tile, int xPos, int yPos)
   {
      double[] xPoints = indentX(xPos, yPos, HEX_POINTS_X);
      double[] yPoints = indentY(xPos, yPos, HEX_POINTS_Y);
      xPoints = scale(scale, xPoints);
      yPoints = scale(scale, yPoints);
      // draw background
      g2d.setColor(tile.getBackground());
      if(parentPanel.explorationMode && !tile.isSeen())
         g2d.setColor(Color.BLACK);
      g2d.fillPolygon(doubleToInt(xPoints), doubleToInt(yPoints), xPoints.length);
      // draw border
      g2d.setColor(Color.BLACK);
      if(parentPanel.explorationMode)
         g2d.setColor(Color.GRAY);
      g2d.drawPolygon(doubleToInt(xPoints), doubleToInt(yPoints), xPoints.length);
      // draw image
      double xOrigin = getXInset(xPos, yPos) + R_SHORT - (SQUARE_SIDE / 2);
      double yOrigin = getYInset(xPos, yPos) + R_LONG - (SQUARE_SIDE / 2);
      if(!parentPanel.explorationMode || tile.isSeen())
      {
         {
            if(tile.getImage() != null)
            {
               BufferedImage img = tile.getScaledImage(scale);
               g2d.drawImage(img, (int)(xOrigin * scale), (int)(yOrigin * scale), null);
            }
         }
      }
      if(!parentPanel.explorationMode || tile.isSeen())
      {
         // draw big PoI
         if(tile.getBigImage() != null)
         {
            BufferedImage img = tile.getScaledBigImage(scale);
            g2d.drawImage(img, (int)(xOrigin * scale), (int)(yOrigin * scale), null);
         }
      }
      // draw small PoI
      BufferedImage smallImage = tile.getSmallImage();
      if(parentPanel.explorationMode && tile.isSeen() && !tile.isExplored())
         smallImage = tile.getUnexploredImage(scale);
      else
      {
         if(tile.getSmallImage() == null)
            smallImage = null;
         else
            smallImage = tile.getScaledSmallImage(scale);
      }
      if(smallImage != null)
      {
         double smallImageXOrigin = xOrigin + (SQUARE_SIDE / 4);
         double smallImageYOrigin = yOrigin + (SQUARE_SIDE * .75);
         g2d.drawImage(smallImage, (int)(smallImageXOrigin * scale), (int)(smallImageYOrigin * scale), null);
      }
   }
}