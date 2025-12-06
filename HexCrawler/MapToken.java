package HexCrawler;

import java.awt.image.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MapToken
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
      radius = 1.0;
   }
   
   public MapToken()
   {
      xLoc = 1.0;
      yLoc = 1.0;
   }
   
   public boolean isInRadius(double x, double y)
   {
      double xOffset = xLoc - x;
      double yOffset = yLoc - y;
      
      return (radius * radius) <= (xOffset * xOffset) + (yOffset * yOffset);  
   }
}