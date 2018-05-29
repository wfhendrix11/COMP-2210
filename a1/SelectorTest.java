import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class SelectorTest {
   
   // test fixtures 
   int[] a = {2};                         // length 1
   int[] b = {2, 4};                      // length 2 
   int[] c = {2, 2};                      // length 2 with duplicates
   int[] d = {10, 4, 6, 8, 15, 3};        // typical length without duplicates
   int[] e = {2, 4, 6, 8, 10, 2, 8};      // typical length with duplicates
   int[] f = {0};                         // array zero
   int[] g = null;                        // null array
   int[] h = {};                          // empty array
   int[] i = {2, 4, Integer.MAX_VALUE};   // max size int
   int[] j = {2, 4, Integer.MIN_VALUE};   // min size int
   
   int expected, actual;                                
   
   //-------------------------------Selector.min-Tests------------------------
   
   // typical cases
   @Test public void minTypicalTests() {
   
      expected = 3;
      actual = Selector.min(d);
      assertEquals(expected, actual);
   
      expected = 2;
      actual = Selector.min(b);
      assertEquals(expected, actual);      
   }
      
   // illegal cases
   @Test public void minIllegalTests() {   
  
      // test for null 
      try {
         Selector.min(g);
      }
      catch (IllegalArgumentException e) {
         Assert.assertTrue(true);
      }
      catch (Exception e) {
         Assert.fail("Threw incorrect exception.");
      }
      
      // test for empty
      try {
         Selector.min(h);
      }
      catch (IllegalArgumentException e) {
         Assert.assertTrue(true);
      }
      catch (Exception e) {
         Assert.fail("Threw incorrect exception.");
      }
   } 
   
   //boundary cases
   @Test public void minBoundaryTests() { 
      
      // length 1
      expected = 2;
      actual = Selector.min(a);
      assertEquals(expected, actual);
      
      // length 2
      expected = 2;
      actual = Selector.min(b);
      assertEquals(expected, actual);
      
      // smallest possible int 
      expected = Integer.MIN_VALUE;
      actual = Selector.min(j);
      assertEquals(expected, actual);
   }
   
   // special cases 
   @Test public void minSpecialTests() { 
      
      // duplicates length 2 
      expected = 2;
      actual = Selector.min(c);
      assertEquals(expected, actual);
      
      // duplicates normal length
      expected = 2;
      actual = Selector.min(e);
      assertEquals(expected, actual);
  }
  
  //-------------------------------Selector.max-Tests------------------------
  
   // typical cases
   @Test public void maxTypicalTests() {
   
      expected = 15;
      actual = Selector.max(d);
      assertEquals(expected, actual);
   
      expected = 4;
      actual = Selector.max(b);
      assertEquals(expected, actual);      
   }
   
   // illegal cases
   @Test public void maxIllegalTests() {   
  
      // test for null 
      try {
         Selector.max(g);
      }
      catch (IllegalArgumentException e) {
         Assert.assertTrue(true);
      }
      catch (Exception e) {
         Assert.fail("Threw incorrect exception.");
      }
      
      // test for empty
      try {
         Selector.max(h);
      }
      catch (IllegalArgumentException e) {
         Assert.assertTrue(true);
      }
      catch (Exception e) {
         Assert.fail("Threw incorrect exception.");
      }
   }

   //boundary cases
   @Test public void maxBoundaryTests() { 
      
      // length 1
      expected = 2;
      actual = Selector.max(a);
      assertEquals(expected, actual);
      
      // length 2
      expected = 4;
      actual = Selector.max(b);
      assertEquals(expected, actual);
      
      // smallest possible int 
      expected = Integer.MAX_VALUE;
      actual = Selector.max(i);
      assertEquals(expected, actual);
   }
   
    // special cases 
   @Test public void maxSpecialTests() { 
      
      // duplicates length 2 
      expected = 2;
      actual = Selector.max(c);
      assertEquals(expected, actual);
      
      // duplicates normal length
      expected = 10;
      actual = Selector.max(e);
      assertEquals(expected, actual);
  }
  
  //-------------------------------Selector.kmin-Tests-----------------------
  
   // typical cases
   @Test public void kminTypicalTests() {
   
      expected = 6;
      actual = Selector.kmin(d, 2);
      assertEquals(expected, actual);
   }
   
   // illegal cases
   @Test public void kminIllegalTests() {   
  
      // test for null 
      try {
         Selector.kmin(g, 2);
      }
      catch (IllegalArgumentException e) {
         Assert.assertTrue(true);
      }
      catch (Exception e) {
         Assert.fail("Threw incorrect exception.");
      }
      
      // test for empty
      try {
         Selector.kmin(h, 2);
      }
      catch (IllegalArgumentException e) {
         Assert.assertTrue(true);
      }
      catch (Exception e) {
         Assert.fail("Threw incorrect exception.");
      }
      
      // test for if k is larger than number distinct values
      try {
         Selector.kmin(e, 6);
      }
      catch (IllegalArgumentException e) {
         Assert.assertTrue(true);
      }
      catch (Exception e) {
         Assert.fail("Threw incorrect exception.");
      }
   }
      
   //boundary cases
   @Test public void kminBoundaryTests() { 
      
      // length 1
      expected = 2;
      actual = Selector.kmin(a, 1);
      assertEquals(expected, actual);
      
      // length 2
      expected = 2;
      actual = Selector.kmin(b, 1);
      assertEquals(expected, actual);
      
      // smallest possible int 
      expected = 4;
      actual = Selector.kmin(j, 2);
      assertEquals(expected, actual);
   }
   
   // special cases 
   @Test public void kminSpecialTests() { 
      
      // duplicates length 2 
      expected = 2;
      actual = Selector.kmin(c, 1);
      assertEquals(expected, actual);
      
      // duplicates normal length
      expected = 6;
      actual = Selector.kmin(e, 2);
      assertEquals(expected, actual);
  }
}

