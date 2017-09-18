import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }
    
    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getLargestSide(s);
        System.out.println("perimeter = " + length);
    }
    
    public int getNumPoints (Shape s) {
        int count = 0;
        for (Point currPt : s.getPoints()) {
            count++;
        }
        return count;
    }

    public double getAverageLength(Shape s) {
        // Put code here
        double perimeter = getPerimeter(s);
        double sides = getNumPoints(s);
        if(sides < 2) return 0.0;
        return perimeter / sides;
    }

    public double getLargestSide(Shape s) {
        // Put code here
        double maxSide = 0.0;
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            if(currDist > maxSide){
                maxSide = currDist;
            }
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        return maxSide;
    }

    public double getLargestX(Shape s) {
        double maxX = Double.MIN_VALUE;
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            maxX = Math.max(maxX, currPt.getX());
        }
        return maxX;
    }

    public double getLargestPerimeterMultipleFiles() {
        double largestPerimeter = Double.MIN_VALUE;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            largestPerimeter = Math.max(largestPerimeter, getPerimeter(s));
        }
        return largestPerimeter;
    }

    public String getFileWithLargestPerimeter() {
        String fileName = "";
        double largestPerimeter = Double.MIN_VALUE;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double thisPerimeter = getPerimeter(s);
            if(thisPerimeter > largestPerimeter){
                largestPerimeter = thisPerimeter;
                fileName = f.getName();
            }
        }
        return fileName;
    }

    public void testMaximumPerimeterMultipleFiles() {
        System.out.println(getLargestPerimeterMultipleFiles());
    }

    public void testFileWithLargestPerimeter() {
        System.out.println(getFileWithLargestPerimeter());
    }
    
    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
    }
}
