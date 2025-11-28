package HexCrawler;

import javax.swing.*;
import java.util.*;
import java.awt.event.*;
import java.io.*;

public class FileManager
{
   public static void load(MainPanel mainPanel)
   {
   
   }
   
   public static void save(MainPanel mainPanel)
   {
      try
      {
         Vector<String> saveList = mainPanel.getMap().serialize();
         for(String str : saveList)
            System.out.println(str);
         PrintWriter writer = new PrintWriter("HexCrawler.dat", "UTF-8");
         for(String str : saveList)
            writer.println(str);
         writer.close();
      }
      catch(Exception ex)
      {
         System.out.println("Failed to save\n" + ex.toString());
      }
   }
}