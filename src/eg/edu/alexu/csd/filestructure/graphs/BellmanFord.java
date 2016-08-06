package eg.edu.alexu.csd.filestructure.graphs;

import java.util.ArrayList;
import java.util.Arrays;

public class BellmanFord {

  public boolean run(ArrayList<Edge> edges, int[] distances, int src, int n) {
    Arrays.fill(distances, Integer.MAX_VALUE / 2);
    distances[src] = 0;
    
    for (int i = 0; i < n - 1; i++) {
      boolean flag = false;
      for (Edge e : edges) {
        if (distances[e.getFrom()] + e.getWeight() < distances[e.getTo()]) {
          distances[e.getTo()] = distances[e.getFrom()] + e.getWeight();
          flag = true;
        }
      }
      if(!flag) {
        break;
      }
    }
    
    for (Edge e : edges) {
      if (distances[e.getFrom()] + e.getWeight() < distances[e.getTo()]) {
        return false;
      }
    }
    return true;
  }
}
