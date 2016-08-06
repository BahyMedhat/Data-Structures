package eg.edu.alexu.csd.filestructure.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Dijkstra {
  
  private ArrayList<Integer> order = new ArrayList<Integer>();

  public int[] run(ArrayList<ArrayList<Edge>> graph, int[] distances, int src, int n) {
    Arrays.fill(distances, Integer.MAX_VALUE / 2);
    boolean[] visited = new boolean[n];
    PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
    distances[0] = 0;
    pq.add(new Edge(-1, src, 0));
    
    while (!pq.isEmpty()) {
      Edge edge = pq.poll();
      if (!visited[edge.getTo()]) {
        order.add(edge.getTo());
        visited[edge.getTo()] = true;
        for (Edge nEdge : graph.get(edge.getTo())) {
          if (distances[nEdge.getFrom()] + nEdge.getWeight() < distances[nEdge.getTo()]) {
            distances[nEdge.getTo()] = distances[nEdge.getFrom()] + nEdge.getWeight();
            pq.add(new Edge(nEdge.getFrom(), nEdge.getTo(), distances[nEdge.getTo()]));
          }
        }
      }
    }
    return distances;
  }
  
  public ArrayList<Integer> getDijkstraOrder() {
    return order;
  }
}
