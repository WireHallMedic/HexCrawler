package Hexcrawler;

import java.io.File;
import java.util.*;
import java.awt.image.*;

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
      
      File[] files = directory.listFiles(); // returns File objects
      
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
   
   public Vector<BufferedImage> getImages()
   {
      Vector<BufferedImage> imgList = new Vector<BufferedImage>();
      
      return imgList;
   }

   public static void main(String[] args) 
   {
      ImageLoader imageLoader = new ImageLoader("./res");
      imageLoader.printFileNames();
   }
}