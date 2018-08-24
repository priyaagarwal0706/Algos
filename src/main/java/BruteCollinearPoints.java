import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BruteCollinearPoints {
    
    private final LineSegment[] collinearLines;
    
    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException("Input is null");
        }
        checkIfPointNull(points);
        Arrays.sort(points);
        checkForDuplicates(points);
        
        List<LineSegment> pointList = new ArrayList<>();
        for (int i = 0; i < points.length-3; i++) {
            for (int j = i+1; j < points.length-2; j++) {
                for (int k = j+1; k < points.length-1; k++) {
                    if (points[i].slopeTo(points[j]) == points[i].slopeTo(points[k])) {
                        for (int d = k+1; d < points.length; d++) {
                            if (points[i].slopeTo(points[j]) == points[i].slopeTo(points[d])) {
                                Point[] cPoints = {points[i], points[j], points[k], points[d]};
                                Arrays.sort(cPoints);
                                LineSegment collinearLine = new LineSegment(cPoints[0], cPoints[3]);
                                if (!pointList.contains(collinearLine)) {
                                    pointList.add(collinearLine);
                                }
                               

                            }
                            
                            
                        }
                    }
                    
                    
                }
            }
        }
        collinearLines = pointList.toArray(new LineSegment[pointList.size()]);
    }
    public int numberOfSegments() {
        return collinearLines.length;
    }
    public LineSegment[] segments() {
        return collinearLines.clone();
    }
    private static void checkForDuplicates(Point[] points) {
        for (int i = 0; i < points.length-1; i++) {
            if (points[i].compareTo(points[i+1]) == 0) 
                throw new IllegalArgumentException("Duplicate point present in given array");
        }
    }
    private void checkIfPointNull(Point[] points) {
        for (Point p : points) {
            if (p == null) 
                throw new IllegalArgumentException("Point is null in given array");
        }
    }
}
