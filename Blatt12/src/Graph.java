import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Graph {

	ArrayList<Node> nodes;
	
	public Graph(){
		nodes = new ArrayList<Node>();
	}
	
	public boolean contains (int pid){
		for (Node node : nodes) {
			if (node.getId() == pid)
				return true;
		}
		
		return false;
	}
	
	public void addNode(int pid){
		if (contains(pid))
			return;
		else
			nodes.add(new Node(pid));
	}
	
	public Node getNode(int pid){
		for (Node node : nodes) {
			if (node.getId() == pid)
				return node;
		}
		return null;
	}
	
	public void addEdge(int src, int dst){
		if (!contains(src))
			addNode(src);
		if(!contains(dst))
			addNode(dst);
		
		Node a = getNode(src);
		Node b = getNode(dst);
		
		a.addEdge(b);
		b.addEdge(a);
	}
	
	static Graph fromFile(String filepath){
		FileReader fr;
		
		try{
			fr = new FileReader(filepath);
		} catch (IOException e){
			System.out.println("File not found made an empty graph");
			return new Graph();
		}
		
		BufferedReader br = new BufferedReader(fr);
		
		String zeile;
		Graph fileGraph = new Graph();
		
		try{
			while ((zeile = br.readLine()) != null){
				System.out.println(zeile);
				int a = Integer.parseInt(zeile.substring(0, zeile.indexOf(",")));
				int b = Integer.parseInt(zeile.substring(zeile.indexOf(",") + 1, zeile.length()));
				fileGraph.addEdge(a, b);
			}
		}catch(IOException e){
			System.out.println("irgendwas mit IO falsch falsch");
		}catch(NumberFormatException e1){
			System.out.println("nummerpars falsch");
		}
		
		return fileGraph;
	}
	
	public void bfs(int startID){
		
		LinkedList<Node> queue = new LinkedList<Node>();
		ArrayList<Node> weiﬂeknoten = new ArrayList<Node>();
		ArrayList<Integer> distanzen = new ArrayList<Integer>();
		for (Node node : nodes) {
			weiﬂeknoten.add(node);
			distanzen.add(0);
		}
		queue.add(nodes.get(startID));
		weiﬂeknoten.remove(startID);
		
		while (queue.size() > 0){
			Node u = queue.get(0);
			for (Edge edge : u.getEdges()) {
				Node v = edge.getDst();
				if (weiﬂeknoten.contains(v) ){
					weiﬂeknoten.remove(v);
					distanzen.set(nodes.indexOf(v), distanzen.get(nodes.indexOf(u)) + 1);
					queue.add(v);
				}
			}
			queue.remove(0);	
		}
		
		System.out.println(distanzen.toString());
	}
	
	
	@Override
	public String toString() {
		return "Graph [nodes=" + nodes.toString() + "]";
	}

	public static void main(String[] args) {
		Graph meinGraph = Graph.fromFile("BspGraphKlein.graph");
		System.out.println(meinGraph.toString());
		meinGraph.bfs(0);
	}
	
}
