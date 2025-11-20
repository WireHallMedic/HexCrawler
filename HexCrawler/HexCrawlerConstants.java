package HexCrawler;

import java.awt.*;

public interface HexCrawlerConstants
{
   public static final double R_LONG = 0.5;
   public static final double R_SHORT = Math.cos(Math.PI/6.0) * R_LONG;
   public static final double HEX_WIDTH = 2.0 * R_SHORT;
   public static final double HEX_HEIGHT = 1.0;
   public static final double X_STEP = HEX_WIDTH;
   public static final double Y_STEP = .75;
   public static final double ODD_X_INSET = R_SHORT;
   public static final double EVEN_X_INSET = 0.0;
   public static final double[] HEX_POINTS_X = {R_SHORT, 2.0 * R_SHORT, 2.0 * R_SHORT, R_SHORT, 0.0, 0.0};
   public static final double[] HEX_POINTS_Y = {0.0, .25, .75, 1.0, .75, .25};
   
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
   public static final Color MOUNTAIN_FOREST_COLOR = new Color(102, 118, 33);
   public static final Color HILL_FOREST_COLOR = new Color(72, 138, 33);
}