package algorithms.Greedy;

import interfaces.Algorithm;
import models.Graph;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class PrimsMST implements Algorithm {
    private Graph graph;
    private boolean[] inMST;
    private int[] parent;
    private int[] key;

    public PrimsMST() {
        this.graph = new Graph(0);
        this.inMST = new boolean[0];
        this.parent = new int[0];
        this.key = new int[0];
    }

    public PrimsMST(Graph graph) {
        this.graph = graph;
        this.inMST = new boolean[graph.getVertices()];
        this.parent = new int[graph.getVertices()];
        this.key = new int[graph.getVertices()];
    }

    @Override
    public void execute() {
        if (graph.getVertices() == 0) {
            System.out.println("Graph is empty.");
            return;
        }

        PriorityQueue<Pair> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(p -> p.key));

        for (int i = 0; i < graph.getVertices(); i++) {
            key[i] = Integer.MAX_VALUE;
            parent[i] = -1;
            priorityQueue.add(new Pair(i, key[i]));
        }

        key[0] = 0;
        priorityQueue.add(new Pair(0, key[0]));

        while (!priorityQueue.isEmpty()) {
            Pair minPair = priorityQueue.poll();
            int u = minPair.vertex;
            inMST[u] = true;

            for (Graph.Edge edge : graph.getAdjacencyList().get(u)) {
                int v = edge.getDestination();
                int weight = edge.getWeight();
                if (!inMST[v] && key[v] > weight) {
                    key[v] = weight;
                    parent[v] = u;
                    priorityQueue.add(new Pair(v, key[v]));
                }
            }
        }
    }

    @Override
    public void displayResult() {
        if (graph.getVertices() == 0) {
            System.out.println("Graph is empty.");
            return;
        }

        System.out.println("Edges in the Minimum Spanning Tree (Prim's MST):");
        for (int i = 1; i < graph.getVertices(); i++) {
            System.out.println(parent[i] + " - " + i + "\t" + key[i]);
        }
    }

    private static class Pair {
        private int vertex;
        private int key;

        public Pair(int vertex, int key) {
            this.vertex = vertex;
            this.key = key;
        }
    }

    public static void primMSTInteractive(Scanner scanner) {
        System.out.print("Enter the number of vertices: ");
        int vertices = scanner.nextInt();

        Graph graph = new Graph(vertices);

        System.out.print("Enter the number of edges: ");
        int edgesCount = scanner.nextInt();

        for (int i = 0; i < edgesCount; i++) {
            System.out.print("Enter edge " + (i + 1) + " (source, destination, weight): ");
            int source = scanner.nextInt();
            int destination = scanner.nextInt();
            int weight = scanner.nextInt();
            graph.addEdge(source, destination, weight);
        }

        PrimsMST primsMST = new PrimsMST(graph);
        primsMST.execute();
        primsMST.displayResult();
    }
}
