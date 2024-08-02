package algorithms.Greedy;

import java.util.*;

public class KruskalsMST {

    public static void kruskalsMSTInteractive(Scanner scanner) {
        // Read the number of vertices and edges from the user
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

        // Instantiate KruskalsAlgorithm and execute
        KruskalsAlgorithm kruskalsAlgorithm = new KruskalsAlgorithm(graph);
        kruskalsAlgorithm.execute();
        List<Edge> mstEdges = kruskalsAlgorithm.getMSTEdges();
        displayResult(mstEdges);
    }

    static class Edge {
        private final int source;
        private final int destination;
        private final int weight;

        public Edge(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }

        public int getSource() {
            return source;
        }

        public int getDestination() {
            return destination;
        }

        public int getWeight() {
            return weight;
        }
    }

    static class Graph {
        private final int vertices;
        private final List<Edge> edges;

        public Graph(int vertices) {
            this.vertices = vertices;
            edges = new ArrayList<>();
        }

        public void addEdge(int source, int destination, int weight) {
            edges.add(new Edge(source, destination, weight));
        }

        public List<Edge> getEdges() {
            return edges;
        }

        public int getVertices() {
            return vertices;
        }
    }

    public static class KruskalsAlgorithm {
        private Graph graph;
        private List<Edge> mstEdges;

        public KruskalsAlgorithm(Graph graph) {
            this.graph = graph;
            mstEdges = new ArrayList<>();
        }

        public void execute() {
            UnionFind uf = new UnionFind(graph.getVertices());
            List<Edge> edges = graph.getEdges();
            edges.sort(Comparator.comparingInt(Edge::getWeight));

            for (Edge edge : edges) {
                int u = edge.getSource();
                int v = edge.getDestination();

                if (uf.find(u) != uf.find(v)) {
                    uf.union(u, v);
                    mstEdges.add(edge);
                }
            }
        }

        public List<Edge> getMSTEdges() {
            return mstEdges;
        }
    }

    static class UnionFind {
        private int[] parent;
        private int[] rank;

        public UnionFind(int size) {
            parent = new int[size];
            rank = new int[size];
            for (int i = 0; i < size; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
        }

        public int find(int u) {
            if (parent[u] != u) {
                parent[u] = find(parent[u]);
            }
            return parent[u];
        }

        public void union(int u, int v) {
            int rootU = find(u);
            int rootV = find(v);

            if (rootU != rootV) {
                if (rank[rootU] > rank[rootV]) {
                    parent[rootV] = rootU;
                } else if (rank[rootU] < rank[rootV]) {
                    parent[rootU] = rootV;
                } else {
                    parent[rootV] = rootU;
                    rank[rootU]++;
                }
            }
        }
    }

    public KruskalsMST() {
        // Default constructor if needed for instantiation
    }

    private static void displayResult(List<Edge> mstEdges) {
        System.out.println("Edges in the Minimum Spanning Tree (Kruskal's MST):");
        for (Edge edge : mstEdges) {
            System.out.println(edge.getSource() + " - " + edge.getDestination() + "\t" + edge.getWeight());
        }
    }

    public static void main(String[] args) {
        // Example usage for standalone testing
        Scanner scanner = new Scanner(System.in);
        kruskalsMSTInteractive(scanner);
        scanner.close();
    }
}
