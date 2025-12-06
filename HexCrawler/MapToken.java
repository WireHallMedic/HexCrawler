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
   }
   
   public MapToken()
   {
      xLoc = .5;
      yLoc = .5;
      radius = .5;
   }
   
   public boolean isInRadius(double x, double y)
   {
      double xOffset = xLoc - x;
      double yOffset = yLoc - y;
      System.out.println("xOffset^2 = " + (xOffset * xOffset));
      System.out.println("yOffset^2 = " + (yOffset * yOffset));
      System.out.println("radius^2 =  " + (radius * radius));
      
      return (radius * radius) >= (xOffset * xOffset) + (yOffset * yOffset);  
   }
}