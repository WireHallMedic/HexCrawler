package Hexcrawler;

import java.io.File;
import java.util.*;

public class ImageLoader
{
   private static Vector<String> fileList;
   
   public ImageLoader()
   {
      
   }
   
   public void loadFiles(String directoryPath)
   {
      fileList = new Vector<String>();
      File directory = new File(directoryPath);
      
      File[] files = directory.listFiles(); // returns File objects
      
      if (files != null) 
      {
         for (File file : files) 
         {
            if (file.isFile()) 
            {
               fileList.add(file.getName());
            }
         }
      }
   }

   public void printFileNames()
   {
      for(String name : fileList)
         System.out.println(name);
   }

   public static void main(String[] args) 
   {
      ImageLoader imageLoader = new ImageLoader();
      imageLoader.loadFiles("./res");
      imageLoader.printFileNames();
   }
}