package collinear;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class FastCollinearPoints {
   
    private List<LineSegment> colinearSegments = new ArrayList<>();
    public FastCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException("Input is null");
        }
        Point[] tempPoints = Arrays.copyOf(points, points.length);
       
        checkIfPointNull(points);
        for (Point refPoint : points) {
           Arrays.sort(tempPoints, refPoint.slopeOrder());
            double slope = 0;
            double lastSlope = Double.NEGATIVE_INFINITY;
            Point endPoint = refPoint;
            Point startPoint = refPoint;
            int noOfPoints = 0;
            for (int i = 1; i < tempPoints.length; i++) {
                 Point newPoint = tempPoints[i];
                 slope = refPoint.slopeTo(newPoint);
                 if (slope == Double.NEGATIVE_INFINITY) {
                     throw new IllegalArgumentException("Duplicate point present in given array");
                 }
                 if (slope == lastSlope) {
                     if (slope == newPoint.slopeTo(tempPoints[i-1])) {
                        if (tempPoints[i-1].compareTo(startPoint) <= 0) 
                            startPoint = tempPoints[i-1];
                        
                        if (tempPoints[i-1].compareTo(endPoint) >= 0)
                            endPoint = tempPoints[i-1];
                     } 
                     if (newPoint.compareTo(endPoint) >= 0) {
                         endPoint = newPoint;
                     }
                     if (newPoint.compareTo(startPoint) <= 0) {
                         startPoint = newPoint;
 
                     }
                     noOfPoints++;
                  } else {
                    
                    if (noOfPoints >= 3 && startPoint.compareTo(refPoint) >= 0) {
                        colinearSegments.add(new LineSegment(startPoint, endPoint));
                    }
                    startPoint = refPoint;
                    endPoint = refPoint;
                    noOfPoints = 1;
                    lastSlope = slope;
                  
                }
             }
             if (noOfPoints >= 3 && startPoint.compareTo(refPoint) >= 0) {
                colinearSegments.add(new LineSegment(startPoint, endPoint));
             }
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
}
