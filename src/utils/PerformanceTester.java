package utils;

import interfaces.Algorithm;


public class PerformanceTester {
    public static void testAlgorithm(Algorithm algorithm) {
        long startTime = System.nanoTime();
        algorithm.execute();
        long endTime = System.nanoTime();
        algorithm.displayResult();
        System.out.println("Execution time: " + (endTime - startTime) + " ns");
        System.out.println(" ");

    }
}
