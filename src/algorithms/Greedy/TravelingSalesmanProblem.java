package algorithms.Greedy;

import interfaces.Algorithm;

import java.util.ArrayList;
import java.util.List;

public class TravelingSalesmanProblem implements Algorithm {
    private int[][] graph;
    private int numOfVertices;

    public TravelingSalesmanProblem(int[][] graph) {
        this.graph = graph;
        this.numOfVertices = graph.length;
    }

    @Override
    public void execute() {
        // Main logic is in solveTSP, so execute can be left empty or used to call solveTSP
    }

    @Override
    public void displayResult() {
        List<Integer> optimalPath = solveTSP(0); // Assuming startVertex 0
        System.out.println("Optimal Path for TSP: " + optimalPath);
    }

    public List<Integer> solveTSP(int startVertex) {
        List<Integer> path = new ArrayList<>();
        boolean[] visited = new boolean[numOfVertices];

        path.add(startVertex);
        visited[startVertex] = true;

        while (path.size() < numOfVertices) {
            int currentVertex = path.get(path.size() - 1);
            int nextVertex = getNextVertex(currentVertex, visited);

            if (nextVertex == -1) {
                break; // No more vertices to visit
            }

            path.add(nextVertex);
            visited[nextVertex] = true;
        }

        // Return to the start vertex to complete the cycle
        path.add(startVertex);

        return path;
    }

    private int getNextVertex(int currentVertex, boolean[] visited) {
        int minWeight = Integer.MAX_VALUE;
        int nextVertex = -1;

        for (int i = 0; i < numOfVertices; i++) {
            if (!visited[i] && graph[currentVertex][i] < minWeight) {
                minWeight = graph[currentVertex][i];
                nextVertex = i;
            }
        }

        return nextVertex;
    }

    // Optional: Provide a constructor without parameters for integration in UI or testing
    public TravelingSalesmanProblem() {
        // Initialize with a default or empty graph
        this.graph = new int[0][0]; // Replace with appropriate initialization
        this.numOfVertices = 0; // Initialize with appropriate value
    }

    // Optional: Setter method for graph
    public void setGraph(int[][] graph) {
        this.graph = graph;
        this.numOfVertices = graph.length;
    }
}
