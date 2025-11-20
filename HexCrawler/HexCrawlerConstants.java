package HexCrawler;

public interface HexCrawlerConstants
{
   public static final double R_SHORT = Math.sqrt(2) / 2.0;
   public static final double R_LONG = 0.5;
   public static final double HEX_WIDTH = 2.0 * R_SHORT;
   public static final double HEX_HEIGHT = 1.0;
   public static final double X_STEP = HEX_WIDTH;
   public static final double Y_STEP = 1.5;
   public static final double EVEN_X_INSET = R_SHORT;
   public static final double EVEN_Y_INSET = .75;
   public static final double ODD_X_INSET = 0.0;
   public static final double ODD_Y_INSET = 0.0;
   public static final double[] HEX_POINTS_X = {R_SHORT, 2.0 * R_SHORT, 2.0 * R_SHORT, R_SHORT, 0.0, 0.0};
   public static final double[] HEX_POINTS_Y = {0.0, .25, .75, 1.0, .75, .25};
}