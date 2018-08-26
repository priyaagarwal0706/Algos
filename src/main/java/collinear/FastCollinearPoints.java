package collinear;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FastCollinearPoints {
    private LineSegment[] collinearLines;
    private List<List<Point>> tempLs = new ArrayList<>();

    public FastCollinearPoints(Point[] points)   {
        Point[] tempPoints = points;
        if (points == null) {
            throw new IllegalArgumentException("Input is null");
        }
        checkIfPointNull(points);
        Arrays.sort(points);
        checkForDuplicates(tempPoints);
        for (Point refPoint : points) {
            Arrays.sort(tempPoints);
            Arrays.sort(tempPoints, refPoint.slopeOrder());
            List<Point> temp = new ArrayList<>();
            double slope = 0;
            double lastSlope = Double.NEGATIVE_INFINITY;
             for (int i = 1; i < tempPoints.length; i++) {
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
        collinearLines = new LineSegment[tempLs.size()];
        for (int j = 0; j < tempLs.size(); j++) {
           collinearLines[j] = new LineSegment(tempLs.get(j).get(0),tempLs.get(j).get(1));
        }
        
    }
    
     private void addIfNewPoints(Point startPoint, Point endPoint) {
       List<Point> arr = new ArrayList<>();
       arr.add(startPoint);
       arr.add(endPoint);
       if (!tempLs.contains(arr)) {
          tempLs.add(arr);
        }
    
    
    }
     
    public  int numberOfSegments() {
        return collinearLines.length;
    }
    public LineSegment[] segments() {
        return collinearLines;
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

}
