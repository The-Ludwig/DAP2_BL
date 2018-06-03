
public class EditDistance {
	
	public static int distance(String a, String b){
		int n = a.length() + 1;
		int m = b.length() + 1;
		
		int[][] D = new int[n][m];
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
		
		return D[a.length()][b.length()];
	}
	
	
	static int min(int a, int b, int c){
		if (a <= b  && a <= c)
			return a;
		else if (b <= a && b<= c)
			return b;
		else
			return c;
	}

}

