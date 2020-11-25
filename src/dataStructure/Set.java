package dataStructure;

public class Set<E extends Comparable<E>> implements Comparable<Set<E>>{
	
	private Vertex<E> head;
	private Vertex<E> tail;
	
	public Set(E first) {
		super();
		Vertex<E> toAdd = new Vertex<E>(first);
		head = toAdd;
		tail = toAdd;
	}

	public Vertex<E> getHead() {
		return head;
	}

	public void setHead(Vertex<E> head) {
		this.head = head;
	}

	public Vertex<E> getTail() {
		return tail;
	}

	public void setTail(Vertex<E> tail) {
		this.tail = tail;
	}

	@Override
	public int compareTo(Set<E> arg0) {
		return head.compareTo(arg0.getHead());
	}
	
	public boolean findSet(E toFind) {
		boolean finded = false;
		Vertex<E> next = head;
		while(next != null) {
			if(next.getElement().equals(toFind)) {
				finded = true;
				next = null;
			} else {
				next = next.getNext();
			}
		}
		return finded;
	}
	
	public void union(Set<E> theSet) {
		tail.setNext(theSet.getHead());
		tail = theSet.getTail();
	}


}