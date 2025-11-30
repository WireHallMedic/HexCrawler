package HexCrawler;

import java.awt.*;
import java.awt.image.*;
import java.util.*;

public class MapHex implements HexCrawlerConstants
{
	private Color background;
	private HexImage image;
	private HexImage bigImage;
	private HexImage smallImage;
	private boolean seen;
	private boolean explored;


	public Color getBackground(){return background;}
	public BufferedImage getImage(){return image.getBufferedImage();}
	public BufferedImage getBigImage(){return bigImage.getBufferedImage();}
	public BufferedImage getSmallImage(){return smallImage.getBufferedImage();}
	public boolean isSeen(){return seen;}
	public boolean isExplored(){return explored;}


	public void setBackground(Color b){background = b;}
	public void setSeen(boolean s){seen = s;}
	public void setExplored(boolean e){explored = e;}


   public MapHex(Color bg, Terrain t)
   {
      background = bg;
      image = new HexImage(null, null);
      bigImage = new HexImage(null, null);
      smallImage = new HexImage(null, null);
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
      if(terrain == null)
         image = new HexImage(null, "null");
      else
         image = new HexImage(ImagePalette.getImage(terrain.imageName), terrain.imageName);
   }
   
   public void setBigImage(PointOfInterest poi)
   {
      if(poi == null)
         bigImage = new HexImage(null, "null");
      else
         bigImage = new HexImage(ImagePalette.getImage(poi.imageName), poi.imageName);
   }
   
   public void setSmallImage(PointOfInterest poi)
   {
      if(poi == null)
         smallImage = new HexImage(null, "null");
      else
         smallImage = new HexImage(ImagePalette.getImage(poi.imageName), poi.imageName);
   }
   
   public void set(Terrain terrain)
   {
      setBackground(terrain.background);
      setImage(terrain);
   }
   
   public BufferedImage getScaledImage(double scale)
   {
      return getScaledInstance(scale, getImage());
   }
   
   public BufferedImage getScaledBigImage(double scale)
   {
      if(bigImage == null)
         return null;
      return getScaledInstance(scale, getBigImage());
   }
   
   public BufferedImage getScaledSmallImage(double scale)
   {
      if(smallImage == null)
         return null;
      return getScaledInstance(scale / 2.0, getSmallImage());
   }
   
   public BufferedImage getUnexploredImage(double scale)
   {
      return getScaledInstance(scale / 2.0, ImagePalette.getImage("questionmark"));
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
   
   public String serialize()
   {
      String outStr = image.getShortName() + DELIMITER;
      outStr += bigImage.getShortName() + DELIMITER;
      outStr += smallImage.getShortName() + DELIMITER;
      if(seen)
         outStr += "t";
      else
         outStr += "f";
      outStr += DELIMITER;
      if(explored)
         outStr += "t";
      else
         outStr += "f";
      return outStr;
   }
   
   public void deserialize(String str)
   {
      String[] strList = str.split(DELIMITER);
      set(Terrain.getByName(strList[0]));
      setBigImage(PointOfInterest.getByName(strList[1]));
      setSmallImage(PointOfInterest.getByName(strList[2]));
      if(strList[3].equals("t"))
         seen = true;
      else
         seen = false;
      if(strList[4].equals("t"))
         explored = true;
      else
         explored = false;
   }
}
