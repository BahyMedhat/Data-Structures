package eg.edu.alexu.csd.filestructure.graphs;

public class Edge implements Comparable<Edge> {

  private Integer from, to, weight;

  public Edge(int from, int to, int weight) {
    this.setFrom(from);
    this.setTo(to);
    this.setWeight(weight);
  }

  public Integer getFrom() {
    return from;
  }

  public void setFrom(Integer from) {
    this.from = from;
  }

  public Integer getTo() {
    return to;
  }

  public void setTo(Integer to) {
    this.to = to;
  }
  
  public Integer getWeight() {
    return weight;
  }

  public void setWeight(Integer weight) {
    this.weight = weight;
  }
  
  @Override
  public int compareTo(Edge edge) {
    // TODO Auto-generated method stub
    return this.weight.compareTo(edge.weight);
  }
}
