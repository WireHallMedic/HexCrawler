// a class for holding loaded images and their names

package HexCrawler;

import java.awt.image.*;

public class HexImage
{
	private BufferedImage bufferedImage;
	private String name;
   private String shortName;


	public BufferedImage getBufferedImage(){return bufferedImage;}
	public String getName(){return name;}
   public String getShortName(){return shortName;}
   
   
   public boolean matches(String searchName)
   {
      if(name.equals(searchName) || shortName.equals(searchName))
         return true;
      return false;
   }
   
   
   public HexImage(BufferedImage bi, String n)
   {
      bufferedImage = bi;
      name = n;
      shortName = name.split("\\.")[0];
   }
}