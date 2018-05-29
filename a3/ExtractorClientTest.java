import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;
import java.util.SortedSet;


public class ExtractorClientTest {
   
/**
 * Sharing site for A3 JUnit tests
 */
 
 // Here are some test cases for slopeTo in the Point class
 //-- Tanner Oakes 2017-02-17 13:52 UTC-6
 // slopeTo test
    @Test public void slopeToTest1() {
       Point a = new Point(1, 1);
       Point b = new Point(2, 2);
       double expected = 1;
       double actual = a.slopeTo(b);
       assertEquals(expected, actual, 0);
    }
    
    // slopeTo test
    @Test public void slopeToTest2() {
       Point a = new Point(2, 3);
       Point b = new Point(5, 4);
       double expected = 0.33;
       double actual = a.slopeTo(b);
       assertEquals(expected, actual, .1);
    }
    
    // slopeTo test horizontal line
    @Test public void slopeToTest3() {
       Point a = new Point(2, 3);
       Point b = new Point(2, 4);
       double expected = Double.POSITIVE_INFINITY;
       double actual = a.slopeTo(b);
       assertEquals(expected, actual, .1);
    }
    
    //slopeTo test vertical line
    @Test public void slopeToTest4() {
       Point a = new Point(2, 3);
       Point b = new Point(3, 3);
       double expected = 0.0;
       double actual = a.slopeTo(b);
       assertEquals(expected, actual, .1);
    }
    
    //slopeTo test same points
    @Test public void slopeToTest5() {
       Point a = new Point(2, 3);
       Point b = new Point(2, 3);
       double expected = Double.NEGATIVE_INFINITY;
       double actual = a.slopeTo(b);
       assertEquals(expected, actual, .1);
    }



/* Because it's good to test other methods, Here's some of mine as well :)
/* -- Sloan Kiechel 2017-02-18 12:10 UTC-6
/*************************************************************************/
/****************************COMPARETO TESTS******************************/
/*************************************************************************/
   @Test public void pointCompareTest1() {
      Point a = new Point (0, 0);
      Point b = new Point (0, 1);
      assertEquals(-1, a.compareTo(b));
   }
   
   @Test public void pointCompareTest2() {
      Point a = new Point (1, 0);
      Point b = new Point (0, 0);
      assertEquals(1, a.compareTo(b));
   }
   
   @Test public void pointCompareTest3() {
      Point a = new Point (0, 0);
      Point b = new Point (1, 0);
      assertEquals(-1, a.compareTo(b));
   }
   
   @Test public void pointCompareTest4() {
      Point a = new Point (0, 1);
      Point b = new Point (0, 0);
      assertEquals(1, a.compareTo(b));
   }
   
   @Test public void pointCompareTest5() {
      Point a = new Point (0, 0);
      Point b = new Point (0, 0);
      assertEquals(0, a.compareTo(b));
   }
   
 /*************************************************************************/
/****************************COMPAREPOINTSBYSLOPE TESTS*******************/
/*************************************************************************/      
   @Test public void compareSlopeTest1() {
      Point a = new Point(0,0);
      Point b = new Point(5,5);
      Point c = new Point(4,3);
      int actual = a.slopeOrder.compare(b,c);
      assertEquals(1, actual);
   }
   
   @Test public void compareSlopeTest2() {
      Point a = new Point(0,0);
      Point b = new Point(5,5);
      Point c = new Point(4,3);
      int actual = a.slopeOrder.compare(c, b);
      assertEquals(-1, actual);
   }
   
   @Test public void compareSlopeTest3() {
      Point a = new Point(0,0);
      Point b = new Point(5,5);
      Point c = new Point(5,5);
      int actual = a.slopeOrder.compare(b, c);
      assertEquals(0, actual);
   }
   
   /* 
   *Those are all my tests for Point Class. Here are mine for Line class
   * Apologies if its alot, I don't believe there is such a thing as overtesting :P
   * -- Sloan Kiechel 2017-02-18 14:46 UTC-6
   /*
/*******************************************************/
/*********************ADD TESTS*************************/
/*******************************************************/

   //First test is an empty line
   @Test public void addTest1() {
      Point a = new Point(0,0);
      Line line = new Line();
      assertEquals(true, line.add(a)); //Checks if true/false works
      assertEquals("(0, 0)", line.toString()); //Checks if actually added
   }
   
