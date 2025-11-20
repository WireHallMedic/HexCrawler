package Hexcrawler;

import java.io.File;
import java.util.*;
import java.awt.image.*;
import java.awt.event.*;
import javax.imageio.*;
import HexCrawler.*;

public class ImageLoader
{
   private Vector<File> fileList;
   
   public ImageLoader(String directoryPath)
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
         try
         {
            img = ImageIO.read(file);
            imgList.add(new HexImage(img, file.getName()));
         } 
         catch (Exception ex)
         {
            System.err.println("Error loading image: " + ex.getMessage());
            ex.printStackTrace();
         }
      }
      return imgList;
   }

   public static void main(String[] args) 
   {
      ImageLoader imageLoader = new ImageLoader("./res");
      imageLoader.printFileNames();
      System.out.println("Total Images: " + imageLoader.getImages().size());
   }
}