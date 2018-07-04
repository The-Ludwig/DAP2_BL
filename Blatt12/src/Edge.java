public class Edge{
		Node src;
		Node dst;
		
		public Edge(Node pa, Node pb){
			src = pa;
			dst = pb;
		}

		public Node getSrc() {
			return src;
		}

		public Node getDst() {
			return dst;
		}

		@Override
		public String toString() {
			return "Edge [src=" + src + ", dst=" + dst + "]";
		}
		
		
	}