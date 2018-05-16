import java.util.Random;


class Quicksort
{
	public static void main(String[] args)
	{
		int len;
		if(args.length < 1)
		{
			printUsage();
			return;
		}
		try {
			len = Integer.parseInt(args[0]);
		}catch(NumberFormatException e) {
			printUsage();
			return;
		}
		
		int[] ints = new int[len];
		
		Random r = new Random();
		
		for(int i = 0; i < len; i++)
		{
			ints[i] = r.nextInt();
		}
		
		double t1 = System.currentTimeMillis();
		quicksort(ints, 0,  len);
		double dt = System.currentTimeMillis() - t1;
		
		if(!isSorted(ints))
			System.out.print("Array is not sorted, ");
		else
			System.out.print("Array is sorted. ");
		
		System.out.println("Done that in "+dt/1000+" seconds!");
			
	}
	
	public static boolean isSorted(int[] A)
	{
		for(int i = 1; i < A.length; i++)
		{
			if(A[i] < A[i-1])
				return false;
		}
		return true; 
	}
	
	public static void quicksort(int[] A, int l, int r)
	{
		if(l > r)
			return;
		
		int i = l;
		int j = r;
		int pivot = A[(l+r)/2];
		
		while(i <= j)
		{
			while(A[i] < pivot)
				i++;
			while(A[j] > pivot)
				j--;
			if(i <= j) {
				int tmp = A[i];
				A[i] = A[j];
				A[j] = tmp;
				i++;
				j--;
			}
		}
		quicksort(A, l, j);
		quicksort(A, i, r);
	}
	
	public static void printUsage()
	{
		System.out.println("java Quicksrot [int]");
	}
}