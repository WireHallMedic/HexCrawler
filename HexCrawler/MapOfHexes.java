package HexCrawler;

import java.util.*;

public class MapOfHexes implements HexCrawlerConstants
{
	private MapHex[][] tileArray;
	private int width;
	private int height;
   private Vector<LinearPath> pathList;
   private MapToken mapToken;


	public MapHex[][] getTileArray(){return tileArray;}
	public int getWidth(){return width;}
	public int getHeight(){return height;}
   public Vector<LinearPath> getPathList(){return pathList;}
   public MapToken getMapToken(){return mapToken;}


	public void setTileArray(MapHex[][] t){tileArray = t;}
	public void setWidth(int w){width = w;}
	public void setHeight(int h){height = h;}
   public void setMapToken(MapToken mt){mapToken = mt;}


   public MapOfHexes(int w, int h)
   {
      setSize(w, h);
      pathList = new Vector<LinearPath>();
      mapToken = new MapToken();
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
   
   public Vector<String> serialize()
   {
      Vector<String> outList = new Vector<String>();
      outList.add("" + width);
      outList.add("" + height);
      for(int x = 0; x < width; x++)
      for(int y = 0; y < height; y++)
         outList.add(tileArray[x][y].serialize());
      outList.add(mapToken.serialize());
      for(LinearPath path : pathList)
         outList.add(path.serialize());
      return outList;
   }
   
   public void deserialize(Vector<String> strList)
   {
      int w = Integer.parseInt(strList.elementAt(0));
      int h = Integer.parseInt(strList.elementAt(1));
      setSize(w, h);
      pathList = new Vector<LinearPath>();
      int numOfHexes = w * h;
      for(int i = 0; i < numOfHexes; i++)
      {
         tileArray[i / width][i % height].deserialize(strList.elementAt(i + 2));
      }
      mapToken.deserialize(strList.elementAt(numOfHexes + 2));
      for(int i = numOfHexes + 3; i < strList.size(); i++)
      {
         LinearPath newPath = new LinearPath();
         newPath.deserialize(strList.elementAt(i));
         addPath(newPath);
      }
   }
   
   public void addPath(LinearPath path)
   {
      pathList.add(path);
   }
   
   public void clearPaths()
   {
      pathList = new Vector<LinearPath>();
   }
   
   // delete paths of less than 2 points
   public void cleanPaths()
   {
      for(int i = 0; i < pathList.size(); i++)
      {
         if(pathList.elementAt(i).size() < 2)
         {
            pathList.removeElementAt(i);
            i--;
         }
      }
   }
   
   public LinearPath getPath(double x, double y)
   {
      for(int i = 0; i < pathList.size(); i++)
         if(pathList.elementAt(i).contains(x, y))
            return pathList.elementAt(i);
      return null;
   }
   
   public void removePath(LinearPath p)
   {
      pathList.remove(p);
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
      
      map.addPath(LinearPath.mock());
      return map;
   }
}