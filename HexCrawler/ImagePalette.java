package HexCrawler;

import java.awt.image.*;
import java.awt.*;
import java.util.*;

public class ImagePalette
{
   private static Vector<HexImage> imageList = null;
   
   public static void load(String directoryPath)
   {
      HexImageLoader loader = new HexImageLoader(directoryPath);
      imageList = loader.getImages();
   }
   
   public static Vector<HexImage> getImageList()
   {
      return imageList;
   }
   
   public static BufferedImage getImage(String name)
   {
      if(name == null)
         return null;
      for(HexImage img : imageList)
      {
         if(img.matches(name))
            return img.getBufferedImage();
      }
      System.out.println("Could not find image '" + name + "'");
      return null;
   }
   
   // assumes square image
   public static BufferedImage scaleToPixelSize(BufferedImage original, int newSize)
   {
      if(newSize == 0 || original == null || original.getWidth() < 1 || original.getHeight() < 1)
         return null;
      Image scaledImage = original.getScaledInstance(newSize, newSize, Image.SCALE_SMOOTH);
      BufferedImage newBuffered = new BufferedImage(newSize, newSize, BufferedImage.TYPE_INT_ARGB);
      Graphics2D g2d = newBuffered.createGraphics();
      g2d.drawImage(scaledImage, 0, 0 , null);
      g2d.dispose();
      return newBuffered;
   }
}