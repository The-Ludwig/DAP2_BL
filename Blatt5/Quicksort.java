import java.util.Random;


class Quicksort
{
	public static void main(String[] args)
	{
		//Try to parse one int
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
		if (len < 0) {,
			System.out.println("bitte keine negative array laengen. Dankle");
			return;
		}
		
		//The array
		int[] ints = new int[len];
		
		//To produce pseudo-random numbers
		Random r = new Random();
		
		//Fill Array
		for(int i = 0; i < len; i++)
		{
			ints[i] = r.nextInt();
		}
		
		//Quicksort and Time...
		double t1 = System.currentTimeMillis();
		quicksort(ints, 0,  len-1);
		double dt = System.currentTimeMillis() - t1;
		
		//Print result
		if(!isSorted(ints))
			System.out.print("Array is not sorted, ");
		else
			System.out.print("Array is sorted. ");
		
		System.out.println("Done that in "+dt/1000+" seconds!");
			
	}
	
	/*
	 * @brief Looks if Array A is sorted
	 * 
	 * @param A Array
	 * @return is sorted?
	 */
	public static boolean isSorted(int[] A)
	{
		for(int i = 1; i < A.length; i++)
		{
			if(A[i] < A[i-1])
				return false;
		}
		return true; 
	}
	
	/*
	 * @brief Implementation of quickSort
	 * 
	 * @param A Array
	 * @param l Left index of sorted  Array (init: 0)
	 * @param r Right index of sorted Array (init: A.length -1)
	 */
	public static void quicksort(int[] A, int l, int r)
	{
		if(l > r)
			return;
		
		int i = l;
		int j = r;
		int pivot = A[(l+r)/2];
		
		//Invariante: alle ellemente A[l] bis A[i] < pivot und alle A[j] bis A[r] sind groeße pivot 
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
	
	/*
	 * @brief Prints usage of this programm
	 */
	public static void printUsage()
	{
		System.out.println("java Quicksrot [int]");
	}
}
