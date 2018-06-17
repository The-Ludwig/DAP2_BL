

import java.util.ArrayList;
import java.util.Random;

public class ruckSackProblem {
	ArrayList<Article> mengeArticle;
	int p, gewicht;
	
	ruckSackProblem(int gewicht, int p, int n)
	{
		this.gewicht = gewicht;
		mengeArticle = new ArrayList<Article>(n);
		Random r = new Random(); 
		for(int i = 0; i < n; i++)
		{
			mengeArticle.add(new Article(r.nextInt(900) + 100, r.nextInt((int)(0.45 * p)) + (int) (0.8 * p))); 
		}
	}
	
	public void sortiere(){
		this.mengeArticle.sort( (a,b) -> (a.value / a.weight) - (b.value / b.weight) );
	}
	
	public int greedie(){
		this.sortiere();
		int restGewicht = this.gewicht;
		
		ArrayList<Article> loesung = new ArrayList<>();
		
		for (Article article : mengeArticle) {
			if (article.weight > restGewicht)
				continue;
			loesung.add(article);
			restGewicht -= article.weight;
		}
		
		int ls = 0;
		
		for (Article article : loesung) {
			ls+= article.value;
		}
		return ls;
	}
	/**
	 * 
	 */
	public int dynamisch() {
		int[][] loesung = new int[mengeArticle.size() + 1][gewicht + 1];
		
		for (int i = 0; i < loesung.length; i++) {
			loesung[i][0] = 0;
		}
		
		for (int i = 0; i < loesung[0].length; i++) {
			loesung[0][i] = 0;
		}
		
		
		for (int i = 1; i < loesung.length; i++) {
			for (int j = 1; j < loesung[0].length; j++) {
				if (mengeArticle.get(i-1).weight > j)
					loesung[i][j] = loesung[i-1][j];
				else {
					loesung[i][j] = max(loesung[i-1][j], loesung[i-1][j-mengeArticle.get(i-1).weight] + mengeArticle.get(i-1).value);
				}
			}
		}
		
		return loesung[mengeArticle.size()][gewicht];
	}
	
	public int max(int a , int b) {
		return a > b ? a : b;
	}
	
}

