package dataStructure;

public class Edge<T> implements Comparable<Edge<T>>{

	private double weight;

	private Vertex<T> source;
	private Vertex<T> destination;
	private int time;

	public Edge(Vertex<T> source, Vertex<T> destination) {
		this(source, destination, 1D);
		this.time=0;
	}

	public Edge(Vertex<T> source, Vertex<T> destination, double weight) {
		this.source = source;
		this.destination = destination;
		this.weight = weight;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	public int getTimes() {
		return time;
	}
	
	public void setTimes(int name) {
		this.time=time+ name;
	}

	public Vertex<T> getSource() {
		return source;
	}

	public Vertex<T> getDestination() {
		return destination;
	}
	
	public boolean areTheSame(Edge<T> o) {
		boolean same = false;
		if(o.getSource().equals(source)&&o.getDestination().equals(destination) || o.getSource().equals(destination)&&o.getDestination().equals(source)) {
			same = true;
		}
		return same;
	}

	@Override
	public int compareTo(Edge<T> o) {
		return Double.compare(weight, o.weight);
	}

}