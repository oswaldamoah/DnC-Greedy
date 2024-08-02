package algorithms.DivideAndConquer;

import interfaces.Algorithm;
import models.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Quickhull implements Algorithm {
    private List<Point> points;
    private List<Point> hull;

    // Default constructor
    public Quickhull() {
        this.points = new ArrayList<>();
        this.hull = new ArrayList<>();
    }

    public Quickhull(List<Point> points) {
        this.points = points;
        this.hull = new ArrayList<>();
    }

    @Override
    public void execute() {
        if (points.size() < 3) {
            hull = new ArrayList<>(points);
            return;
        }

        Point minPoint = points.get(0);
        Point maxPoint = points.get(0);

        for (Point point : points) {
            if (point.x < minPoint.x) {
                minPoint = point;
            }
            if (point.x > maxPoint.x) {
                maxPoint = point;
            }
        }

        hull.add(minPoint);
        hull.add(maxPoint);

        List<Point> leftSet = new ArrayList<>();
        List<Point> rightSet = new ArrayList<>();

        for (Point point : points) {
            if (point == minPoint || point == maxPoint) {
                continue;
            }

            if (isLeft(minPoint, maxPoint, point)) {
                leftSet.add(point);
            } else {
                rightSet.add(point);
            }
        }

        findHull(minPoint, maxPoint, leftSet);
        findHull(maxPoint, minPoint, rightSet);
    }

    private void findHull(Point A, Point B, List<Point> set) {
        int insertPosition = hull.indexOf(B);

        if (set.isEmpty()) {
            return;
        }

        if (set.size() == 1) {
            Point point = set.get(0);
            set.remove(point);
            hull.add(insertPosition, point);
            return;
        }

        int dist = Integer.MIN_VALUE;
        Point furthestPoint = null;

        for (Point point : set) {
            int distance = distance(A, B, point);
            if (distance > dist) {
                dist = distance;
                furthestPoint = point;
            }
        }

        hull.add(insertPosition, furthestPoint);
        List<Point> leftSetA = new ArrayList<>();
        List<Point> leftSetB = new ArrayList<>();

        for (Point point : set) {
            if (point == furthestPoint) {
                continue;
            }

            if (isLeft(A, furthestPoint, point)) {
                leftSetA.add(point);
            }

            if (isLeft(furthestPoint, B, point)) {
                leftSetB.add(point);
            }
        }

        findHull(A, furthestPoint, leftSetA);
        findHull(furthestPoint, B, leftSetB);
    }

    private boolean isLeft(Point A, Point B, Point P) {
        return ((B.x - A.x) * (P.y - A.y) - (B.y - A.y) * (P.x - A.x)) > 0;
    }

    private int distance(Point A, Point B, Point C) {
        int ABx = B.x - A.x;
        int ABy = B.y - A.y;
        int num = ABx * (A.y - C.y) - ABy * (A.x - C.x);
        return Math.abs(num);
    }

    @Override
    public void displayResult() {
        System.out.println("Convex Hull: ");
        for (Point point : hull) {
            System.out.println(point);
        }
    }

    // Method to execute Quickhull interactively
    public static void quickhullInteractive(Scanner scanner) {
        List<Point> points = new ArrayList<>();

        // Step 1: Read the number of points from the user
        System.out.println("Enter the number of points:");
        int n = scanner.nextInt();

        // Step 2: Read the coordinates for each point
        System.out.println("Enter the coordinates for each point (format: x y):");
        for (int i = 0; i < n; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            points.add(new Point(x, y));
        }

        // Step 3: Create an instance of Quickhull and execute the algorithm
        Quickhull quickhull = new Quickhull(points);
        quickhull.execute();

        // Step 4: Display the convex hull
        quickhull.displayResult();
    }
}
