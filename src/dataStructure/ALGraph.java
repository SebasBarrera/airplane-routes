package dataStructure;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class ALGraph<E extends Comparable<E>> implements IGraph<E >{
	
	private List<VertexList<E>> vertexs;
	private List<Edge<E>> edges;
	private int time;
	private List<Set<E>> sets;

	public ALGraph() {
		vertexs = new ArrayList<VertexList<E>>();
		sets = new ArrayList<Set<E>>();
		edges = new ArrayList<Edge<E>>();
	}
	
	public List<VertexList<E>> getVertexs() {
		return vertexs;
	}
	
	public void setVertexs(List<VertexList<E>> vertexs) {
		this.vertexs = vertexs;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public List<Set<E>> getSets() {
		return sets;
	}

	public void setSets(List<Set<E>> sets) {
		this.sets = sets;
	}

	public List<Edge<E>> getEdges() {
		return edges;
	}

	public void setEdges(List<Edge<E>> edges) {
		this.edges = edges;
	}

	@Override
	public void add(E toAdd) {
		VertexList<E> theVertex = new VertexList<E>(toAdd);
		vertexs.add(theVertex); 
	}

	@Override
	public void delete(E toDelete) {
		for(int i = 0; i < vertexs.size(); i++) {
			if(vertexs.get(i).getObject().equals(toDelete)) {
				vertexs.remove(i);
			} else {
				vertexs.get(i).deleteAdjacent(toDelete);
			}
		}
		for(int i = 0; i < edges.size(); i++) {
			if(edges.get(i).getFirst().equals(toDelete) || edges.get(i).getSecond().equals(toDelete)) {
				edges.remove(i);
			}
		}
	}
 
	@Override

	public void BFS(E origin) {
		Queue<VertexList<E>> q = new LinkedList<VertexList<E>>();
		for(int i = 0; i < vertexs.size(); i++) {
			if(vertexs.get(i).getObject().equals(origin)) {
				vertexs.get(i).setColor("GRAY");
				vertexs.get(i).setDistance(0);
				vertexs.get(i).setPredecessor(null);
				q.offer(vertexs.get(i));
			} else {
				vertexs.get(i).setColor("WHITE");
				vertexs.get(i).setDistance(Integer.MAX_VALUE);
				vertexs.get(i).setPredecessor(null);
			}
		}
		while(!q.isEmpty()) {
			VertexList<E> u = q.poll();
			List<Adjacent<E>> theAdjacents = u.getAdjacents();
			for(int i = 0; i < theAdjacents.size(); i++) {
				if(theAdjacents.get(i).getVertex().getColor().equals("WHITE")) {
					theAdjacents.get(i).getVertex().setColor("GRAY");
					theAdjacents.get(i).getVertex().setDistance(u.getDistance()+1);
					theAdjacents.get(i).getVertex().setPredecessor(u);
					q.offer(theAdjacents.get(i).getVertex());
					
				}
			}
			u.setColor("BLACK");
		}
	}

	@Override
	public void DFS() {
		for(int i = 0; i < vertexs.size(); i++) {
			vertexs.get(i).setColor("WHITE");
			vertexs.get(i).setPredecessor(null);
		}
		time = 0;
		for(int i = 0; i < vertexs.size(); i++) {
			if(vertexs.get(i).getColor().equals("WHITE")) {
				DFSVisit(vertexs.get(i));
			}
		}
	}

	public void DFSVisit(VertexList<E> u) {
		time++;
		u.setDistance(time);
		u.setColor("GRAY");
		List<Adjacent<E>> adjacents = u.getAdjacents();
		for(int i = 0; i < adjacents.size(); i++) {
			if(adjacents.get(i).getVertex().getColor().equals("WHITE")) {
				adjacents.get(i).getVertex().setPredecessor(u);
				DFSVisit(adjacents.get(i).getVertex());
			}
		}
		u.setColor("BLACK");
		time++;
		u.setF(time);
	} 
	
	@Override
	public int prim(E node) {
		int cost = 0;
		VertexList<E> firstNode = null;
		boolean finded = false;
		for(int i = 0; i < vertexs.size() && !finded; i++) {
			if(vertexs.get(i).getObject().equals(node)) {
				firstNode = vertexs.get(i);
				finded = true;
			}
		}
		List<VertexList<E>> addVertexVisited = new ArrayList<>();
		List<Adjacent<E>> noVisited = new ArrayList<>();
		addVertexVisited.add(firstNode);
		System.out.println("aqui");
		for(int i = 0; i < firstNode.getAdjacents().size();i++) {
			noVisited.add(firstNode.getAdjacents().get(i));
		}
		int m = 1;
		boolean t = false;
		while(!noVisited.isEmpty() && !t) {			
				Adjacent<E>  temp = compareWeight(noVisited);
				if(temp != null) {
				if(!existInList(temp,addVertexVisited)) {
					addVertexVisited.add(temp.getVertex());
					cost += temp.getWeight();
					m++;
					Adjacent<E> temp1 = temp;
					noVisited.remove(temp1);
					for(int i = 0; i < temp.getVertex().getAdjacents().size();i++) {
						noVisited.add(temp.getVertex().getAdjacents().get(i));
					}

					
				}else {
					noVisited.remove(temp);
				}
		}else {
			t = true;
		}
	}
		return cost;
	}
	
	
	public boolean existInList(Adjacent<E> temp,List<VertexList<E>> arr) {
		boolean exist = false;
		for(int i = 0; i < arr.size() && !exist;i++) {
			if(temp.getVertex() == arr.get(i)) {
				exist = true;
			}
		}
		return exist;
		
	}
	public Adjacent<E> compareWeight(List<Adjacent<E>> m) {
		Adjacent<E>  s = null;
		for(int i = 0; i < m.size();i++) {
			for(int j = 0; j < m.size()-1-i;j++) {
				if(m.get(j+1).getWeight() < m.get(j).getWeight()) {
					s = m.get(j+1);
				}else if(m.get(j).getWeight() < m.get(j+1).getWeight()) {
					s = m.get(j);
				}
			}
		}
		return s; 
	}
	

	@Override
	public List<Edge<E>> Kruskal() {
		List<Edge<E>> a = new ArrayList<Edge<E>>();
		for(int i = 0; i < vertexs.size(); i++) {
			makeSet(vertexs.get(i).getObject());
		}
		sortEdges();
		for(int i = 0; i < edges.size(); i++) {
			if(!findSet(edges.get(i).getFirst()).equals(findSet(edges.get(i).getSecond()))) {
				a.add(edges.get(i));
				union(edges.get(i).getFirst(), edges.get(i).getSecond());
			}
		}
		return a;
	}
	
	public void sortEdges() {
		for(int i = edges.size(); i > 0; i--) {
			for(int j = 0; j < i-1; j++) {
				if(edges.get(j).getWeight() > edges.get(j+1).getWeight()) {
					Edge<E> temp = edges.get(j);
					edges.set(j, edges.get(j+1));
					edges.set(j+1, temp);
				}
			}
		}
	}

	public void connect(E one, E two, int weight) {
		Edge<E> toAdd = new Edge<E>(one, two, weight);
		edges.add(toAdd);
		VertexList<E> v1 = null;
		VertexList<E>  v2 = null;
		for(int i = 0; i < vertexs.size(); i++) {
			if(vertexs.get(i).getObject().equals(one)) {
				v1 = vertexs.get(i);
			} else if(vertexs.get(i).getObject().equals(two)){
				v2 = vertexs.get(i);
			}
		}
		Adjacent<E> a1 = new Adjacent<E>(v1, weight);
		Adjacent<E> a2 = new Adjacent<E>(v2, weight);
		v1.getAdjacents().add(a2);
		v2.getAdjacents().add(a1);
	} 
	
	public String printPath(E originO, E destinyO) {
		VertexList<E> origin = null;
		VertexList<E> destiny = null;
		for(int i = 0; i < vertexs.size(); i++) {
			if(vertexs.get(i).getObject().equals(originO)) {
				origin = vertexs.get(i);
			} if(vertexs.get(i).getObject().equals(destinyO)) {
				destiny = vertexs.get(i);
			}
		}
		if(destiny.equals(origin)) {
			return origin.toString();
		} else if(destiny.getPredecessor() == null) {
			return "There is not path";
		} else {
			return printPath(originO, destiny.getPredecessor().getObject())+" -> " + destiny.toString();
		}
	}

	@Override
	public void Dijkstra(E origin) {
		PriorityQueue<VertexList<E>> pq = new PriorityQueue<VertexList<E>>();
		for(int i = 0; i < vertexs.size(); i++) {
			if(vertexs.get(i).getObject().equals(origin)) {
				vertexs.get(i).setDistance(0);
			} else {
				vertexs.get(i).setDistance(Integer.MAX_VALUE);
			}
			vertexs.get(i).setPredecessor(null);
			pq.add(vertexs.get(i));
		}
		while(!pq.isEmpty()) {
			VertexList<E> u = pq.poll();
			List<Adjacent<E>> adjacents = u.getAdjacents();
			for(int i = 0; i < adjacents.size(); i++) {
				int alt = u.getDistance() + adjacents.get(i).getWeight();
				if(alt < adjacents.get(i).getVertex().getDistance()) {
					adjacents.get(i).getVertex().setDistance(alt);
					adjacents.get(i).getVertex().setPredecessor(u);
					pq.remove(adjacents.get(i).getVertex());
					pq.add(adjacents.get(i).getVertex());
				}
			}
		}
	}

	public void makeSet(E toAdd) {
		Set<E> s = new Set<E>(toAdd);
		sets.add(s);
	}

	public void union(E one, E two) {
		Set<E> firstSet = findSet(one);
		Set<E> secondSet = findSet(two);
		if(firstSet.compareTo(secondSet) < 0) {
			firstSet.union(secondSet);
		} else {
			secondSet.union(firstSet);
		}
	}

	public Set<E> findSet(E toFind) {
		Set<E> t = null;
		boolean finded = false;
		for(int i = 0; i < sets.size() && !finded; i++) {
			if(sets.get(i).findSet(toFind)) {
				t = sets.get(i);
				finded = true;
			}
		}
		return t;
	}

	@Override
	public long[][] floydWarshall() {
		long[][] min = new long[vertexs.size()][vertexs.size()];
		for(int i = 0; i < vertexs.size(); i++) {
			for(int j = 0; j < vertexs.size(); j++) {
				if(i == j) {
					min[i][j] = 0;
				} else {
					min[i][j] = vertexs.get(i).weight(vertexs.get(j));
				}
			}
		}
		for(int k = 0; k < vertexs.size(); k++) {
			for(int i = 0; i < vertexs.size(); i++) {
				for(int j = 0; j < vertexs.size(); j++) {
					if(min[i][j] > min[i][k] + min[k][j]) {
						min[i][j] = min[i][k] + min[k][j];
					}
				}
			}
		}
		return min;
	}

}