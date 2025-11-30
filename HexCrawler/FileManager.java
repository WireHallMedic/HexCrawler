package HexCrawler;

import javax.swing.*;
import java.util.*;
import java.awt.event.*;
import java.io.*;
import java.awt.*;

public class FileManager
{
   private static final String FILE_EXTENSION = ".hcm";
   
   public static void load(MainPanel mainPanel)
   {
		Scanner inFile = null;
      String fileName = "HexCrawler" + FILE_EXTENSION;
      fileName = getFileName(mainPanel);
      if(fileName.equals(""))
         return;
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
         String fileName = getFileName(mainPanel);
         if(fileName.equals(""))
            return;
         if(!fileName.contains(FILE_EXTENSION))
            fileName = fileName + FILE_EXTENSION;
         PrintWriter writer = new PrintWriter(fileName, "UTF-8");
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
   
   public static String getFileName(Component component)
   {
      String fileName = "";
      JFileChooser fc;
      fc = new JFileChooser(".");
      fc.setDialogTitle("Choose file.");
      fc.setApproveButtonText("Select");
      int returnVal = fc.showOpenDialog(component);
      if(returnVal == JFileChooser.APPROVE_OPTION)
      {
         fileName = fc.getSelectedFile().getName();
      }
      return fileName;
   }
   
}