package ui;

import algorithms.DivideAndConquer.ClosestPair;
import algorithms.DivideAndConquer.MergeSort;
import algorithms.DivideAndConquer.QuickSort;
import algorithms.DivideAndConquer.Quickhull;
import algorithms.DivideAndConquer.StrassensMatrixMultiplication;
import algorithms.Greedy.DijkstraShortestPath;
import algorithms.Greedy.HuffmanTree;
import algorithms.Greedy.KruskalsMST;
import algorithms.Greedy.PrimsMST;
import algorithms.Greedy.TravelingSalesmanProblem;
import interfaces.Algorithm;
import java.util.Scanner;
import models.Graph;
import utils.PerformanceTester;

public class UserInterface {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("Select an algorithm:");
                System.out.println("1. QuickSort");
                System.out.println("2. MergeSort");
                System.out.println("3. Closest-Pair Problem");
                System.out.println("4. Strassen’s Matrix Multiplication");
                System.out.println("5. Quickhull");
                System.out.println("6. Prim’s Minimum Spanning Tree");
                System.out.println("7. Traveling Salesman Problem");
                System.out.println("8. Kruskal’s Minimum Spanning Tree");
                System.out.println("9. Dijkstra’s Shortest Path");
                System.out.println("10. Huffman Codes");
                System.out.println("0. Exit");
                

                int choice = scanner.nextInt();
                Algorithm algorithm = null;

                switch (choice) {
                    case 1:
                        // QuickSort
                        int[] arrayToQuickSort = readIntArrayFromConsole(scanner);
                        algorithm = new QuickSort(arrayToQuickSort);
                        break;
                    case 2:
                        // MergeSort
                        int[] arrayToMergeSort = readIntArrayFromConsole(scanner);
                        algorithm = new MergeSort(arrayToMergeSort);
                        break;
                    case 3:
                        // Closest-Pair Problem
                        ClosestPair.closestPairInteractive(scanner);
                        break;
                    case 4:
                        // Strassen’s Matrix Multiplication
                        StrassensMatrixMultiplication.strassenInteractive(scanner);
                        break;
                    case 5:
                        // Quickhull
                        Quickhull.quickhullInteractive(scanner);

                        break;
                    case 6:
                        //  Prim’s Minimum Spanning Tree
                        PrimsMST.primMSTInteractive(scanner);

                        break;
                    case 7:
                        // Traveling Salesman Problem
                        int[][] tspGraph = readGraphFromConsole(scanner);
                        algorithm = new TravelingSalesmanProblem(tspGraph);
                        break;
                    case 8:
                    KruskalsMST.kruskalsMSTInteractive(scanner);
                        break;
                    case 9:
                        // Dijkstra’s Shortest Path
                        int[][] dijkstraGraph = readGraphFromConsole(scanner);
                        System.out.print("Enter the source vertex: ");
                        int sourceVertex = scanner.nextInt();
                        algorithm = new DijkstraShortestPath(new Graph(dijkstraGraph.length), sourceVertex);
                        break;
                    case 10:
                        // Huffman Codes
                        // Read input text from the user
                        System.out.print("Enter the text to encode: ");
                        scanner.nextLine(); // Consume newline left from nextInt()

                        String text = scanner.nextLine();

                        // Create HuffmanTree instance and encode the text
                        HuffmanTree huffmanTree = new HuffmanTree();
                        String encodedText = huffmanTree.encode(text);
                        System.out.println("Encoded text: " + encodedText);

                        // Decode the encoded text
                        String decodedText = huffmanTree.decode(encodedText);
                        System.out.println("Decoded text: " + decodedText);
                        break;
                    case 0:
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice");
                        continue;
                }

                if (algorithm != null) {
                    PerformanceTester.testAlgorithm(algorithm);
                }
            }
        }
    }


    // MERGESORT AND QUICKSORT
    private static int[] readIntArrayFromConsole(Scanner scanner) {
        System.out.println("Enter the number of elements:");
        int n = scanner.nextInt();
        int[] array = new int[n];

        System.out.println("Enter the elements of the array:");
        for (int i = 0; i < n; i++) {
            array[i] = scanner.nextInt();
        }

        return array;
    }

    // TRAVELING SALESMAN PROBLEM  & DIKSTRA
    private static int[][] readGraphFromConsole(Scanner scanner) {
        System.out.print("Enter the number of vertices: ");
        int vertices = scanner.nextInt();

        int[][] graph = new int[vertices][vertices];

        System.out.println("Enter the adjacency matrix (enter " + Integer.MAX_VALUE + " for no direct path):");
        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                graph[i][j] = scanner.nextInt();
            }
        }

        return graph;
    }
}