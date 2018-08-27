import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import edu.princeton.cs.algs4.In;

public class FastCollinearPoints {
   
    private List<List<Point>> tempLs = new ArrayList<>();
    private List<LineSegment> colinearSegments = new ArrayList<LineSegment>();
    public FastCollinearPoints(Point[] points)   {
        Point[] tempPoints = points;
        if (points == null) {
            throw new IllegalArgumentException("Input is null");
        }
        checkIfPointNull(points);
        Arrays.sort(tempPoints);
        checkForDuplicates(tempPoints);
        for (Point refPoint : points) {
           
            Arrays.sort(tempPoints, refPoint.slopeOrder());
            List<Point> temp = new ArrayList<>();
            double slope = 0;
            double lastSlope = Double.NEGATIVE_INFINITY;
             for (int i = 0; i < tempPoints.length; i++) {
                 Point newPoint = tempPoints[i];
                 slope = refPoint.slopeTo(newPoint);
                 if (slope == lastSlope) {
                    temp.add(newPoint);
                  } else {
                    if (temp.size() >= 3) {
                      temp.add(refPoint);
                      Collections.sort(temp);
                      addIfNewPoints(temp.get(0), temp.get(temp.size()-1));
                    }
                    temp.clear();
                    temp.add(newPoint);
                    lastSlope = slope;
                }
             }
             if (temp.size() >= 3) {
                 temp.add(refPoint);
                 Collections.sort(temp);
                 addIfNewPoints(temp.get(0), temp.get(temp.size()-1));
             }
        }
    }
    
     private void addIfNewPoints(Point startPoint, Point endPoint) {
       List<Point> arr = new ArrayList<>();
       arr.add(startPoint);
       arr.add(endPoint);
       if (!tempLs.contains(arr)) {
          tempLs.add(arr);
          colinearSegments.add(new LineSegment(startPoint, endPoint));
       }
       
    }
     
    public  int numberOfSegments() {
        return colinearSegments.size();
    }
    public LineSegment[] segments() {
        return colinearSegments.toArray(new LineSegment[colinearSegments.size()]);
    }
    
    private void checkIfPointNull(Point[] points) {
        for (Point p : points) {
            if (p == null) 
                throw new IllegalArgumentException("Point is null in given array");
        }
    }
    private static void checkForDuplicates(Point[] points) {
        for (int i = 0; i < points.length-1; i++) {
            if (points[i].compareTo(points[i+1]) == 0) 
                throw new IllegalArgumentException("Duplicate point present in given array");
        }
    }
    public static void main(String[] args) {
        try {
          int[] inputPoints = new In("/Users/priya730563/Downloads/collinear/input40.txt").readAllInts();
          int arraySize = 1;
          Point[] points = new Point [inputPoints[0]];
          for(int i=0;i<points.length;i++) {
              points[i] = new Point(inputPoints[arraySize],inputPoints[arraySize+1]);
              arraySize = arraySize+2;
          }
        FastCollinearPoints collinaerPoints = new FastCollinearPoints(points);
          for(LineSegment p :collinaerPoints.segments()) {
              System.out.println(p.toString());
          }
          System.out.println(collinaerPoints.numberOfSegments());
        }catch(Exception e) {
            e.printStackTrace();
        }
      }
}
