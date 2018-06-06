
public class Main {

	public static void main(String[] args) {
		if(args.length < 2){
			System.out.println("zu wenig argumente");
			return;
		}
		
		String stringA = args[0];
		String stringB = args[1];
		
		int laengeA = stringA.length();
		int laengeB = stringB.length();
		
		int schritte = -1;
		int[][] loesungsMatrix = EditDistance.distance(stringA, stringB);
		
		schritte = loesungsMatrix[laengeA][laengeB];
		
		String[] stringAA = new String[1];
		stringAA[0] = stringA;
		
		if(args.length == 3 && args[2].equals("-o") && schritte != -1) {
			
			System.out.println("Loesung fuer \" "+stringA+"\" --> \""+stringB+"\" mit Gesamtkosten "+loesungsMatrix[stringA.length()][stringB.length()]+":"); 
			System.out.println("====================================================================================================");

			EditDistance.backTracingAusgabe(loesungsMatrix, laengeA, laengeB, stringAA, stringB); 
		}
		else if(args.length == 2 && schritte != -1)
			System.out.println("Minimale Anzahl Editierschritte: "+schritte);
		else
			System.out.println("Usage: java Main arg1 arg2 [-0]");
	}
}
