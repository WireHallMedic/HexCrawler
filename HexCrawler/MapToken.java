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
	private double diameter;


	public double getXLoc(){return xLoc;}
	public double getYLoc(){return yLoc;}
	public double getDiameter(){return diameter;}


	public void setXLoc(double x){xLoc = x;}
	public void setYLoc(double y){yLoc = y;}
	public void setDiameter(double r){diameter = r;}

   
   public void setLoc(double x, double y)
   {
      xLoc = x;
      yLoc = y;
   }
   
   public MapToken()
   {
      xLoc = 0.0;
      yLoc = 0.0;
      diameter = .5;
   }
   
   public boolean isInDiameter(double x, double y)
   {
      double xOffset = xLoc - x;
      double yOffset = yLoc - y;
      
      return (diameter * diameter) <= (xOffset * xOffset) + (yOffset * yOffset);  
   }
}