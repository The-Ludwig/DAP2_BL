import java.util.Random; 

class PriorityQueue
{
	public static void main(String[] args) 
	{
		if(args.length != 2)
		{
			System.out.println("You have to give 2 arguments (ints)");
			return;
		}
		int n = -1, k = -1;
		try
		{
			n = Integer.parseInt(args[0]);
			k = Integer.parseInt(args[1]);
		}catch(NumberFormatException e){
			System.out.println("Your Arguments were no ints!");
			return;
		}
		
		MaxHeap heap = new MaxHeap(n+k);
		Random r = new Random();
		
		for(int i = 0; i < n; i++) 
			heap.insert(r.nextInt(100));
		
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