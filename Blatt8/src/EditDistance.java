/*
 *tolle klasse legen wir ersmal weg die tolle klasse
 * macht string distancen und so
 */
public class EditDistance {
	
	
	/*
	 * berechnet die editierdistanz von zwei strings
	 * 
	 * @param die zu editierenden strings
	 * @return die loesungsmatrix
	 */
	public static int[][] distance(String a, String b){
		
		int n = a.length() + 1;
		int m = b.length() + 1;
		
		int D[][] = new int[n][m];
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
		System.out.print(" #");
		for (int i = 0 ; i < n-1; i++)
			System.out.print(a.charAt(i));
		System.out.print("\n#");

		for (int j = 0; j < m; j++) {
			if (j > 0)
			 System.out.print(b.charAt(j - 1));
			for (int i = 0; i < n; i++) {
				System.out.print(D[i][j]);
			}	
			System.out.println("");
		}
		
		return D;
	}

	/*
	 * berechnet das minimum aus drei integern 
	 * @param int , int b, int c drei ints die verglichen werdne solle 
	 * @return int das minimum aus den drei integern 
	 */
	private static int min(int a, int b, int c){
		if (a <= b  && a <= c)
			return a;
		else if (b <= a && b<= c)
			return b;
		else
			return c;
	}
	
	/*
	 * rekursieves backtracing welches den verlauf der editierung ausgiebt
	 * @param int[][] D die loesungsmatrix der editierung 
	 * @param int a, int b die stelle an welcher wir in der matrix sind
	 * @param String sA[] der zu editierende string im array weil call by reference und so java abfuck!!!
	 * @param String sB der String in den editiert werden soll 
	 * @return int schritte 
	 */
	public static int backTracingAusgabe(int[][] D, int a, int b, String sA[], String sB){
		int i = D.length - 1;
		
		
		if (a == 0 && b == 0) {
			return 1;
		}
		
		if ( a < 1){
			int schritt =backTracingAusgabe(D, a, b-1, sA, sB);
			int offset = sA[0].length() - i;
			System.out.println(schritt + ") Kosten 1: Fuege " + sB.charAt(b-1) + " an Position " + (a + offset) + " ein --> " + (sA[0] = einfuegen(sA[0], sB.charAt(b-1), a-1+ offset)));
			return schritt + 1;
		} 
		
		if (b < 1){
			int schritt = backTracingAusgabe(D, a-1, b, sA, sB);
			int offset = sA[0].length() - i;
			System.out.println(schritt + ") Kosten 1: Loesche " + sA[0].charAt(a-1+ offset) + " an Position " + (a+ offset) + " --> " + (sA[0] = loesche(sA[0], a-1+ offset)));
			return schritt + 1;
		}
		
		if (sA[0].charAt(a-1) == sB.charAt(b-1) && D[a][b] == D[a-1][b-1]){
			int schritt = backTracingAusgabe(D, a-1, b-1, sA, sB);
			int offset = sA[0].length() - i;
			System.out.println(schritt + ") Kosten 0: " + sA[0].charAt(a-1+ offset) + " an Position " + (a+ offset) + " --> " + sA[0]);
			return schritt + 1;
		} else{
			if (D[a][b] == D[a-1][b-1] + 1){
				int schritt =backTracingAusgabe(D, a-1, b-1, sA, sB);
				int offset = sA[0].length() - i;
				System.out.println(schritt + ") Kosten 1: Ersetze " + sA[0].charAt(a-1+ offset) + " an Position " + (a+ offset) + " mit " + sB.charAt(b-1) + " --> " + (sA[0] = ersetze(sA[0], sB.charAt(b-1), a-1+ offset)));
				return schritt + 1;
			}
			else if (D[a][b] == D[a][b-1] + 1){
				int schritt =backTracingAusgabe(D, a, b-1, sA, sB); 
				int offset = sA[0].length() - i;
				System.out.println(schritt + ") Kosten 1: Fuege " + sB.charAt(b-1) + " an Position " + (a+ offset) + " ein --> " + (sA[0] = einfuegen(sA[0], sB.charAt(b-1), a-1+ offset)));
				return schritt + 1;
			}
			else if (D[a][b] == D[a-1][b] + 1){
				int schritt =backTracingAusgabe(D, a-1, b, sA, sB);
				int offset = sA[0].length() - i;
				System.out.println(schritt + ") Kosten 1: Loesche " + sA[0].charAt(a-1+ offset) + " an Position " + (a+ offset) + " --> " + (sA[0] = loesche(sA[0], a-1+ offset)));
				return schritt + 1;
			}
		}	
		return -1;
		
	}
	private static String einfuegen(String sA, char b, int index){
		if (index - 1 < 0){
			sA = b + sA;
		}
		else if (index >= sA.length()){
			sA = sA + b;
		}else{
			String sA1 = sA.substring(0, index);
			String sA2 = sA.substring(index);
			sA = sA1 + b + sA2;
		}
		return sA;
	}
	
	/*
	 * ersetzt einen char an einem index mit einem anderen in einem array
	 * @param String sA der String der editiert werdne soll
	 * @param char b der char der eingesetzt werden soll
	 * @param der index an dem ersetzt wird 
	 */
	private static String ersetze(String sA, char b, int index){
		if (index - 1 < 0 ){
			if (index + 1 > sA.length())
				sA = "" + b;
			else{
			String sA1 = sA.substring(index + 1 );
			sA = b + sA1;
			}
			
		}
		else if (index >= sA.length()){
			String sA1 = sA.substring(0, index - 1);
			sA = sA1 + b;
		}else{
			String sA1 = sA.substring(0, index);
			String sA2 = sA.substring(index + 1);
			sA = sA1 + b + sA2;
		}
		return sA;
	}
	
	private static String loesche(String sA, int index){
		if (index - 1 < 0){
			if(index >= sA.length())
				sA = "";
			else{
				sA = sA.substring(index);
			}
		}
		else if (index >= sA.length()){
			sA = sA.substring(0, index - 1);
		}else{
			String sA1 = sA.substring(0, index);
			String sA2 = sA.substring(index + 1);
			sA = sA1 + sA2;
		}
		return sA;
	}
	
}

