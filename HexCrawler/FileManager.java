package HexCrawler;

import javax.swing.*;
import java.util.*;
import java.awt.event.*;

public class FileManager
{
   public static void load(MainPanel mainPanel)
   {
   
   }
   
   public static void save(MainPanel mainPanel)
   {
      Vector<String> saveList = mainPanel.getMap().serialize();
      for(String str : saveList)
         System.out.println(str);
   }
}