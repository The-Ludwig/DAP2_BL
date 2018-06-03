
public class Main {

	public static void main(String[] args) {
		int schritte = -1; 
		if(args.length() >= 2)
			schritte = EditDistance.distance(args[0], args[1]);
		
		if(args.length == 3 && args[2].equals("-o") && schritte != -1)
			EditDistance.printEditOperations(); 
		else if(args.length() == 2 && schritte != -1)
			System.out.println(schritte);
		else
			System.out.println("Usage: java Main arg1 arg2 [-0]");
	}
}
