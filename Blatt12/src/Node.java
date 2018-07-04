import java.util.ArrayList;

public class Node{
		ArrayList<Edge> edges;
		int id;
		
		public Node(int pid){
			id = pid;
			edges = new ArrayList<Edge>();
		}

		
		public void addEdge(Node dst){
			edges.add(new Edge(this, dst));
		}
		
		public boolean equals(Node other){
			if(this.id == other.getId())
				return true;
			else
				return false;
		}
		
		public ArrayList<Edge> getEdges() {
			return edges;
		}

		public int getId() {
			return id;
		}


		@Override
		public String toString() {
			return "Node [id=" + id +"]";
		}


	}