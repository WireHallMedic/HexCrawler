package HexCrawler;

import HexCrawler.*;
import java.awt.image.*;
import java.util.*;

public class ImagePalette
{
   private Vector<HexImage> imageList;
   
   public ImagePalette(String directoryPath)
   {
      HexImageLoader loader = new HexImageLoader("./res");
      imageList = loader.getImages();
   }
   
   public Vector<HexImage> getImageList()
   {
      return imageList;
   }
   
   public BufferedImage getImage(String name)
   {
      for(HexImage img : imageList)
      {
         if(img.matches(name))
            return img.getBufferedImage();
      }
      return null;
   }
}