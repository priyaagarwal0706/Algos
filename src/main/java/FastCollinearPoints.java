import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FastCollinearPoints {
    private LineSegment[] collinearLines;
    
    public FastCollinearPoints(Point[] points)   {
        if (points == null) {
            throw new IllegalArgumentException("Input is null");
        }
        checkIfPointNull(points);
        Arrays.sort(points);
        checkForDuplicates(points);
        Arrays.sort(points, points[0].slopeOrder());
        List<LineSegment> pointList = new ArrayList<>();
        Point refPoint = points[0];
        Point[] temp = new Point[points.length];
        temp[0] = refPoint;
        double lastSlope = refPoint.slopeTo(points[1]);
        int count = 0;
        for (int i = 1; i < points.length; i++) {
            Point newPoint = points[i];
            double slope = refPoint.slopeTo(newPoint);
            if (slope == lastSlope) {
               count++;
               temp[count] = newPoint;
           } else {
               if (count >= 3) {
                   pointList.add(new LineSegment(refPoint, points[i-1]));
               }
               count = 1;
               temp[1] = newPoint;
           }
           lastSlope = slope;
        }
        if (count >= 3) {
            pointList.add(new LineSegment(refPoint, temp[count]));
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
}
