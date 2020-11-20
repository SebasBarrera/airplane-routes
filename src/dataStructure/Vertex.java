package dataStructure;

import java.util.ArrayList;

public class Vertex<E extends Comparable<E>> {
	
	public final static int WHITE = 0;
	public final static int GRAY = 1;
	public final static int BLACK = 2;
	
	private int color;
	private int distance;
	private Vertex<E> prev;
	private ArrayList<Vertex<E>> v;
	private double weight;
	private E element;
	private int timeStamp1;
	private int timeStamp2;
	
	/**
	 * @param color
	 * @param distance
	 * @param prev
	 * @param element
	 */
	public Vertex(E element) {
		super();
		color = WHITE;
		distance = Integer.MIN_VALUE;
		this.element = element;
	}
	/**
	 * @return the color
	 */
	public int getColor() {
		return color;
	}
	/**
	 * @param color the color to set
	 */
	public void setColor(int color) {
		this.color = color;
	}
	/**
	 * @return the distance
	 */
	public int getDistance() {
		return distance;
	}
	/**
	 * @param distance the distance to set
	 */
	public void setDistance(int distance) {
		this.distance = distance;
	}
	/**
	 * @return the prev
	 */
	public Vertex<E> getPrev() {
		return prev;
	}
	/**
	 * @param prev the prev to set
	 */
	public void setPrev(Vertex<E> prev) {
		this.prev = prev;
	}
	/**
	 * @return the element
	 */
	public E getElement() {
		return element;
	}
	/**
	 * @param element the element to set
	 */
	public void setElement(E element) {
		this.element = element;
	}
	/**
	 * @return the timeStamp1
	 */
	public int getTimeStamp1() {
		return timeStamp1;
	}
	/**
	 * @param timeStamp1 the timeStamp1 to set
	 */
	public void setTimeStamp1(int timeStamp1) {
		this.timeStamp1 = timeStamp1;
	}
	/**
	 * @return the timeStamp2
	 */
	public int getTimeStamp2() {
		return timeStamp2;
	}
	/**
	 * @param timeStamp2 the timeStamp2 to set
	 */
	public void setTimeStamp2(int timeStamp2) {
		this.timeStamp2 = timeStamp2;
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