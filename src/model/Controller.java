package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import dataStructure.LGraph;

public class Controller {

	public final static String AIRPORTS = "data/aeropuertos.csv";
	public final static String FLIGHTS = "data/vuelos.csv";
	
	private String[] cities;
	private String[] airports;
	private String[][] flights;
	private double[] prices;
	private LGraph<String> g;
	
	public Controller() {
		g = new LGraph<String>(true, true); 
	}
	
	public void loadFromFilesAirports() throws IOException {
		FileReader br = new FileReader(new File(AIRPORTS));
		BufferedReader bw = new BufferedReader(br);
		String line = bw.readLine();
		int i = 0;
		while (line != null) {
			String[] data = line.split(",");
			cities[i]  = data[0];
			airports[i] = data[1];
			i++;
			line = bw.readLine();
		}
		br.close();
		bw.close();
	}
	
	public void loadFromFilesFlights() throws IOException {
		FileReader br = new FileReader(new File(FLIGHTS));
		BufferedReader bw = new BufferedReader(br);
		String line = bw.readLine();
		int i = 0;
		while (line != null) {
			String[] data = line.split(",");
			flights[i][0] = data[0]; //from
			flights[i][1] = data[1]; // to
			prices[i]	  = Double.parseDouble(data[1]);
			i++;
			line = bw.readLine();
		}
		br.close();
		bw.close();
	}
	
	public void addAirports() {
		for (int i = 0; i < airports.length; i++) {
			g.addVertex(airports[i]);
		}
	}
	
	public void addFlights() {
		for (int i = 0; i < flights.length; i++) {
			g.addEdge(g.searchVertex(flights[i][0]), g.searchVertex(flights[i][1]), prices[i]);
		}
	}
	
	public String searchFlight(String airport1, String airport2) {
		String msg = "";

		return msg;
	}
	

}
