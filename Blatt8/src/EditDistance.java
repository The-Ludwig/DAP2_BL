
public class EditDistance {
	
	static int[][] D;
	static String a, b; 
	
	
	public static int[][] distance(String a, String b){
		
		int n = a.length() + 1;
		int m = b.length() + 1;
		
		D = new int[n][m];
		D[0][0] = 0;
		
		for (int i = 1; i < n; i++) {
			D[i][0]= i;
		}
		for (int j = 1; j < m; j++) {
			D[0][j]= j;
		}
		
		
		for (int i = 1; i < n; i++) {
			for (int j = 1; j < m; j++) {
					D[i][j] = min(D[i-1][j-1] + (a.charAt(i -1) == b.charAt(j -1) ? 0 : 1), D[i][j-1] + 1, D[i-1][j] + 1) ;
			}	
		}
		
		for (int i = 1; i < n; i++) {
			for (int j = 1; j < m; j++) {
				System.out.print(D[i][j]);
			}	
			System.out.println("");
		}
		
		return D;
	}

	private static int min(int a, int b, int c){
		if (a <= b  && a <= c)
			return a;
		else if (b <= a && b<= c)
			return b;
		else
			return c;
	}
	
	public static void printEditOperations()
	{
		System.out.println("Loesung fuer \" "+a+"\" --> \""+b+"\" mit Gesamtkosten "+D[a.length()][b.length()]+":"); 
		System.out.println("====================================================================================================");
		
		String edit = ""; 
		
		//TODO: Backtracing
	}
	
	public static int backTracingAusgabe(int[][] D, int a, int b, String sA, String sB){
		if (a == 0 && b == 0)
			return 1;
		
		if ( a < 1){
			int schritt =backTracingAusgabe(D, a, b-1, sA, sB);
			System.out.println(schritt + ") Kosten 1: Fuege " + sB.charAt(b-1) + " an Position " + a + " ein --> " );
			return schritt + 1;
		} 
		
		if (b < 1){
			int schritt = backTracingAusgabe(D, a-1, b, sA, sB);
			System.out.println(schritt + ") Kosten 1: Loesche " + sA.charAt(a-1) + " an Position " + a + " --> " );
			return schritt + 1;
		}
		
		if (sA.charAt(a-1) == sB.charAt(b-1) && D[a][b] == D[a-1][b-1]){
			int schritt = backTracingAusgabe(D, a-1, b-1, sA, sB);
			System.out.println(schritt + ") Kosten 0: " + sA.charAt(a-1) + " an Position " + a + " --> " );
			return schritt + 1;
		} else{
			if (D[a][b] == D[a-1][b-1] + 1){
				int schritt =backTracingAusgabe(D, a, b-1, sA, sB);
				System.out.println(schritt + ") Kosten 1: Ersetze " + sA.charAt(a-1) + " an Position " + a + " mit " + sB.charAt(b-1) + " --> " );
				return schritt + 1;
			}
			else if (D[a][b] == D[a][b-1] + 1){
				int schritt =backTracingAusgabe(D, a, b-1, sA, sB);
				System.out.println(schritt + ") Kosten 1: Fuege " + sB.charAt(b-1) + " an Position " + a + " ein --> " );
				return schritt + 1;
			}
			else if (D[a][b] == D[a-1][b] + 1){
				int schritt =backTracingAusgabe(D, a-1, b, sA, sB);
				System.out.println(schritt + ") Kosten 1: Loesche " + sA.charAt(a-1) + " an Position " + a + " --> " );
				return schritt + 1;
			}
		}	
		return -1;
	}
	
}