   //Tests when it only has one item in line
   @Test public void addTest2() {
      Point a = new Point(0,0);
      Point b = new Point(1,1);
      Line line = new Line();
      line.add(a);
      assertEquals(true, line.add(b)); //Checks if true/false works
      assertEquals("(0, 0) -> (1, 1)", line.toString()); //Checks if actually added
   }
   
   //Normal test, more than one item, are collinear.
   @Test public void addTest3() {
      Point a = new Point(0,0);
      Point b = new Point(1,1);
      Point c = new Point(2,2);
      Line line = new Line();
      line.add(a);
      line.add(b);
      assertEquals(true, line.add(c)); //Checks if true/false works
      //Checks if actually added
      assertEquals("(0, 0) -> (1, 1) -> (2, 2)", line.toString()); 
   }
   
   //Tests if aren't collinear
   @Test public void addTest4() {
      Point a = new Point(0,0);
      Point b = new Point(1,1);
      Point c = new Point(2,3);
      Line line = new Line();
      line.add(a);
      line.add(b);
      assertEquals(false, line.add(c)); //Checks if true/false works
      //Checks to be sure it did not add
      assertEquals("(0, 0) -> (1, 1)", line.toString()); 
   }
   
   //Tests if already in line
   @Test public void addTest5() {
      Point a = new Point(0,0);
      Point b = new Point(1,1);
      Point c = new Point(1,1);
      Line line = new Line();
      line.add(a);
      line.add(b);
      assertEquals(false, line.add(c)); //Checks if true/false works
      //Checks to be sure it did not add
      assertEquals("(0, 0) -> (1, 1)", line.toString()); 
   }
   
   /*******************************************************/
/*********************FIRST TESTS*************************/
/*******************************************************/
   
   //Tests if null if empty
   @Test public void firstTest1() {
      Line line = new Line();
      assertEquals(null, line.first());
   }
   
   //Tests it pulls first
   @Test public void firstTest2() {
      Point a = new Point(0,0);
      Point b = new Point(1,1);
      Point c = new Point(2,2);
      Line line = new Line();
      line.add(a);
      line.add(b);
      line.add(c);
      assertEquals(a, line.first());
   }
   
/*******************************************************/
/*********************LAST TESTS*************************/
/*******************************************************/
   
   //Tests if null if empty
   @Test public void lastTest1() {
      Line line = new Line();
      assertEquals(null, line.last());
   }
   
   //Tests it pulls first
   @Test public void lastTest2() {
      Point a = new Point(0,0);
      Point b = new Point(1,1);
      Point c = new Point(2,2);
      Line line = new Line();
      line.add(a);
      line.add(b);
      line.add(c);
      assertEquals(c, line.last());
   }   
   
/*******************************************************/
/*********************LENGTH TESTS*************************/
/*******************************************************/ 

   //Tests if 0 if empty
   @Test public void lengthTest1() {
      Line line = new Line();
      assertEquals(0, line.length());
   }
   
   //Tests it pulls first
   @Test public void lengthTest2() {
      Point a = new Point(0,0);
      Point b = new Point(1,1);
      Point c = new Point(2,2);
      Line line = new Line();
      line.add(a);
      line.add(b);
      line.add(c);
      assertEquals(3, line.length());
   }  
   
   /*******************************************************/
/*********************COMPARETO TESTS*************************/
/*******************************************************/  

   //First < First
   @Test public void compareTest1() {
      Line line1 = new Line();
      Line line2 = new Line();
      Point a = new Point(0,0);
      Point b = new Point(1,1);
      Point c = new Point(2,2);
      Point d = new Point(3,3);
      line1.add(a);
      line1.add(b);
      line2.add(c);
      line2.add(d);
      assertEquals(-1, line1.compareTo(line2));
   }  
   
   //First > First
   @Test public void compareTest2() {
      Line line1 = new Line();
      Line line2 = new Line();
      Point a = new Point(0,0);
      Point b = new Point(1,1);
      Point c = new Point(2,2);
      Point d = new Point(3,3);
      line1.add(a);
      line1.add(b);
      line2.add(c);
      line2.add(d);
      assertEquals(1, line2.compareTo(line1));
   }  
   
