package dataStructure;

import java.util.List;


public interface IGraph<E extends Comparable<E>> {
	
	public void add(E toAdd);
	public void delete(E toDelete);
	public void BFS(E origin);
	public void DFS();
	public int prim(E origin);
	public List<Edge<E>> Kruskal();
	public void Dijkstra(E origin);
	public long[][] floydWarshall();
	public Vertex<E> find();
	
}
