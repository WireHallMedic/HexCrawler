package HexCrawler;

import java.awt.*;
import java.awt.image.*;
import java.util.*;

public class MapHex implements HexCrawlerConstants
{
	private Color background;
	private BufferedImage image;
	private BufferedImage bigImage;
	private BufferedImage smallImage;
	private boolean seen;
	private boolean explored;


	public Color getBackground(){return background;}
	public BufferedImage getImage(){return image;}
	public BufferedImage getBigImage(){return bigImage;}
	public BufferedImage getSmallImage(){return smallImage;}
	public boolean isSeen(){return seen;}
	public boolean isExplored(){return explored;}


	public void setBackground(Color b){background = b;}
	public void setImage(BufferedImage i){image = i;}
	public void setBigImage(BufferedImage b){bigImage = b;}
	public void setSmallImage(BufferedImage s){smallImage = s;}
	public void setSeen(boolean s){seen = s;}
	public void setExplored(boolean e){explored = e;}


   public MapHex(Color bg, BufferedImage img)
   {
      background = bg;
      image = img;
      bigImage = null;
      smallImage = null;
      seen = false;
      explored = false;
   }
   
   public MapHex(Terrain terrain)
   {
      this(Color.WHITE, null);
      set(terrain);
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
      return getScaledInstance(scale, image);
   }
   
   public BufferedImage getScaledBigImage(double scale)
   {
      if(bigImage == null)
         return null;
      return getScaledInstance(scale, bigImage);
   }
   
   public BufferedImage getScaledSmallImage(double scale)
   {
      if(smallImage == null)
         return null;
      return getScaledInstance(scale, smallImage);
   }
   
   private BufferedImage getScaledInstance(double scale, BufferedImage original)
   {
      // size
      int width = (int)(scale * SQUARE_SIDE);
      int height = (int)(scale * SQUARE_SIDE);
      // copy image
      Image scaledImage = original.getScaledInstance(width, height, Image.SCALE_SMOOTH);
      BufferedImage newBuffered = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
      Graphics2D g2d = newBuffered.createGraphics();
      g2d.drawImage(scaledImage, 0, 0 , null);
      g2d.dispose();
      return newBuffered;
   }
}