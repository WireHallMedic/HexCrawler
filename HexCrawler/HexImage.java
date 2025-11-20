// a class for holding loaded images and their names

package HexCrawler;

import java.awt.image.*;

public class HexImage
{
	private BufferedImage bufferedImage;
	private String name;


	public BufferedImage getBufferedImage(){return bufferedImage;}
	public String getName(){return name;}


	public void setBufferedImage(BufferedImage b){bufferedImage = b;}
	public void setName(String n){name = n;}
   
   
   public HexImage(BufferedImage bi, String n)
   {
      bufferedImage = bi;
      name = n;
   }
}