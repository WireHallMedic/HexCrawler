package HexCrawler;

import java.io.File;
import java.util.*;
import java.awt.image.*;
import java.awt.event.*;
import javax.imageio.*;

public class HexImageLoader
{
   private Vector<File> fileList;
   
   public HexImageLoader(String directoryPath)
   {
      loadFiles(directoryPath);
   }
   
   public void loadFiles(String directoryPath)
   {
      fileList = new Vector<File>();
      File directory = new File(directoryPath);
      
      File[] files = directory.listFiles();
      
      if (files != null) 
      {
         for (File file : files) 
         {
            if (file.isFile()) 
            {
               fileList.add(file);
            }
         }
      }
   }

   public void printFileNames()
   {
      for(File file : fileList)
         System.out.println(file.getName());
   }
   
   public Vector<HexImage> getImages()
   {
      Vector<HexImage> imgList = new Vector<HexImage>();
      BufferedImage img;
      
      for(File file : fileList)
      {
         if(file.exists())
         {
            try
            {
               img = ImageIO.read(file);
               imgList.add(new HexImage(img, file.getName()));
            } 
            catch (Exception ex)
            {
               System.out.println("Error loading image: " + ex.getMessage());
               ex.printStackTrace();
            }
         }
         else
         {
            System.out.println("Non-existant file: " + file.getName());
         }
      }
      return imgList;
   }

   public static void main(String[] args) 
   {
      HexImageLoader imageLoader = new HexImageLoader("./res");
      imageLoader.printFileNames();
      System.out.println("Total Images: " + imageLoader.getImages().size());
   }
}