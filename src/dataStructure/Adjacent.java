package dataStructure;

public class Adjacent<E> {
	
	private VertexList<E> vertex;
	private int weight;
	
	public Adjacent(VertexList<E> vertex, int weight) {
		this.vertex = vertex;
		this.weight = weight;
	}

	public VertexList<E> getVertex() {
		return vertex;
	}

	public void setVertex(VertexList<E> vertex) {
		this.vertex = vertex;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
}