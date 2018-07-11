import java.io.IOException;
import java.util.ArrayList;

public class GraphTest {

	/**
	 * Loest das SSSP-Proplem mit Hilfe des Algortihmus von Bellman-Ford.
	 * @param g der Graph
	 * @param source id des Startknotes
	 * @return Array mit Weglaengen; Element i gibt die Laenge eines kuerzesten
	 * Weges von dem Knoten mit der id source zu dem Knoten mit id i an
	 */
	public static double[] sssp(Graph g, int source) {
		
		ArrayList<Node> nodes = g.getNodes();
		ArrayList<Edge> edges = g.getEdges();
		double[] kuerzesteWege = new double[nodes.size()];
		
		for (int i = 0; i < kuerzesteWege.length; i++) {
			kuerzesteWege[i] = Double.POSITIVE_INFINITY;
		}
		
		kuerzesteWege[source] = 0;
		
		for (int i = 0; i < kuerzesteWege.length - 1; i++) {
			for (Node v : nodes) {
				for (Edge d : edges) {
					if (d.getDest() == v){
						if(kuerzesteWege[d.getSource().getID()] + d.getCost() < kuerzesteWege[v.getID()])
							kuerzesteWege[v.getID()] = kuerzesteWege[d.getSource().getID()] + d.getCost();
					}
				}
			} 
		}
		return kuerzesteWege;
	}
	
	


	/**
	 * Loest das APSP-Problem mit Hilfe des Algorithmus von Floyd-Warshall 
	 * @param g der Graph
	 * @return Matrix mit Weglaengen; Element (i,j) gibt die Laenge eines 
	 * kuerzesten Weges von dem Knoten mit der id i zu dem Knoten mit id j an 
	 */
	public static double[][] apsp(Graph g) {
		ArrayList<Node> nodes = g.getNodes();
		ArrayList<Edge> edges = g. getEdges();
		double[][][] kuerzesteWege = new double[nodes.size()][nodes.size()][nodes.size()];
		
		for (int i = 0; i < kuerzesteWege.length; i++) {
			for (int j = 0; j < kuerzesteWege.length; j++) {
				for (int j2 = 0; j2 < kuerzesteWege.length; j2++) {	
					if (i != j)
						kuerzesteWege[j2][i][j] = Double.POSITIVE_INFINITY;
					else
						kuerzesteWege[j2][i][j] = 0;
				}
			}
		}
		
		for (Edge d : edges) {
			kuerzesteWege[0][d.getSource().getID()][d.getDest().getID()] = d.getCost();
		}
		
		
		for (int k = 1; k < kuerzesteWege.length; k++) {
			for (int i = 0; i < kuerzesteWege.length; i++) {
				for (int j = 0; j < kuerzesteWege.length; j++) {
					kuerzesteWege[k][i][j] = min(kuerzesteWege[k-1][i][j], kuerzesteWege[k-1][i][k] + kuerzesteWege[k-1][k][j]);
				}
			}
		}
		
		
		
		double[][] ergebnis = kuerzesteWege[nodes.size() - 1];
		
		return ergebnis;
		
	}

	static double min(double a, double b){
		if(a < b)
			return a;
		else
			return b;
	}
	
	
	/**
 	 * Realisiert einen APSP-Algorithmus, indem fuer alle Knoten das 
 	 * SSSP-Problem mittels Bellman-Ford geloest wird.
 	 * @param g der Graph
	 * @return Matrix mit Weglaengen; Element (i,j) gibt die Laenge eines 
	 * kuerzesten Weges von dem Knoten mit der id i zu dem Knoten mit id j an 
	 */
	public static double[][] apspBellmanFord(Graph g) {
		// Knoten holen
		ArrayList<Node> nodes = g.getNodes();
		// Tabelle anlegen
		double[][] result = new double[nodes.get(nodes.size()-1).getID()+1][];
		for (Node n : nodes){
			// Bellman-Ford fuer jeden Knoten
			result[n.getID()] = sssp(g, n.getID());
		}
		return result;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		if (args.length < 1) {
			System.err.println("Syntax: java GraphTest <filename> [<nodenumber>]");
			System.exit(-1);
		}
		
		Graph g = Graph.fromFile(args[0]);
		if (g==null) {
			System.err.println("Konnte Datei "+args[0]+ " nicht oeffnen oder enthaelt keinen Graphen");
			System.exit(1);
		}
		if (g.getNodes().isEmpty()) {
			System.err.println("Leerer Graph.");
			System.exit(2);
		}
		
		if (args.length == 2) {
			// Fuehre Bellman-Ford-Algorithmus aus
			int nodenumber=-1;
			try {
				nodenumber=Integer.parseInt(args[1]);
			} catch (NumberFormatException ex) {
			}
			if (g.getNode(nodenumber) == null) {
				System.err.println("Ungueltiger Startknoten angegeben: "+args[1]);
				System.exit(1);
			}
			double[] minCost = sssp(g, nodenumber);
			ArrayList<Node> nodes = g.getNodes();
			Node s = g.getNode(nodenumber), e = s;
			double maxDist = 0d;
			for (Node n : nodes){
				if (nodes.size()<= 20){
					System.out.println("Abstand von Knoten " + n.getID() + " zu Knoten " + s.getID() + ": " + minCost[n.getID()]);
				}
				if (minCost[n.getID()] != Double.POSITIVE_INFINITY && minCost[n.getID()] > maxDist){
					maxDist = minCost[n.getID()];
					e = n;
				}
			}
			System.out.println("Der maximale Abstand ist " + maxDist + " fuer Knoten " + e.getID());
		} else {
			// Fuehre Floyd-Warshall-Algorithmus aus
			double startApsp = System.currentTimeMillis();
			double[][] minCost=apsp(g);
			double endApsp = System.currentTimeMillis();
			double zeitApsp = endApsp - startApsp;
			
			double startApspBell = System.currentTimeMillis();
			double[][] minCostBellman=apspBellmanFord(g);
			double endApspBell = System.currentTimeMillis();
			double zeitApspBell = endApspBell - startApspBell;
			
			
			ArrayList<Node> nodes = g.getNodes();
			Node s = nodes.get(0), e = s;
			double maxDist = 0d;
			for (Node u : nodes){
				for (Node v : nodes){
					if (nodes.size()<= 10){
						System.out.print((minCost[u.getID()][v.getID()] == Double.POSITIVE_INFINITY? "\u221E": "" +minCost[u.getID()][v.getID()]) + "\t");
					}
					if (minCost[u.getID()][v.getID()] != Double.POSITIVE_INFINITY && minCost[u.getID()][v.getID()] > maxDist){
						maxDist = minCost[u.getID()][v.getID()];
						s = u;
						e = v;
					}
				}
				if (nodes.size()<= 10) System.out.println();
			}
			System.out.println("Der maximale Abstand ist " + maxDist + " fuer das Knotenpaar (" + s.getID() + ", " + e.getID() + ")");
			
			System.out.println();
			System.out.println();
			System.out.println("apsp hat gebraucht:  " + zeitApsp);
			System.out.println("apspBellmanFord hat gebraucht:  " + zeitApspBell);
		}
		
		
		
	}
}
