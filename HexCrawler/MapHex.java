package HexCrawler;

import java.awt.*;
import java.awt.image.*;
import java.util.*;

public class MapHex
{
   private Color background;
	private BufferedImage image;


	public Color getBackground(){return background;}
	public BufferedImage getImage(){return image;}


	public void setBackground(Color b){background = b;}
	public void setImage(BufferedImage i){image = i;}

   public MapHex(Color bg, BufferedImage img)
   {
      background = bg;
      image = img;
   }
}