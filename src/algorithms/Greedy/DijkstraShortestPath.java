package algorithms.Greedy;

import interfaces.Algorithm;
import java.util.*;
import models.Graph;

public class DijkstraShortestPath implements Algorithm {
    private Graph graph;
    private int source;
    private int[] dist;
    private boolean[] visited;

    public DijkstraShortestPath(Graph graph, int source) {
        this.graph = graph;
        this.source = source;
        dist = new int[graph.getVertices()];
        visited = new boolean[graph.getVertices()];
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(visited, false);
    }

    @Override
    public void execute() {
        dist[source] = 0;
        PriorityQueue<Pair> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(p -> p.distance));
        priorityQueue.add(new Pair(source, 0));

        while (!priorityQueue.isEmpty()) {
            Pair minPair = priorityQueue.poll();
            int u = minPair.vertex;

            if (visited[u]) {
                continue;
            }

            visited[u] = true;

            for (Graph.Edge edge : graph.getAdjacencyList().get(u)) {
                int v = edge.getDestination();
                int weight = edge.getWeight();

                if (!visited[v] && dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                    priorityQueue.add(new Pair(v, dist[v]));
                }
            }
        }
    }

    @Override
    public void displayResult() {
        System.out.println("Shortest distances from source vertex " + source + ":");
        for (int i = 0; i < graph.getVertices(); i++) {
            System.out.println("Vertex " + i + ": " + dist[i]);
        }
    }

    private static class Pair {
        private int vertex;
        private int distance;

        public Pair(int vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Step 1: Read the number of vertices and edges
        System.out.print("Enter the number of vertices in the graph: ");
        int vertices = scanner.nextInt();

        // Step 2: Create a graph object and populate the adjacency list
        Graph graph = new Graph(vertices);
        System.out.print("Enter the number of edges: ");
        int edges = scanner.nextInt();

        System.out.println("Enter edges in format <source> <destination> <weight>: ");
        for (int i = 0; i < edges; i++) {
            int source = scanner.nextInt();
            int destination = scanner.nextInt();
            int weight = scanner.nextInt();
            graph.addEdge(source, destination, weight);
        }

        // Step 3: Read the source vertex from the user
        System.out.print("Enter the source vertex: ");
        int sourceVertex = scanner.nextInt();

        // Step 4: Perform Dijkstra's algorithm
        DijkstraShortestPath dijkstra = new DijkstraShortestPath(graph, sourceVertex);
        dijkstra.execute();

        // Step 5: Display the shortest distances from the source vertex
        dijkstra.displayResult();

        scanner.close();
    }
}