   //First == First, Last < Last
   @Test public void compareTest3() {
      Line line1 = new Line();
      Line line2 = new Line();
      Point a = new Point(0,0);
      Point b = new Point(1,1);
      Point c = new Point(2,2);
      line1.add(a);
      line1.add(b);
      line2.add(a);
      line2.add(c);
      assertEquals(-1, line1.compareTo(line2));
   }  
   
   //First == First, Last > Last
   @Test public void compareTest4() {
      Line line1 = new Line();
      Line line2 = new Line();
      Point a = new Point(0,0);
      Point b = new Point(1,1);
      Point c = new Point(2,2);
      line1.add(a);
      line1.add(b);
      line2.add(a);
      line2.add(c);
      assertEquals(1, line2.compareTo(line1));
   }  
   
   //Equal to eachother
   @Test public void compareTest5() {
      Line line1 = new Line();
      Line line2 = new Line();
      Point a = new Point(0,0);
      line1.add(a);
      line2.add(a);
      assertEquals(0, line1.compareTo(line2));
   } 
   
   
   /* -- Tyler Robins 2017-02-20 
/*************************************************************************/
/****************************SlopeTo Tests******************************/
/*************************************************************************/
//Same Points
   @Test public void slopeTo1() {
      Point a = new Point(0,0);
      Point b = new Point(0,0);
      double infinity = Double.NEGATIVE_INFINITY;
      double actual = a.slopeTo(b);
      assertEquals(infinity, actual, 0.001);
   }
   
   //Horizontal Line
   @Test public void slopeTo2() {
      Point a = new Point(0,0);
      Point b = new Point(1,0);
      double actual = a.slopeTo(b);
      assertEquals(+0.0, actual, 0.001);
   }
   
   //Vertical Line
   @Test public void slopeTo3() {
      Point a = new Point(0,0);
      Point b = new Point(0,1);
      double infinity = Double.POSITIVE_INFINITY;
      double actual = a.slopeTo(b);
      assertEquals(infinity, actual, 0.001);
   }
   
   //Slope = 1
   @Test public void slopeTo4() {
      Point a = new Point(0,0);
      Point b = new Point(5,5);
      double actual = a.slopeTo(b);
      assertEquals(1, actual, 0.001);
   }
   
   //Slope = -1
   @Test public void slopeTo5() {
      Point b = new Point(0,5);
      Point a = new Point(5,0);
      double actual = a.slopeTo(b);
      assertEquals(-1, actual, 0.001);
   }
   
   //Building off Sloan's Tests
   //These are to account for the new empty line comparisons
   /*************************************************************************/
/****************************CompareTo Tests******************************/
/*************************************************************************/

  //Two Empty Lines
   @Test public void compareTest6() {
      Line line1 = new Line();
      Line line2 = new Line();
      assertEquals(0, line1.compareTo(line2));
   } 
   
   //First Line Empty
   @Test public void compareTest7() {
      Line line1 = new Line();
      Line line2 = new Line();
      Point a = new Point(0,0);
      line2.add(a);
      assertEquals(-1, line1.compareTo(line2));
   } 
   //Second Line Empty
   @Test public void compareTest8() {
      Line line1 = new Line();
      Line line2 = new Line();
      Point a = new Point(0,0);
      line1.add(a);
      assertEquals(1, line1.compareTo(line2));
   }
   
   
      /*************************************************************************/
/****************************EXTRACTOR Tests******************************/
/*************************************************************************/

   /*************************************************************************/
/****************************getLinesFast Tests******************************/
/*************************************************************************/

   // 2 lines no garbage
   @Test public void fastTest() {
      Point[] p = new Point[] {
         // 
         new Point(0, 1), 
         new Point(1, 2),
         new Point(2, 3),
         new Point(3, 4),
         //
         new Point(0, 0), 
         new Point(1, 1),
         new Point(2, 2),
         new Point(3, 3),
         };
      Extractor test = new Extractor(Arrays.asList(p));
      SortedSet<Line> fastLines = test.getLinesFast();
      
      int numLines = fastLines.size();
   
      Assert.assertEquals(2, numLines);
   }
   
