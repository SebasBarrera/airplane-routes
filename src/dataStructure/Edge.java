package dataStructure;

public class Edge<E extends Comparable<E>> {
	
	private Vertex<E> from;
	private Vertex<E> to;
	private double weight;
	/**
	 * @param from
	 * @param to
	 * @param weight
	 */
	public Edge(Vertex<E> from, Vertex<E> to, double weight) {
		super();
		this.from = from;
		this.to = to;
		this.weight = weight;
	}
	/**
	 * @return the from
	 */
	public Vertex<E> getFrom() {
		return from;
	}
	/**
	 * @param from the from to set
	 */
	public void setFrom(Vertex<E> from) {
		this.from = from;
	}
	/**
	 * @return the to
	 */
	public Vertex<E> getTo() {
		return to;
	}
	/**
	 * @param to the to to set
	 */
	public void setTo(Vertex<E> to) {
		this.to = to;
	}
	/**
	 * @return the weight
	 */
	public double getWeight() {
		return weight;
	}
	/**
	 * @param weight the weight to set
	 */
	public void setWeight(double weight) {
		this.weight = weight;
	}

}
