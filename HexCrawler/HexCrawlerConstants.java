package HexCrawler;

import java.awt.*;

public interface HexCrawlerConstants
{
   public static final double R_LONG = 0.5;  // long radius of hex, also edge length of hex
   public static final double R_SHORT = Math.cos(Math.PI/6.0) * R_LONG; // short radius of hex
   public static final double HEX_WIDTH = 2.0 * R_SHORT;
   public static final double HEX_HEIGHT = 1.0;
   public static final double SQUARE_SIDE = R_LONG * (3 - Math.sqrt(3));
   public static final double X_STEP = HEX_WIDTH;
   public static final double Y_STEP = .75;
   public static final double ODD_X_INSET = R_SHORT;
   public static final double EVEN_X_INSET = 0.0;
   public static final double[] HEX_POINTS_X = {R_SHORT, 2.0 * R_SHORT, 2.0 * R_SHORT, R_SHORT, 0.0, 0.0};
   public static final double[] HEX_POINTS_Y = {0.0, .25, .75, 1.0, .75, .25};
   public static final int MAX_LINEAR_PATHS = 12;
   
   public static final Color BEACH_COLOR = new Color(253, 251, 151);
   public static final Color GRASS_COLOR = new Color(149, 198, 99);
   public static final Color FOREST_COLOR = new Color(57, 145, 45);
   public static final Color HEAVY_FOREST_COLOR = new Color(47, 118, 33);
   public static final Color HILL_COLOR = new Color(231, 206, 90);
   public static final Color MOUNTAIN_COLOR = new Color(205, 155, 0);
   public static final Color BADLAND_COLOR = new Color(160, 160, 160);
   public static final Color SWAMP_COLOR = new Color(173, 213, 170);
   public static final Color TUNDRA_COLOR = new Color(255, 255, 255);
   public static final Color DESERT_COLOR = new Color(240, 225, 113);
   public static final Color WATER_COLOR = new Color(152, 204, 251);
   public static final Color ROAD_COLOR = new Color(73, 32, 0);
   public static final Color MOUNTAIN_FOREST_COLOR = new Color(102, 118, 33);
   public static final Color HILL_FOREST_COLOR = new Color(72, 138, 33);
   
   public enum Terrain
   {
      NONE              (Color.GRAY, null),
      GRASSLAND         (GRASS_COLOR, "grassland"),
      GRASSLAND_HILLS   (GRASS_COLOR, "grasslandhills"),
      LIGHT_FOREST      (FOREST_COLOR, "evergreen"),
      HEAVY_FOREST      (FOREST_COLOR, "heavyevergreen"),
      FORESTED_HILLS    (HILL_FOREST_COLOR, "evergreenhills"),
      FORESTED_MOUNTAIN (MOUNTAIN_FOREST_COLOR, "evergreenmountain"),
      FARMLAND          (GRASS_COLOR, "cultivatedfarmland"),
      HILLS             (MOUNTAIN_COLOR, "hills"),
      MOUNTAINS         (MOUNTAIN_COLOR, "mountains"),
      MARSH             (SWAMP_COLOR, "marsh"),
      SWAMP             (SWAMP_COLOR, "swamp"),
      JUNGLE            (FOREST_COLOR, "junglelight"),
      HEAVY_JUNGLE      (FOREST_COLOR, "jungle"),
      JUNGLE_HILLS      (HILL_FOREST_COLOR, "junglehills"),
      JUNGLE_MOUNTAIN   (MOUNTAIN_FOREST_COLOR, "junglemountain"),
      SANDY_DESERT      (DESERT_COLOR, "sandydesert"),
      ROCKY_DESERT      (DESERT_COLOR, "rockydesert"),
      TUNDRA            (TUNDRA_COLOR, "snowfields"),
      WATER             (WATER_COLOR, null);
      
      public Color background;
      public String imageName;
      
      private Terrain(Color bg, String img)
      {
         background = bg;
         imageName = img;
      }
      
      public Terrain getByIndex(int index)
      {
         return values()[index];
      }
      
      public static Terrain getByName(String str)
      {
         for(Terrain t : Terrain.values())
            if(str.equals(t.imageName))
               return t;
         return NONE;
      }
   }
   
   public enum PointOfInterest
   {
      NONE           (null),
      CAMP           ("camp"),
      VILLAGE        ("village"),
      TOWN           ("town"),
      CITY           ("city"),
      CASTLE         ("castle"),
      TEMPLE         ("temple"),
      TOWER          ("tower"),
      CHURCH         ("church"),
      FORT           ("fort"),
      RUINS          ("ruins"),
      BATTLE         ("battle"),
      BRIDGE         ("bridge"),
      CAVE           ("cave"),
      MONOLITH       ("monolith"),
      MONUMENT       ("monument"),
      SHIPWRECK      ("shipwreck"),
      SHRINE         ("shrine"),
      STAR           ("star"),
      X              ("x"),
      QUESTION_MARK  ("questionmark");
      
      public String imageName;
      
      private PointOfInterest(String img)
      {
         imageName = img;
      }
      
      public PointOfInterest getByIndex(int index)
      {
         return values()[index];
      }
      
      public static PointOfInterest getByName(String str)
      {
         for(PointOfInterest poi : PointOfInterest.values())
            if(str.equals(poi.imageName))
               return poi;
         return NONE;
      }
   }
}