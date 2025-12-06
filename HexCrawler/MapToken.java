package HexCrawler;

import java.awt.image.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MapToken implements HexCrawlerConstants
{
	private double xLoc;
	private double yLoc;
	private double radius;


	public double getXLoc(){return xLoc;}
	public double getYLoc(){return yLoc;}
	public double getRadius(){return radius;}


	public void setXLoc(double x){xLoc = x;}
	public void setYLoc(double y){yLoc = y;}
	public void setRadius(double r){radius = r;}

   
   public void setLoc(double x, double y)
   {
      xLoc = x;
      yLoc = y;
   }
   
   public MapToken()
   {
      xLoc = .5;
      yLoc = .5;
      radius = .25;
   }
   
   public boolean isInRadius(double x, double y)
   {
      double xOffset = xLoc - x;
      double yOffset = yLoc - y;
      
      return (radius * radius) >= (xOffset * xOffset) + (yOffset * yOffset);  
   }
   
   public String serialize()
   {
      return xLoc + DELIMITER + yLoc;
   }
   
   public void deserialize(String str)
   {
      String[] vals = str.split(DELIMITER);
      try
      {
         xLoc = Double.parseDouble(vals[0]);
         yLoc = Double.parseDouble(vals[1]);
      }
      catch(Exception ex)
      {
         System.out.println("Failed to load token from file.");
      }
   }
}