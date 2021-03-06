
/**
 * 
 * @author Stames
 *
 */
public class SearchTree {
	
	private Node root;
	
	/**
	 * 
	 * @param nodes
	 */
	public SearchTree(int... nodes) {
		this.root = null;
		for (int i = 0; i < nodes.length; i++) {
			this.einfuegen(nodes[i]);
		}
	}
	
	/**
	 * 
	 * @param value
	 */
	public void einfuegen(int value){
		this.einfuegen(value, this.root);
	}
	
	/**
	 * 
	 * @param value
	 * @param n
	 */
	public void einfuegen(int value, Node n){
		if (this.root == null){
			this.root = new Node(value, null, null, null);
			return;
		}
		
		if (value > n.value)
			if(n.rc == null)
				n.rc = new Node(value, n);
			else
				einfuegen(value, n.rc);
		else if (value < n.value)
			if (n.lc == null)
				n.lc = new Node(value, n);
			else
				einfuegen(value, n.lc);
		else
			System.out.println("Node Bereits im Baum drinne");
	}
	
	/**
	 * 
	 */
	public void preOrder(){
		preOrder(this.root);
		System.out.println();
	}
	
	/**
	 * 
	 * @param n
	 */
	public void preOrder(Node n){
		if (n == null)
			return;
		else{
			System.out.print(n.value+ ", ");
			preOrder(n.lc);
			preOrder(n.rc);
		}	
	}
	
	/**
	 * 
	 */
	public void inOrder() {
		inOrder(this.root);
		System.out.println();
	}
	
	/**
	 * 
	 * @param n
	 */
	public void inOrder(Node n){
		if (n == null)
			return;
		else{
			inOrder(n.lc);
			System.out.print(n.value + ", ");
			inOrder(n.rc);
		}	
	}
	
	/**
	 * 
	 */
	public void postOrder() {
		postOrder(this.root);
		System.out.println();
	}
	
	/**
	 * 
	 * @param n
	 */
	public void postOrder(Node n){
		if (n == null)
			return;
		else{
			postOrder(n.lc);
			postOrder(n.rc);
			System.out.print(n.value + ", ");
		}	
	}
	
	
	/**
	 * 
	 * @author Stames
	 *
	 */
	private class Node{
		int value;
		Node lc;
		Node rc;
		Node pa;
		
		/**
		 * 
		 * @param pValue
		 * @param pPa
		 */
		public Node(int pValue, Node pPa){
			this(pValue, null, null, pPa);
		}
		
		/**
		 * 
		 * @param pValue
		 * @param pLc
		 * @param pRc
		 * @param pPa
		 */
		public Node(int pValue, Node pLc, Node pRc, Node pPa){
			this.value = pValue;
			this.lc = pLc;
			this.rc = pRc;
			this.pa = pPa;
		}
	}
}
