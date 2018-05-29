import java.util.Arrays;

/**
* Defines a library of selection methods
* on arrays of ints.
*
* @author   William Hendrix (wfh0008@auburn.edu)
* @author   Dean Hendrix (dh@auburn.edu)
* @version  2017-01-19
*
*/
public final class Selector {

   /**
    * Can't instantiate this class.
    *
    * D O   N O T   C H A N G E   T H I S   C O N S T R U C T O R
    *
    */
   private Selector() { }


   /**
    * Selects the minimum value from the array a. This method
    * throws IllegalArgumentException if a is null or has zero
    * length. The array a is not changed by this method.
    * 
    * @param a for array. 
    * @return min.
    */
   public static int min(int[] a) {
      
      // array is null
      if (a == null) { 
         throw new IllegalArgumentException(); 
      } 
      
      // array has no elements
      if (a.length == 0) { 
         throw new IllegalArgumentException(); 
      } 
      
      // array has one element 
      if (a.length == 1) { 
         return a[0];
      }
      
      // find the minimum value of the array
      int min = a[1];
      for (int i = 0; i < a.length; i++) { 
         if (a[i] < min) { 
            min = a[i];
         }
      }
      return min;
   }


   /**
    * Selects the maximum value from the array a. This method
    * throws IllegalArgumentException if a is null or has zero
    * length. The array a is not changed by this method.
    *
    * @param a for array. 
    * @return max
    */
   public static int max(int[] a) {
      
      // array is null
      if (a == null) { 
         throw new IllegalArgumentException(); 
      }
      
      // array has no elements
      if (a.length == 0) { 
         throw new IllegalArgumentException(); 
      }  
      
      // array has one element 
      if (a.length == 1) { 
         return a[0];
      }
      
      //find the minimum value of the array
      int max = a[1];
      for (int i = 0; i < a.length; i++) { 
         if (a[i] > max) { 
            max = a[i];
         }
      }
      return max;
   }


   /**
    * Selects the kth minimum value from the array a. This method
    * throws IllegalArgumentException if a is null, has zero length,
    * or if there is no kth minimum value. Note that there is no kth
    * minimum value if k < 1, k > a.length, or if k is larger than
    * the number of distinct values in the array. The array a is not
    * changed by this method.
    *
    * @param a for array.
    * @param k for k value.
    * @return kmin
    */
   public static int kmin(int[] a, int k) {
      
      // array is null
      if (a == null) { 
         throw new IllegalArgumentException(); 
      }
      
      // array has no elements
      if (a.length == 0) { 
         throw new IllegalArgumentException(); 
      }
      
      // if k is less than zero, or greater than length 
      if (k < 0 || k > a.length) { 
         throw new IllegalArgumentException(); 
      } 
      
      // remove duplicates
      int[] b = {a[0]};
      for (int i = 0; i < a.length; i++) { 
         if (!contains(b, a[i])) { 
            b = add(b, a[i]);
         }
      }
      
      // if k is larger than number of distinct values
      int x = b.length; 
      if (k > b.length) { 
         throw new IllegalArgumentException(); 
      }
 
      // sort array
      Arrays.sort(b);
      
      //return kmin
      return b[k - 1];
   }


   /**
    * Selects the kth maximum value from the array a. This method
    * throws IllegalArgumentException if a is null, has zero length,
    * or if there is no kth maximum value. Note that there is no kth
    * maximum value if k < 1, k > a.length, or if k is larger than
    * the number of distinct values in the array. The array a is not
    * changed by this method.
    *
    * @param a for array.
    * @param k for k value. 
    * @return kmax.
    */
   public static int kmax(int[] a, int k) {
      
      // array is null
      if (a == null) { 
         throw new IllegalArgumentException(); 
      }
      
      // array has no elements
      if (a.length == 0) { 
         throw new IllegalArgumentException(); 
      }
      
      // if k is less than zero, or greater than length 
      if (k < 0 || k > a.length) { 
         throw new IllegalArgumentException(); 
      } 
      
      // remove duplicates
      int[] b = {a[0]};
      for (int i = 0; i < a.length; i++) { 
         if (!contains(b, a[i])) { 
            b = add(b, a[i]);
         }
      }
      
      // if k is larger than number of distinct values
      int x = b.length; 
      if (k > b.length) { 
         throw new IllegalArgumentException(); 
      }
 
      // sort array largest to smallest
      Arrays.sort(b);
      
      //return kmax
      return b[b.length - k];
   }


   /**
    * Returns an array containing all the values in a in the
    * range [low..high]; that is, all the values that are greater
    * than or equal to low and less than or equal to high,
    * including duplicate values. The length of the returned array
    * is the same as the number of values in the range [low..high].
    * If there are no qualifying values, this method returns a
    * zero-length array. Note that low and high do not have
    * to be actual values in a. This method throws an
    * IllegalArgumentException if a is null or has zero length.
    * The array a is not changed by this method.
    *
    * @param a for array.
    * @param low for lowest value. 
    * @param high for highest value.   
    * @return range.
    */
   public static int[] range(int[] a, int low, int high) {
      
      // array is null
      if (a == null) { 
         throw new IllegalArgumentException(); 
      }
      
      // array has no elements
      if (a.length == 0) { 
         throw new IllegalArgumentException(); 
      }
      
      // find range 
      int[] b = new int[0];
      
      for (int i = 0; i < a.length; i++) { 
         if (a[i] >= low && a[i] <= high) {
            b = add(b, a[i]);
         }
      }
      
      // returns empty if no qualifying values 
      return b;
   }


   /**
    * Returns the smallest value in a that is greater than or equal to
    * the given key. This method throws an IllegalArgumentException if
    * a is null or has zero length, or if there is no qualifying
    * value. Note that key does not have to be an actual value in a.
    * The array a is not changed by this method.
    *
    * @param a for array. 
    * @param key for key value. 
    * @return ceiling.
    */
   public static int ceiling(int[] a, int key) {
   
      // array is null
      if (a == null) { 
         throw new IllegalArgumentException(); 
      }
      
      // array has no elements
      if (a.length == 0) { 
         throw new IllegalArgumentException(); 
      }
      
      // find ceiling
      int[] b = new int[0];
      
      for (int i = 0; i < a.length; i++) { 
         if (a[i] >= key) {
            b = add(b, a[i]);
         }
      }
      
      return min(b);
   }


   /**
    * Returns the largest value in a that is less than or equal to
    * the given key. This method throws an IllegalArgumentException if
    * a is null or has zero length, or if there is no qualifying
    * value. Note that key does not have to be an actual value in a.
    * The array a is not changed by this method.
    *
    * @param a for array. 
    * @param key for key value. 
    * @return floor. 
    */
   public static int floor(int[] a, int key) {
      
      // array is null
      if (a == null) { 
         throw new IllegalArgumentException(); 
      }
      
      // array has no elements
      if (a.length == 0) { 
         throw new IllegalArgumentException(); 
      }
      
      // find ceiling
      int[] b = new int[0];
      
      for (int i = 0; i < a.length; i++) { 
         if (a[i] <= key) {
            b = add(b, a[i]);
         }
      }
      
      return max(b);
   }
   
   //-------------------------------Helper-Methods------------------------
   
   // returns if a contains c
   private static boolean contains(int[] a, int c) { 
      for (int i = 0; i < a.length; i++) { 
         if (a[i] == c) { 
            return true; 
         } 
      }
      return false; 
   }
   
   // adds value to an array
   private static int[] add(int[] a, int e) {
      a  = Arrays.copyOf(a, a.length + 1);
      a[a.length - 1] = e;
      return a;
   }
}

