package HexCrawler;

import javax.swing.*;
import java.util.*;
import java.awt.event.*;
import java.io.*;

public class FileManager
{
   public static void load(MainPanel mainPanel)
   {
		Scanner inFile = null;
      String fileName = "HexCrawler.dat";
      Vector<String> strList = new Vector<String>();
		try
		{
			inFile = new Scanner(new FileReader(fileName));
		}
		catch(Exception ex)
		{
			String errorMessage = "Error: Cannot read from " + fileName;
			JOptionPane.showMessageDialog(null, errorMessage, "Exception Occured", JOptionPane.ERROR_MESSAGE);
		   return;
      }
		while(inFile.hasNext() == true)
			strList.add(inFile.nextLine());
		inFile.close();
      mainPanel.getMap().deserialize(strList);
   }
   
   public static void save(MainPanel mainPanel)
   {
      try
      {
         Vector<String> saveList = mainPanel.getMap().serialize();
         PrintWriter writer = new PrintWriter("HexCrawler.dat", "UTF-8");
         for(String str : saveList)
            writer.println(str);
         writer.close();
      }
      catch(Exception ex)
      {
			String errorMessage = ex.toString();
			JOptionPane.showMessageDialog(null, errorMessage, "Exception Occured", JOptionPane.ERROR_MESSAGE);
      }
   }
}