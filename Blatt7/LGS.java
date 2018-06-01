import java.util.Random; 

class LGS
{
	public static void main(String[] args)
	{
		int len = 0;
		try
		{
			len = Integer.parseInt(args[0]);
		}catch(NumberFormatException e)
		{
			System.out.println(e.toString());
			return;
		}catch(ArrayIndexOutOfBoundsException e) {
			System.out.println(e.toString());
			return; 
		}
		
		String rand1 = "AsdfljksdfoihB";
		String rand2 = "A235346346B";
		
		System.out.println("Folge 1: "+rand1+"\nFolge 2: "+rand2);
		long t = System.currentTimeMillis();
		String res = lks(rand1, rand2);
		t = System.currentTimeMillis() - t;
		
		System.out.println("Die längste gemeinsame SubFolge ist: \""+ res + "\" Dies zu berechnen hat "+(double)t/1000+ " Sekunden gedauert");
		System.out.println("Die lanege der Laengsten war " + res.length());
	}
	
	public static String lks(String s1, String s2)
	{
		int l1 = s1.length();
		int l2 = s2.length();
		
		int[][] c = new int[l1+1][l2+1];
		for(int i = 0; i <= l1; i++) c[i][0] = 0;
		for(int i = 0; i <= l2; i++) c[0][i] = 0;
		
		for(int i = 1; i <= l1; i++)
		for(int j = 1; j <= l2; j++)
			if(s1.charAt(i-1) == s2.charAt(j-1))
				c[i][j] = c[i-1][j-1]+1;
			else
				if(c[i-1][j] >= c[i][j-1])
					c[i][j] = c[i-1][j];
				else
					c[i][j] = c[i][j-1];
		
		StringBuilder res = new StringBuilder(c[l1][l2]);
		
		while(l1 != 0 && l2 != 0)
			if(s1.charAt(l1-1) == s2.charAt(l2-1)) 
			{
				res.append(s2.charAt(--l2));
				l1--;
			}
			else if (c[l1][l2-1] >= c[l1-1][l2])
				l2--;
			else 
				l1--;
		
		return res.reverse().toString();
		
	}
	
	public static String randStr(int n)
	{
		Random r = new Random();
		String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuilder res = new StringBuilder(n);
		while(--n >= 0)
		{
			res.append(alphabet.charAt(r.nextInt(alphabet.length())));
		}
		return res.toString(); 
	}
}