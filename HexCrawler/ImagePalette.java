package HexCrawler;

import java.awt.image.*;
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
      for(HexImage img : imageList)
      {
         if(img.matches(name))
            return img.getBufferedImage();
      }
      return null;
   }
}