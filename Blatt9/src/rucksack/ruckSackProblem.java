package rucksack;

import java.util.ArrayList;

public class ruckSackProblem {
	ArrayList<Article> mengeArticle;
	int gewicht;
	int p;
	
	public void sortiere(){
		this.mengeArticle.sort( (a,b) -> (a.value / a.weight) - (b.value / b.weight) );
	}
	
	public RuckSack greedie(){
		
		
	}
}
