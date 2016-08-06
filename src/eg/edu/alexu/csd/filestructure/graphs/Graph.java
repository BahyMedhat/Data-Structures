package eg.edu.alexu.csd.filestructure.graphs;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Graph implements IGraph {

  private ArrayList<ArrayList<Edge>> graph;
  private ArrayList<Edge> edges;
  private ArrayList<Integer> vertices;
  private int verticesNum, edgesNum;
  private Scanner sc;
  Dijkstra dijkstra;

  public Graph() {
    graph = new ArrayList<ArrayList<Edge>>();
    edges = new ArrayList<Edge>();
    vertices = new ArrayList<Integer>();
    dijkstra = new Dijkstra();
    verticesNum = 0;
    edgesNum = 0;
  }

  @Override
  public void readGraph(File file) {
    // TODO Auto-generated method stub
    if (!file.exists()) {
      throw new RuntimeException("File Not Found!!");
    }
    try {
      sc = new Scanner(file);
      if (sc.hasNext()) {
        verticesNum = sc.nextInt();
      }
      if (sc.hasNext()) {
        edgesNum = sc.nextInt();
      }
      boolean vis[] = new boolean[verticesNum];
      int iter = 0;
      graph = new ArrayList<ArrayList<Edge>>();
      edges = new ArrayList<Edge>();
      vertices = new ArrayList<Integer>();
      for (int i = 0; i < verticesNum; i++) {
        graph.add(new ArrayList<Edge>());
      }
      for (iter = 0; iter < edgesNum && sc.hasNext(); iter++) {
        int i = sc.nextInt();
        if(i < 0 || i >= verticesNum) {
          throw new RuntimeException("Vertex out of bounds");
        }
        int j = sc.nextInt();
        if(j < 0 || j >= verticesNum) {
          throw new RuntimeException("Vertex out of bounds");
        }
        if (!vis[i]) {
          vertices.add(i);
          vis[i] = true;
        }
        if (!vis[j]) {
          vertices.add(j);
          vis[j] = true;
        }
        int w = sc.nextInt();
        edges.add(new Edge(i, j, w));
        graph.get(i).add(new Edge(i, j, w));
      }
      if(iter != edgesNum) {
        throw new RuntimeException("Incomplete Graph");
      }
      sc.close();
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  @Override
  public int size() {
    // TODO Auto-generated method stub
    return edges.size();
  }

  @Override
  public ArrayList<Integer> getVertices() {
    // TODO Auto-generated method stub
    return vertices;
  }

  @Override
  public ArrayList<Integer> getNeighbors(int v) {
    // TODO Auto-generated method stub
    ArrayList<Integer> neighbors = new ArrayList<Integer>();
    for(int i = 0; i < graph.get(v).size(); i++) {
      neighbors.add(graph.get(v).get(i).getTo());
    }
    return neighbors;
  }

  @Override
  public void runDijkstra(int src, int[] distances) {
    // TODO Auto-generated method stub
    dijkstra.run(graph, distances, src, verticesNum);
  }

  @Override
  public ArrayList<Integer> getDijkstraProcessedOrder() {
    // TODO Auto-generated method stub
    return dijkstra.getDijkstraOrder();
  }

  @Override
  public boolean runBellmanFord(int src, int[] distances) {
    // TODO Auto-generated method stub
    BellmanFord bellman = new BellmanFord();
    return bellman.run(edges, distances, src, verticesNum);
  }
}