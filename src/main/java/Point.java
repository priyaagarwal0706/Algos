import java.util.Comparator;
import edu.princeton.cs.algs4.StdDraw;

public class Point implements Comparable<Point> {
    
    private final int x;
    private final int y; 
    public Point(int x, int y) {
       this.x = x;
       this.y = y;
    }

    public  void draw() {
        StdDraw.point(x, y);
    }
    public  void drawTo(Point that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }
    public String toString()  {
        return "(" + x + ", " + y + ")";
    }

    public int compareTo(Point that) {
        if (that == null) {
            throw new NullPointerException("Input is null");
        }
        if (this.y < that.y || (this.y == that.y && this.x < that.x))
            return -1;
        else if (this.x == that.x && this.y == that.y) 
            return 0;
        else
            return 1;
    }
    public double slopeTo(Point that)  {
        if (that == null) {
            throw new NullPointerException("Input is null");
        }
        if (this.x == that.x && this.y == that.y) {
            return Double.NEGATIVE_INFINITY;
        }
        if (this.y == that.y) {
            return 0.0;
        }
        if (this.x == that.x) {
            return Double.POSITIVE_INFINITY;
        }
        
        return (double) (that.y - this.y) / (that.x - this.x);
    }
    public Comparator<Point> slopeOrder() {
        return new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                if (o1 == null || o2 == null) {
                    throw new NullPointerException("Input is null");
                }
                if (slopeTo(o1) < slopeTo(o2)) 
                    return -1;
                if (slopeTo(o1) > slopeTo(o2)) 
                    return 1;
                return 0;
            }
        };
    }
   
}
