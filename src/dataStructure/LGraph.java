package dataStructure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class LGraph<T> implements IGraph<T> {

	private boolean directed;
	private boolean weighted;
	private int numberOfVertices;
	private int numberOfEdges;

	private List<Vertex<T>> vertices;
	private HashMap<T, AdjVertex<T>> map;
	
	private List<Edge<T>> edgeCount;

	public LGraph(boolean directed, boolean weighted) {
		this.directed = directed;
		this.weighted = weighted;
		numberOfVertices = 0;
		numberOfEdges = getNumberOfEdges();
		vertices = new LinkedList<Vertex<T>>();
		map = new HashMap<>();
		edgeCount = new LinkedList<Edge<T>>();
	}

	public List<Vertex<T>> getVertices() {
		return vertices;
	}
	
	public List<Edge<T>> getEdgeCount() {
		return edgeCount;
	}

	public int getNumberOfVertices() {
		return numberOfVertices;
	}

	public int getNumberOfEdges() {
		return numberOfEdges;
	}

	public boolean isDirected() {
		return directed;
	}

	public boolean isWeighted() {
		return weighted;
	}
	
	public void addEfgeCount() {
		
	}

	@Override
	public void addVertex(T value) {
		if (!isInGraph(value)) {
			AdjVertex<T> vertex = new AdjVertex<T>(value);
			map.put(value, vertex);
			vertex.setIndex(numberOfVertices);
			vertices.add(vertex);
			numberOfVertices++;
		}
	}

	@Override
	public void addEdge(T x, T y) {
		AdjVertex<T> from = searchVertex(x);
		AdjVertex<T> to = searchVertex(y);
		addEdge(from, to);
	}

	public void addEdge(AdjVertex<T> from, AdjVertex<T> to) {
		addEdge(from, to, 1D);
	}

	@Override
	public void addEdge(T x, T y, double w) {
		if (weighted) {
			AdjVertex<T> from = searchVertex(x);
			AdjVertex<T> to = searchVertex(y);
			addEdge(from, to, w);
		}
	}

	public void addEdge(AdjVertex<T> from, AdjVertex<T> to, double w) {
		if (from != null && to != null) {
			Edge<T> edge = new Edge<T>(from, to, w);
			from.getAdjList().add(edge);
			if (!isDirected()) {
				edge = new Edge<T>(to, from, w);
				to.getAdjList().add(edge);
			}
			numberOfEdges++;
		}

	}

	public void removeVertex(Vertex<T> v) {
		for (int i = 0; i < vertices.size(); i++) {
			removeEdge(vertices.get(i), v);
			if (isDirected()) {
				removeEdge(v, vertices.get(i));
			}
		}
		vertices.remove(v);
		map.remove(v.getValue());
		numberOfVertices--;
	}

	public void removeVertex(T v) {
		if (isInGraph(v)) {
			removeVertex(searchVertex(v));
		}
	}

	public void removeEdge(Vertex<T> x, Vertex<T> y) {
		AdjVertex<T> from = (AdjVertex<T>) x;
		AdjVertex<T> to = (AdjVertex<T>) y;
		List<Edge<T>> adjFrom = from.getAdjList();
		Edge<T> e = from.findEdge(to);
		if (e != null)
			adjFrom.remove(e);

		if (!isDirected()) {
			List<Edge<T>> adjTo = to.getAdjList();
			e = to.findEdge(from);
			if (e != null)
				adjTo.remove(e);
		}

		numberOfEdges--;
	}

	public void removeEdge(T x, T y) {
		if (isInGraph(x) && isInGraph(y)) {
			removeEdge(searchVertex(x), searchVertex(y));
		}
	}

	public List<Vertex<T>> getNeighbors(Vertex<T> x) {
		List<Vertex<T>> neigh = new ArrayList<>();
		AdjVertex<T> from = (AdjVertex<T>) x;
		List<Edge<T>> adj = from.getAdjList();
		for (int i = 0; i < adj.size(); i++) {
			neigh.add(adj.get(i).getDestination());
		}
		return neigh;
	}

	public boolean areAdjacent(Vertex<T> x, Vertex<T> y) {
		return getNeighbors(x).contains(y);
	}

	public boolean isInGraph(T value) {
		return searchVertex(value) != null;
	}

	public double getEdgeWeight(Vertex<T> x, Vertex<T> y) {
		double w = 0;
		if (isInGraph(x.getValue()) && isInGraph(y.getValue())) {
			AdjVertex<T> from = (AdjVertex<T>) x;
			AdjVertex<T> to = (AdjVertex<T>) y;
			Edge<T> e = from.findEdge(to);
			if (e != null)
				w = e.getWeight();
		}
		return w;
	}

	public void setEdgeWeight(Vertex<T> x, Vertex<T> y, double w) {
		if (isInGraph(x.getValue()) && isInGraph(y.getValue()) && weighted) {
			AdjVertex<T> from = (AdjVertex<T>) x;
			AdjVertex<T> to = (AdjVertex<T>) y;
			Edge<T> e = from.findEdge(to);
			if (e != null)
				e.setWeight(w);

			if (!isDirected()) {
				e = to.findEdge(from);
				if (e != null)
					e.setWeight(w);
			}
		}
	}

	public AdjVertex<T> searchVertex(T value) {
		return map.get(value);
	}

	public int getIndexOf(Vertex<T> v) {
		int index = -1;
		boolean searching = true;
		for (int i = 0; i < vertices.size() && searching; i++) {
			if (vertices.get(i) == v) {
				index = i;
				searching = false;
			}
		}
		return index;
	}

	public void bfs(Vertex<T> x) {
		AdjVertex<T> s = (AdjVertex<T>) x;
		for (Vertex<T> u : vertices) {
			u.setColor(Vertex.WHITE);
			u.setDistances(INF);
			u.setPred(null);
		}
		s.setColor(Vertex.GRAY);
		s.setDistances(0);
		s.setPred(null);
		Queue<AdjVertex<T>> q = new LinkedList<>();
		q.offer(s);
		while (!q.isEmpty()) {
			AdjVertex<T> u = q.poll();
			for (int i = 0; i < u.getAdjList().size(); i++) {
				AdjVertex<T> v = (AdjVertex<T>) u.getAdjList().get(i).getDestination();
				if (v.getColor() == Vertex.WHITE) {
					v.setColor(Vertex.GRAY);
					v.setDistances(u.getDistances() + 1);
					v.setPred(u);
					q.offer(v);
				}
			}
			u.setColor(Vertex.BLACK);
		}
	}

	public void dfs() {
		for (Vertex<T> u : vertices) {
			u.setColor(Vertex.WHITE);
			u.setPred(null);
		}
		int time = 0;
		for (Vertex<T> u : vertices) {
			if (u.getColor() == Vertex.WHITE)
				time = dfsVisit((AdjVertex<T>) u, time);
		}
	}

	private int dfsVisit(AdjVertex<T> u, int time) {
		time++;
		u.setDistances(time);
		u.setColor(Vertex.GRAY);
		for (int i = 0; i < u.getAdjList().size(); i++) {
			AdjVertex<T> v = (AdjVertex<T>) u.getAdjList().get(i).getDestination();
			if (v.getColor() == Vertex.WHITE) {
				v.setPred(u);
				time = dfsVisit(v, time);
			}
		}
		u.setColor(Vertex.BLACK);
		time++;
		u.setF(time);
		return time;
	}


	private void initSingleSource(AdjVertex<T> s) {
		for (Vertex<T> u : vertices) {
			u.setDistances(INF);
			u.setPred(null);
		}
		s.setDistances(0);
	}

	public ArrayList<Integer> dijkstra(Vertex<T> x, double w) {

		ArrayList<Integer> a= new ArrayList<Integer>();
		AdjVertex<T> s = (AdjVertex<T>) x;
		initSingleSource(s);
		PriorityQueue<AdjVertex<T>> queue = new PriorityQueue<>();
		queue.add(s);
		while (!queue.isEmpty()) {
			AdjVertex<T> u = queue.poll();
			for (Edge<T> e : u.getAdjList()) {
				AdjVertex<T> v = (AdjVertex<T>) e.getDestination();
				double weight = e.getWeight();
				if(weight == w) {
					for (int i = 0; i < edgeCount.size(); i++) {
						System.out.println("si1");
						if(e==edgeCount.get(i)) {
							edgeCount.get(i).setTimes(1);
						}
					}
				}
				double distanceFromU = u.getDistances() + weight;
				if (distanceFromU < v.getDistances()) {
					queue.remove(v);
					v.setDistances(distanceFromU);
					v.setPred(u);
					queue.add(v);
				}
			}
		}
		
		return a;
	}

	public double[][] floydwarshall() {
		double[][] weights = getWeightsMatrix();
		for (int k = 0; k < vertices.size(); k++) {
			for (int i = 0; i < vertices.size(); i++) {
				for (int j = 0; j < vertices.size(); j++) {
					weights[i][j] = Math.min(weights[i][j], weights[i][k] + weights[k][j]);
				}
			}
		}
		return weights;
	}

	private double[][] getWeightsMatrix() {
		double[][] weights = new double[vertices.size()][vertices.size()];
		for (int i = 0; i < weights.length; i++) {
			Arrays.fill(weights[i], INF);
		}
		for (int i = 0; i < vertices.size(); i++) {
			weights[i][i] = 0;
			AdjVertex<T> u = (AdjVertex<T>) vertices.get(i);
			for (Edge<T> e : u.getAdjList()) {
				AdjVertex<T> v = (AdjVertex<T>) e.getDestination();
				double weight = e.getWeight();
				weights[i][getIndexOf(v)] = weight;
				//int name = e.getName();
				//int index = index(name);
				//edgeCount.get(index).setWeight(edgeCount.get(index).getWeight()+1);
			}
		}
		return weights;
	}
	


	public void prim(Vertex<T> s) {
		AdjVertex<T> r = (AdjVertex<T>) s;
		for (Vertex<T> u : vertices) {
			u.setDistances(INF);
			u.setColor(Vertex.WHITE);
		}
		r.setDistances(0);
		r.setPred(null);
		PriorityQueue<AdjVertex<T>> queue = new PriorityQueue<>();
		for (Vertex<T> u : vertices) {
			queue.add((AdjVertex<T>) u);
		}
		while (!queue.isEmpty()) {
			AdjVertex<T> u = queue.poll();
			for (Edge<T> e : u.getAdjList()) {
				AdjVertex<T> v = (AdjVertex<T>) e.getDestination();
				if (v.getColor() == Vertex.WHITE && e.getWeight() < v.getDistances()) {
					queue.remove(v);
					v.setDistances(e.getWeight());
					queue.add(v);
					v.setPred(u);
				}
			}
			u.setColor(Vertex.BLACK);
		}
	}

	public ArrayList<Edge<T>> kruskal() { // Adapted from
											// www.geeksforgeeks.org/kruskals-minimum-spanning-tree-algorithm-greedy-algo-2/
		ArrayList<Edge<T>> result = new ArrayList<>(); // Tnis will store the resultant MST
		int e = 0; // An index variable, used for result[]
		int i = 0; // An index variable, used for sorted edges

		ArrayList<Edge<T>> edges = getEdges();

		// Step 1: Sort all the edges in non-decreasing order of their
		// weight. If we are not allowed to change the given graph, we
		// can create a copy of array of edges
		Collections.sort(edges);

		UnionFind uf = new UnionFind(vertices.size());

		i = 0; // Index used to pick next edge

		// Number of edges to be taken is equal to V-1
		while (e < vertices.size() - 1 && i < edges.size()) {
			// Step 2: Pick the smallest edge. And increment
			// the index for next iteration
			Edge<T> edge = edges.get(i);
			i++;

			int x = uf.find(getIndexOf(edge.getSource()));
			int y = uf.find(getIndexOf(edge.getDestination()));

			// If including this edge does't cause cycle,
			// include it in result and increment the index
			// of result for next edge
			if (x != y) {
				result.add(edge);
				e++;
				uf.union(x, y);
			}
			// Else discard the edge
		}
		return result;
	}

	public ArrayList<Edge<T>> getEdges() {
		ArrayList<Edge<T>> edges = new ArrayList<>();
		for (int i = 0; i < vertices.size(); i++) {
			AdjVertex<T> v = (AdjVertex<T>) vertices.get(i);
			for (int j = 0; j < v.getAdjList().size(); j++) {
				edges.add(v.getAdjList().get(j));
			}
		}
		return edges;
	}

}