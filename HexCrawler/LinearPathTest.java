package HexCrawler;

import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class LinearPathTest {


   /** Fixture initialization (common initialization for all tests). **/
   @Before public void setUp(){}


   @Test public void testVertical() 
   {
      LinearPath lp = new LinearPath();
      lp.add(.5, .5);
      lp.add(.5, 1.5);
      assertTrue("Point on vertical line is on line.", lp.contains(.5, 1.0));
      assertTrue("Point close to vertical line is on line.", lp.contains(.54, 1.0));
      assertFalse("Point not close to vertical line is not on line.", lp.contains(.57, 1.0));
      assertFalse("Point above vertical line is not on line.", lp.contains(.5, 0.0));
      assertFalse("Point below vertical line is not on line.", lp.contains(.5, 2.0));
   }

   @Test public void testHorizontal() 
   {
      LinearPath lp = new LinearPath();
      lp.add(.5, .5);
      lp.add(1.5, .5);
      assertTrue("Point on horizontal line is on line.", lp.contains(1.0, .5));
      assertTrue("Point close to horizontal line is on line.", lp.contains(1.0, .54));
      assertFalse("Point not close to horizontal line is not on line.", lp.contains(1.0,.57));
      assertFalse("Point left of horizontal line is not on line.", lp.contains(0.0, .5));
      assertFalse("Point right of horizontal line is not on line.", lp.contains(2.0, .5));
   }
}
