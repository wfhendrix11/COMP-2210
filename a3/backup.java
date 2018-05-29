import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.util.Comparator;

/**
 * Extractor.java. Implements feature extraction for collinear points in
 * two dimensional data.
 *
 * @author  Dean Hendrix (dh@auburn.edu)
 * @version 2017-02-08
 *
 */
public class Extractor {
   
   /** raw data: all (x,y) points from source data. */
   private Point[] points;
   
   /** lines identified from raw data. */
   private SortedSet<Line> lines;
  
   /**
    * Builds an extractor based on the points in the file named by filename. 
    */
   public Extractor(String filename) throws IOException {
      Scanner scan = new Scanner(new File(filename));
      int lineNum = Integer.parseInt(scan.next());
      Point[] array = new Point[lineNum];
      
      for (int i = 0; i < lineNum; i++) {
         int x = Integer.parseInt(scan.next());
         int y = Integer.parseInt(scan.next());
         Point p = new Point(x, y);
         array[i] = p;
      }
      points = array;
   } 
  
   /**
    * Builds an extractor based on the points in the Collection named by pcoll. 
    *
    * THIS METHOD IS PROVIDED FOR YOU AND MUST NOT BE CHANGED.
    */
   public Extractor(Collection<Point> pcoll) {
      points = pcoll.toArray(new Point[]{});
   }
  
   /**
    * Returns a sorted set of all line segments of exactly four collinear
    * points. Uses a brute-force combinatorial strategy. Returns an empty set
    * if there are no qualifying line segments.
    */
   public SortedSet<Line> getLinesBrute() {
    lines = new TreeSet<Line>();
    
    // compare all points to eachother to find slope  
    for (int a = 0; a < points.length; a++) {
       for (int b = a + 1; b < points.length; b++) { 
          for (int c = b + 1; c < points.length; c++) { 
             for (int d = c + 1; d < points.length; d++) {
                double slope1 = points[a].slopeTo(points[b]);
                double slope2 = points[a].slopeTo(points[c]);
                double slope3 = points[a].slopeTo(points[d]); 
                    
                    // if points are collinear add them to the line
                    if (slope1 == slope2 && slope2 == slope3 && slope1 == slope3) {
                       Line line = new Line();
                       line.add(points[a]);
                       line.add(points[b]);
                       line.add(points[c]);
                       line.add(points[d]);
                       lines.add(line);
                    }
                 }
              }
           }
        } 
      return lines;
   }
  
   /**
    * Returns a sorted set of all line segments of at least four collinear
    * points. The line segments are maximal; that is, no sub-segments are
    * identified separately. A sort-and-scan strategy is used. Returns an empty
    * set if there are no qualifying line segments.
    */
   public SortedSet<Line> getLinesFast() {
      lines = new TreeSet<Line>();
      Point[] sortedPoints = points;
      double[] slopes = new double[sortedPoints.length];
      
      // sort the array by slope between p and N
      Arrays.sort(sortedPoints, points[0].slopeOrder);
      
      for (int p = 0; p < sortedPoints.length; p++) { 
          // find points with the same slope 
          for (int i = 0; i < sortedPoints.length - 1; i++) {
             Line line = new Line();
             Double slope1 = sortedPoints[p].slopeTo(sortedPoints[i]);
             Double slope2 = sortedPoints[p].slopeTo(sortedPoints[i + 1]);
             
             for (int j = i; j < sortedPoints.length && slope1.equals(slope2); j++) {  
                i++;
                if (slope1.equals(slope2) && j < sortedPoints.length - 1) {
                    line.add(sortedPoints[j]);
                    line.add(sortedPoints[j + 1]);
                }
                if (j < sortedPoints.length - 2) { 
                    slope1 = sortedPoints[p].slopeTo(sortedPoints[j + 1]);
                    slope2 = sortedPoints[p].slopeTo(sortedPoints[j + 2]);
                }
             }
             
             // add the line if it is larger than 3 points   
             if (line.length() > 3) {
                line.add(sortedPoints[p]); 
                lines.add(line);
             }
          }
      }
      return lines;
  }
   
}
