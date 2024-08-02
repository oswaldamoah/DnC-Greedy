package algorithms.DivideAndConquer;

import java.util.Scanner;

public class ClosestPair {

    public static void closestPairInteractive(Scanner scanner) {
        // Step 1: Read the number of points from the user
        System.out.println("Enter the number of points:");
        int n = scanner.nextInt();
        Point[] points = new Point[n];

        // Step 2: Read the coordinates for each point
        for (int i = 0; i < n; i++) {
            System.out.println("Enter the coordinates for point " + (i + 1) + " (format: x y):");
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            points[i] = new Point(x, y);
        }

        // Variables to track the minimum distance and closest pair of points
        double minDistance = Double.MAX_VALUE;
        Point closestPoint1 = null;
        Point closestPoint2 = null;

        // Step 3: Find the minimum distance using a brute-force approach
        long startTime = System.nanoTime();
        for (int i = 0; i < points.length - 1; i++) {
            for (int j = i + 1; j < points.length; j++) {
                double distance = points[i].distance(points[j]);
                if (distance < minDistance) {
                    minDistance = distance;
                    closestPoint1 = points[i];
                    closestPoint2 = points[j];
                }
            }
        }
        long endTime = System.nanoTime();

        // Output the result
        if (closestPoint1 != null && closestPoint2 != null) {
            System.out.println("Closest pair of points: (" + closestPoint1.x + ", " + closestPoint1.y +
                    ") and (" + closestPoint2.x + ", " + closestPoint2.y + ")");
        }
        System.out.println("Minimum distance: " + minDistance);
        long duration = (endTime - startTime);
        System.out.println("Execution time: " + duration + " nanoseconds.");
        System.out.println("Time complexity: O(n^2)");
    }

    // Point class for Closest Pair
    static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        double distance(Point p) {
            return Math.sqrt(Math.pow(this.x - p.x, 2) + Math.pow(this.y - p.y, 2));
        }
    }
}
