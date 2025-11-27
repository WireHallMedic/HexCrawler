package HexCrawler;

import java.awt.*;
import java.awt.image.*;
import java.util.*;

public class MapHex implements HexCrawlerConstants
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
   
   public void setImage(Terrain terrain)
   {
      setImage(ImagePalette.getImage(terrain.imageName));
   }
   
   public void set(Terrain terrain)
   {
      setBackground(terrain.background);
      setImage(terrain);
   }
   
   public BufferedImage getScaledImage(double scale)
   {
      int width = (int)(scale * SQUARE_SIDE);
      int height = (int)(scale * SQUARE_SIDE);
      Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
      BufferedImage newBuffered = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
      Graphics2D g2d = newBuffered.createGraphics();
      g2d.drawImage(scaledImage, 0, 0 , null);
      g2d.dispose();
      return newBuffered;
   }
}