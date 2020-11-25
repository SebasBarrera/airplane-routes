package dataStructure;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class VertexList<E> implements Comparable<VertexList<E>>{
	
	private E object;
	private String color;
	private int distance;
	private VertexList<E> predecessor;
	private List<Adjacent<E>> adjacents;
	private int f;
	
	public VertexList(E object) {
		this.object = object;
		adjacents = new ArrayList<Adjacent<E>>();
	}

	public E getObject() {
		return object;
	}

	public void setObject(E object) {
		this.object = object;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public VertexList<E> getPredecessor() {
		return predecessor;
	}

	public void setPredecessor(VertexList<E> predecessor) {
		this.predecessor = predecessor;
	}

	public List<Adjacent<E>> getAdjacents() {
		return adjacents;
	}

	public void setAdjacents(List<Adjacent<E>> adjacents) {
		this.adjacents = adjacents;
	}
	
	public int getF() {
		return f;
	}

	public void setF(int f) {
		this.f = f;
	}

	public void deleteAdjacent(E toDelete) {
		boolean finded = false;
		for(int i = 0; i < adjacents.size() && !finded; i++) {
			if(adjacents.get(i).getVertex().getObject().equals(toDelete)) {
				adjacents.remove(i);
				finded = true;
			}
		}
	}

	@Override
	public String toString() {
		return object.toString();
	}
	
	public int weight(VertexList<E> vertex) {
		boolean finded = false;
		int weight = Integer.MAX_VALUE;
		for(int i = 0; i < adjacents.size() && !finded; i++) {
			if(adjacents.get(i).getVertex().equals(vertex)) {
				finded = true;
				weight = adjacents.get(i).getWeight();
			}
		}
		return weight;
	}

	@Override
	public int compareTo(VertexList<E> arg0) {
		return distance - arg0.distance;
	}
}
