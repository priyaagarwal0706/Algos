package collinear;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BruteCollinearPoints {
    
    private List<LineSegment> pointList = new ArrayList<>();
    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException("Input is null");
        }
        Point[] refPoints = points.clone();
        checkIfPointNull(refPoints);
        Arrays.sort(refPoints);
        checkForDuplicates(refPoints);
        for (int i = 0; i < refPoints.length-3; i++) {
            for (int j = i+1; j < refPoints.length-2; j++) {
                for (int k = j+1; k < refPoints.length-1; k++) {
                    if (refPoints[i].slopeTo(refPoints[j]) == refPoints[i].slopeTo(refPoints[k])) {
                        for (int d = k+1; d < refPoints.length; d++) {
                            if (refPoints[i].slopeTo(refPoints[j]) == refPoints[i].slopeTo(refPoints[d])) {
                                Point[] cPoints = {refPoints[i], refPoints[j], refPoints[k], refPoints[d]};
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
    }
    public int numberOfSegments() {
        return pointList.size();
    }
    public LineSegment[] segments() {
        return pointList.toArray(new LineSegment[pointList.size()]);
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
