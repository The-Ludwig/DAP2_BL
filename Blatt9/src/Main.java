

public class Main {
	
	public static void main(String[] args) {
		SearchTree myTree = new SearchTree(13,17,5,3,-10,100,40,-5,4,12,11);
	
		myTree.inOrder();
		myTree.preOrder();
		myTree.postOrder(); 
		
		if(args.length != 3) {
			System.out.println("java Main p W n");
			return;
		}
		int p, W, n;
		
		
		try{
			p = Integer.parseInt(args[0]);
			W = Integer.parseInt(args[1]);
			n = Integer.parseInt(args[2]);
		}catch(NumberFormatException e) {
			System.out.println("Nan");
			return;
		}
		
		ruckSackProblem rsp = new ruckSackProblem(W, p, n); 
		
		double t1 = System.currentTimeMillis();
		System.out.println(rsp.greedie());
		System.out.println("Needed Time for Greedie: "+ (System.currentTimeMillis() - t1)/1000 +"s" );
		
		t1 = System.currentTimeMillis(); 
		System.out.println(rsp.dynamisch());
		System.out.println("Needed Time for Dynamic: "+ (System.currentTimeMillis() - t1)/1000 +"s" );
	}
}
