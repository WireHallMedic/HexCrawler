package HexCrawler;

import java.util.*;

public class MapOfHexes implements HexCrawlerConstants
{
	private MapHex[][] tileArray;
	private int width;
	private int height;
   private LinearPath[] pathList;


	public MapHex[][] getTileArray(){return tileArray;}
	public int getWidth(){return width;}
	public int getHeight(){return height;}
   public LinearPath getPath(int i){return pathList[i];}


	public void setTileArray(MapHex[][] t){tileArray = t;}
	public void setWidth(int w){width = w;}
	public void setHeight(int h){height = h;}
   public void setPath(LinearPath path, int i){pathList[i] = path;}


   public MapOfHexes(int w, int h)
   {
      setSize(w, h);
   }
   
   public MapOfHexes()
   {
      this(7, 7);
      pathList = new LinearPath[MAX_LINEAR_PATHS];
      for(int i = 0; i < pathList.length; i++)
         pathList[i] = new LinearPath();
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
   
   public Vector<String> serialize()
   {
      Vector<String> outList = new Vector<String>();
      outList.add("" + width);
      outList.add("" + height);
      for(int x = 0; x < width; x++)
      for(int y = 0; y < height; y++)
         outList.add(tileArray[x][y].serialize());
      return outList;
   }
   
   public void deserialize(Vector<String> strList)
   {
      int w = Integer.parseInt(strList.elementAt(0));
      int h = Integer.parseInt(strList.elementAt(1));
      setSize(w, h);
      for(int i = 0; i < strList.size() - 2; i++)
      {
         tileArray[i / width][i % height].deserialize(strList.elementAt(i + 2));
      }
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