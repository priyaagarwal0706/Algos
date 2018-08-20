package collinear;

public class BruteCollinearPoints {
    
    private int count;
    private LineSegment[] collinearLines;
    //The method segments() should include each line segment containing 4 
    //points exactly once. If 4 points appear on a line segment in the order p→q→r→s, 
    //then you should include either the line segment p→s or s→p (but not both) 
    //and you should not include subsegments such as p→r or q→r. For simplicity,
    //we will not supply any input to BruteCollinearPoints that has 5 or more collinear points.
    public BruteCollinearPoints(Point[] points) {
        // finds all line segments containing 4 points
    }
    public int numberOfSegments() {
        return count;
    }
    public LineSegment[] segments() {
        return collinearLines;
    }
}
