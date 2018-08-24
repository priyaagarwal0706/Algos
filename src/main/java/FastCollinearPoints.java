import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.princeton.cs.algs4.In;

public class FastCollinearPoints {
    private final LineSegment[] collinearLines;
    
    public FastCollinearPoints(Point[] points)   {
        if (points == null) {
            throw new IllegalArgumentException("Input is null");
        }
        checkIfPointNull(points);
        Arrays.sort(points);
        checkForDuplicates(points);
        Arrays.sort(points, points[0].slopeOrder());
        System.out.println("-----------------------");
        for(Point p :points) {
            System.out.println(p);
        }
        List<LineSegment> pointList = new ArrayList<>();
        Point refPoint = points[0];
        int count = 1;
        Point[] temp = new Point[points.length];
        temp[0] = refPoint;
        temp[1] = points[1];
        double lastSlope = refPoint.slopeTo(points[1]);
        for (int i = 2; i < points.length; i++) {
           if (refPoint.slopeTo(points[i]) == lastSlope) {
               count++;
               temp[count] = points[i];
           } else {
               if (count >= 3) {
                   pointList.add(new LineSegment(refPoint, points[i-1]));
               }
               lastSlope = refPoint.slopeTo(points[i]);
               count = 1;
               temp = null;
               temp = new Point[points.length];
               temp[0] = refPoint;
               temp[1] = points[i];
           }
        }
        if (count >= 3) {
            pointList.add(new LineSegment(refPoint,temp[count]));
        }
        collinearLines = pointList.toArray(new LineSegment[pointList.size()]);
    }
    public  int numberOfSegments() {
        return collinearLines.length;
    }
    public LineSegment[] segments() {
        return collinearLines.clone();
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
          int[] inputPoints = new In("/Users/priya730563/Downloads/collinear/vertical5.txt").readAllInts();
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