   // 1 8-point collinear line
   @Test public void fastTest2() {
      Point[] p = new Point[] {
         //
         new Point(0, 0), 
         new Point(1, 1),
         new Point(2, 2),
         new Point(3, 3),
         new Point(4, 4), 
         new Point(5, 5),
         new Point(6, 6),
         new Point(7, 7),
         };
      Extractor test = new Extractor(Arrays.asList(p));
      SortedSet<Line> fastLines = test.getLinesFast();
      
      Line testL = new Line(Arrays.asList(p));
      
   
      Assert.assertEquals(testL, fastLines.first());
   }
   
   // all garbage
   @Test public void fastTest3() {
      Point[] p = new Point[] {
         // 
         new Point(0, 1), 
         new Point(2, 2),
         new Point(5, 3),
         new Point(8, 4),
         new Point(7, 0), 
         new Point(20, 1),
         new Point(18, 2),
         new Point(13, 3),
         };
      Extractor test = new Extractor(Arrays.asList(p));
      SortedSet<Line> fastLines = test.getLinesFast();
      
      int numLines = fastLines.size();
   
      Assert.assertEquals(0, numLines);
   }
   
    // empty
   @Test public void fastTest4() {
      Point[] p = new Point[0];
      Extractor test = new Extractor(Arrays.asList(p));
      SortedSet<Line> fastLines = test.getLinesFast();
      
      int numLines = fastLines.size();
   
      Assert.assertEquals(0, numLines);
   }
   
   // intersecting lines
   @Test public void fastTest5() {
      Point[] p = new Point[] {
         //
         new Point(0, 0), 
         new Point(1, 1),
         new Point(2, 2),
         new Point(3, 3),
         //
         new Point(0, 1), 
         new Point(1, 1),
         new Point(2, 1),
         new Point(3, 1),
         };
      Extractor test = new Extractor(Arrays.asList(p));
      SortedSet<Line> fastLines = test.getLinesFast();
      
      Line testL = new Line(Arrays.asList(p));
      
   
      Assert.assertEquals(testL, fastLines.first());
   }
   
      /*************************************************************************/
/****************************getLinesBrute Tests******************************/
/*************************************************************************/
      // 2 lines no garbage
   @Test public void bruteTest() {
      Point[] p = new Point[] {
         // 
         new Point(0, 1), 
         new Point(1, 2),
         new Point(2, 3),
         new Point(3, 4),
         //
         new Point(0, 0), 
         new Point(1, 1),
         new Point(2, 2),
         new Point(3, 3),
         };
      Extractor test = new Extractor(Arrays.asList(p));
      SortedSet<Line> bruteLines = test.getLinesBrute();
      
      int numLines = bruteLines.size();
   
      Assert.assertEquals(2, numLines);
   }
   

   // all garbage
   @Test public void bruteTest3() {
      Point[] p = new Point[] {
         // 
         new Point(0, 1), 
         new Point(2, 2),
         new Point(5, 3),
         new Point(8, 4),
         new Point(7, 0), 
         new Point(20, 1),
         new Point(18, 2),
         new Point(13, 3),
         };
      Extractor test = new Extractor(Arrays.asList(p));
      SortedSet<Line> bruteLines = test.getLinesBrute();
      
      int numLines = bruteLines.size();
   
      Assert.assertEquals(0, numLines);
   }
   
    // empty
   @Test public void bruteTest4() {
      Point[] p = new Point[0];
      Extractor test = new Extractor(Arrays.asList(p));
      SortedSet<Line> bruteLines = test.getLinesBrute();
      
      int numLines = bruteLines.size();
   
      Assert.assertEquals(0, numLines);
   }
   
   // intersecting lines
   @Test public void bruteTest5() {
      Point[] p = new Point[] {
         //
         new Point(0, 0), 
         new Point(1, 1),
         new Point(2, 2),
         new Point(3, 3),
         //
         new Point(0, 1), 
         new Point(1, 1),
         new Point(2, 1),
         new Point(3, 1),
         };
      Extractor test = new Extractor(Arrays.asList(p));
      SortedSet<Line> bruteLines = test.getLinesBrute();
      
      Line testL = new Line(Arrays.asList(p));
      
   
      //Assert.assertEquals(testL, bruteLines.first());
   } 
}
