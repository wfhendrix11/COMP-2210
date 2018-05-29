import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
//import java.util.Iterator;
//import java.util.List;
import java.util.NoSuchElementException;

/**
 * Defines a library of selection methods on Collections.
 *
 * @author  Your Name (you@auburn.edu)
 * @author  Dean Hendrix (dh@auburn.edu)
 * @version 2017-01-30
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
    * Returns the minimum value in the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty, this method throws a
    * NoSuchElementException. This method will not change coll in any way.
    *
    * @param coll    the Collection from which the minimum is selected
    * @param comp    the Comparator that defines the total order on T
    * @return        the minimum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T min(Collection<T> coll, Comparator<T> comp) {
      
      // coll or comp null
      if (coll == null || comp == null) { 
         throw new IllegalArgumentException(); 
      } 
      
      // if coll empty 
      if (coll.size() == 0) { 
         throw new NoSuchElementException(); 
      } 
      
      // find min value of coll
      T min = coll.iterator().next();
      for (T i : coll) {
         if (comp.compare(i, min) < 0) { 
            min = i;
         }   
      } 
      return min;
   }


   /**
    * Selects the maximum value in the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty, this method throws a
    * NoSuchElementException. This method will not change coll in any way.
    *
    * @param coll    the Collection from which the maximum is selected
    * @param comp    the Comparator that defines the total order on T
    * @return        the maximum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T max(Collection<T> coll, Comparator<T> comp) {
      
      // coll or comp null
      if (coll == null || comp == null) { 
         throw new IllegalArgumentException(); 
      } 
      
      // if coll empty 
      if (coll.size() == 0) { 
         throw new NoSuchElementException(); 
      } 
      
      // find min value of coll
      T max = coll.iterator().next();
      for (T i : coll) {
         if (comp.compare(i, max) > 0) { 
            max = i;
         }   
      } 
      return max;
   }


   /**
    * Selects the kth minimum value from the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty or if there is no kth minimum
    * value, this method throws a NoSuchElementException. This method will not
    * change coll in any way.
    *
    * @param coll    the Collection from which the kth minimum is selected
    * @param k       the k-selection value
    * @param comp    the Comparator that defines the total order on T
    * @return        the kth minimum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T kmin(Collection<T> coll, int k, Comparator<T> comp) {
      
      // coll or comp null
      if (coll == null || comp == null) { 
         throw new IllegalArgumentException(); 
      } 
      
      // if coll empty 
      if (coll.size() == 0) { 
         throw new NoSuchElementException(); 
      }
      
      // if k is negative or greater than size 
      if (k < 1 || k > coll.size()) { 
         throw new NoSuchElementException(); 
      }
      
      // find kmin
      ArrayList<T> copy = new ArrayList<T>(coll);
      java.util.Collections.sort(copy, comp);

      return copy.get(k - 1);
   }


   /**
    * Selects the kth maximum value from the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty or if there is no kth maximum
    * value, this method throws a NoSuchElementException. This method will not
    * change coll in any way.
    *
    * @param coll    the Collection from which the kth maximum is selected
    * @param k       the k-selection value
    * @param comp    the Comparator that defines the total order on T
    * @return        the kth maximum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T kmax(Collection<T> coll, int k, Comparator<T> comp) {
      
      // coll or comp null
      if (coll == null || comp == null) { 
         throw new IllegalArgumentException(); 
      } 
      
      // if coll empty 
      if (coll.size() == 0) { 
         throw new NoSuchElementException(); 
      }
      
      // if k is negative or greater than size 
      if (k < 1 || k > coll.size()) { 
         throw new NoSuchElementException(); 
      }
      
      // find kmax
      ArrayList<T> copy = new ArrayList<T>(coll);
      java.util.Collections.sort(copy, comp);

      return copy.get(copy.size() - k);
   }


   /**
    * Returns a new Collection containing all the values in the Collection coll
    * that are greater than or equal to low and less than or equal to high, as
    * defined by the Comparator comp. The returned collection must contain only
    * these values and no others. The values low and high themselves do not have
    * to be in coll. Any duplicate values that are in coll must also be in the
    * returned Collection. If no values in coll fall into the specified range or
    * if coll is empty, this method throws a NoSuchElementException. If either
    * coll or comp is null, this method throws an IllegalArgumentException. This
    * method will not change coll in any way.
    *
    * @param coll    the Collection from which the range values are selected
    * @param low     the lower bound of the range
    * @param high    the upper bound of the range
    * @param comp    the Comparator that defines the total order on T
    * @return        a Collection of values between low and high
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> Collection<T> range(Collection<T> coll, T low, T high,
                                         Comparator<T> comp) {
      // coll or comp null
      if (coll == null || comp == null) { 
         throw new IllegalArgumentException(); 
      } 
      
      // if coll empty 
      if (coll.size() == 0) { 
         throw new NoSuchElementException(); 
      }
      
      // low less than high
      if (comp.compare(low, high) >= 0) { 
         throw new NoSuchElementException();
      }
      
      // find range
      ArrayList<T> rangeArray = new ArrayList<T>(0);
      ArrayList<T> copy = new ArrayList<T>(coll);
      
      for (T i : copy) { 
         if (comp.compare(i, low) >= 0 && comp.compare(i, high) <= 0) { 
            rangeArray.add(i); 
         }
      }
      
      // if there is no range 
      if (rangeArray.size() == 0) { 
         throw new NoSuchElementException();
      }  
      
      return rangeArray;
   }


   /**
    * Returns the smallest value in the Collection coll that is greater than
    * or equal to key, as defined by the Comparator comp. The value of key
    * does not have to be in coll. If coll or comp is null, this method throws
    * an IllegalArgumentException. If coll is empty or if there is no
    * qualifying value, this method throws a NoSuchElementException. This
    * method will not change coll in any way.
    *
    * @param coll    the Collection from which the ceiling value is selected
    * @param key     the reference value
    * @param comp    the Comparator that defines the total order on T
    * @return        the ceiling value of key in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T ceiling(Collection<T> coll, T key, Comparator<T> comp) {
      
      // coll or comp null
      if (coll == null || comp == null) { 
         throw new IllegalArgumentException(); 
      } 
      
      // if coll empty 
      if (coll.size() == 0) { 
         throw new NoSuchElementException(); 
      }
      
      // find the range
      T low = key; 
      T high = max(coll, comp);
      Collection<T> ceilingColl = range(coll, low, high, comp);
      
      if (ceilingColl.isEmpty()) {
         throw new NoSuchElementException();
      }
      
      // find the min of the range
      T ceiling = min(ceilingColl, comp);
      
      return ceiling;
   
   }


   /**
    * Returns the largest value in the Collection coll that is less than
    * or equal to key, as defined by the Comparator comp. The value of key
    * does not have to be in coll. If coll or comp is null, this method throws
    * an IllegalArgumentException. If coll is empty or if there is no
    * qualifying value, this method throws a NoSuchElementException. This
    * method will not change coll in any way.
    *
    * @param coll    the Collection from which the floor value is selected
    * @param key     the reference value
    * @param comp    the Comparator that defines the total order on T
    * @return        the floor value of key in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T floor(Collection<T> coll, T key, Comparator<T> comp) {
      
      // coll or comp null
      if (coll == null || comp == null) { 
         throw new IllegalArgumentException(); 
      } 
      
      // if coll empty 
      if (coll.size() == 0) { 
         throw new NoSuchElementException(); 
      }
      
      // find the range 
      T low = min(coll, comp);
      T high = key;
      Collection<T> floorColl = range(coll, low, high, comp);
      
      if (floorColl.isEmpty()) {
         throw new NoSuchElementException();
      }
      
      // find the max
      T floor = max(floorColl, comp);
     
      return floor;
   }

}
