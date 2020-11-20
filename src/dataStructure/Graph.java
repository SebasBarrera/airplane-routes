package dataStructure;

import java.util.ArrayList;

public class Graph<E extends Comparable<E>> {
	
	private double[][] weight;
	private int[][] a;// adjacency Matrix
	private boolean directed;
	private int size;
	
	private ArrayList<Vertex<E>> vertex;// todos los vertices
	
	public ArrayList<Vertex<E>> bfs(Vertex<E> s) {
		ArrayList<Vertex<E>> ve = null;
		for (int i = 0; i < vertex.size(); i++) {
			Vertex<E> u = vertex.get(i);
			u.setColor(Vertex.WHITE);
			u.setPrev(null);
			u.setDistance(Integer.MAX_VALUE);;
		}
		s.setColor(Vertex.GRAY);
		s.setPrev(null);
		s.setDistance(0);
		Queue<Vertex<E>> q = new Queue<>();
		q.offer(s);
		while (!q.isEmpty()) {
			Vertex<E> u = q.poll();
			for (int i = 0; i < a.length; i++) {
				Vertex<E> v = u.getAdj();
				if (v.getColor() == Vertex.WHITE) {
					v.setColor(Vertex.GRAY);
					v.setDistance(u.getDistance() + 1);
					v.setPrev(u);
					q.offer(v);
				}
			}
			u.setColor(Vertex.BLACK);
		}
		return ve;
	}
	
	public ArrayList<Vertex<E>> dfs(Vertex<E> s) {
		
		return vertex;
		
	}
	
	public Vertex[] adj(int i) {
		Vertex[] v = new Vertex[a.length];
		for (int j = 0; j < vertex.size(); j++) {
			if (a[i][j] != 0) {
				if (a[i][j] != 0) {
					
				}
			}
		}
		return v;
		
	}

}
