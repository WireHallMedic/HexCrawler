package HexCrawler;

import java.util.*;

public class MapOfHexes implements HexCrawlerConstants
{
	private MapHex[][] tileArray;
	private int width;
	private int height;


	public MapHex[][] getTileArray(){return tileArray;}
	public int getWidth(){return width;}
	public int getHeight(){return height;}


	public void setTileArray(MapHex[][] t){tileArray = t;}
	public void setWidth(int w){width = w;}
	public void setHeight(int h){height = h;}


   public MapOfHexes(int w, int h)
   {
      setSize(w, h);
   }
   
   public MapOfHexes()
   {
      this(7, 7);
   }
   
   public void setSize(int w, int h)
   {
      width = w;
      height = h;
      tileArray = new MapHex[width][height];
      setAll(Terrain.NONE);
   }
   
   public void setAll(Terrain terrain)
   {
      for(int x = 0; x < width; x++)
      for(int y = 0; y < height; y++)
      {
         tileArray[x][y] = new MapHex(terrain);
      }
   }
   
   public MapHex getTile(int x, int y)
   {
      if(x >= 0 && x < width &&
         y >= 0 && y < height)
         return tileArray[x][y];
      return null;
   }
   
   public static MapOfHexes mock()
   {
      int w = 9;
      int h = 9;
      MapOfHexes map = new MapOfHexes(w, h);
      int i = 0;
      for(int y = 0; y < h; y++)
      for(int x = 0; x < w; x++)
      {
         map.tileArray[x][y].set(Terrain.values()[i]);
         i++;
         if(i >= Terrain.values().length)
            i = 0;
      }
      map.tileArray[1][0].setBigImage(PointOfInterest.TOWN);
      map.tileArray[2][0].setSmallImage(PointOfInterest.X);
      
      return map;
   }
}