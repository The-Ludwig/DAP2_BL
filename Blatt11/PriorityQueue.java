import java.util.Random; 

class PriorityQueue
{
	public static void main(String[] args) 
	{
		//self explaining
		if(args.length != 2)
		{
			System.out.println("You have to give 2 arguments (ints)");
			return;
		}
		int n = -1, k = -1;
		//self explaining
		try
		{
			n = Integer.parseInt(args[0]);
			k = Integer.parseInt(args[1]);
		}catch(NumberFormatException e){
			System.out.println("Your Arguments were no ints!");
			return;
		}
		
		//heap and random generator 
		MaxHeap heap = new MaxHeap(n+k);
		Random r = new Random();
		
		
		//fill heap
		for(int i = 0; i < n; i++) 
			heap.insert(r.nextInt(100));
		
		//do operations on hepa
		for(int i = 0; i < k; i++)
		{
			if(r.nextInt(4) < 3)
			{
				System.out.println((i+1)+". Job "+heap.extractMax()+" wurde entfernt!");
			}else{
				int new_job = r.nextInt(100);
				heap.insert(new_job); 
				System.out.println((i+1)+". Job "+new_job+" wurde hinzugefügt!");
			}
			
			heap.printHeap();
		}
		
	}
	
	
}