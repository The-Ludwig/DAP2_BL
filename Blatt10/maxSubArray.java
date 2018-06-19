import java.util.Random;

public class maxSubArray
{
	public static void main(String[] args)
	{
				
		int len = 0;
		try 
		{
			len = Integer.parseInt(args[0]);
		}catch(NumberFormatException | ArrayIndexOutOfBoundsException e) {
			System.out.println("Usage:\njava maxSubArray n");
			return;
		}
		
		if(len < 1) 
		{
			System.out.println("Length (n) must be greater than 1");
			return; 
		}
		
		Random r = new Random(); 
		
		int[] array = new int[len];
		for(int i = 0; i < len; i++)
		{
			array[i] = r.nextInt(200) - 100;
		}
		
		if(args.length > 1)
			System.out.println("Zufaelliges Array: " + java.util.Arrays.toString(array));
		
		int[] answer; 
		long t1 = System.currentTimeMillis();
		answer = naive(array);
		System.out.println("Der naive Algorithmus spuckt aus: " + java.util.Arrays.toString(answer) + " und das in " + (float)(System.currentTimeMillis() - t1)/1000.0 + " Sekunden.");
		t1 = System.currentTimeMillis();
		answer = dynamic(array);
		System.out.println("Der dynamische Algorithmus spuckt aus: " + java.util.Arrays.toString(answer) + " und das in " + (float)(System.currentTimeMillis() - t1)/1000.0 + " Sekunden.");
	}
	
	/**
	 * @brief Berechnet maximalen Subarray dynamisch in O(n) Zeit
	 * @param array Zu betrachtenes Array
	 * @return SubArray der Form {index1, index2, summe von index1 bis index2}
	 */
	public static int[] dynamic(int[] array)
	{
		//Kandidatenarray
		int start = 0, end = 0, count = 0; 
		
		
		//Summe des maximalen Subarrays von array[1...index] in der Form (start, end, count)
		int[][] maxSubarrayTo = new int[array.length][3]; 
		
		//Entfalteter Rekursionsabbruch
		if(array[0] >= 0)
		{
			start = 0; end = 1; count = array[0];
			maxSubarrayTo[0] = new int[] {0, 1, array[0]};
		}
		else
		{
			start = 1; 
			end = 1;
			maxSubarrayTo[0] = new int[] {0, 0, 0}; 
		} 
		
		//Iteration durchs Array (O(n))
		for(int i = 1; i < array.length; i++)
		{
			if(count + array[i] >= 0 ) 
			{
				end ++;
				count += array[i];
				if(count > maxSubarrayTo[i-1][2])
					maxSubarrayTo[i] = new int[] {start, end, count};
				else
					maxSubarrayTo[i] = maxSubarrayTo[i-1];
			}else{
				start = i+1;
				end = i+1;
				count = 0; 
				maxSubarrayTo[i] = maxSubarrayTo[i-1];
			}
		}
		
		return maxSubarrayTo[array.length - 1]; 
	}
	/**
	 * @brief Berechnet maximales Subarray in O(n^2) Zeit
	 * @param array Zu betrachtenes Array
	 * @return SubArray der Form {index1, index2, summe von index1 bis index2}
	 */
	public static int[] naive(int[] array)
	{
		//Momentan Maximales Array
		int start = 0, end = 0, count = 0;
		
		//Iteration über alle Subarrays (Anzahl nicht leerer Subarray in O(n^2))
		for(int i = 0; i < array.length; i++)
		{
			int curSum = 0;
			for(int j = i; j < array.length; j++)
			{
				curSum += array[j];
				if(curSum > count)
				{
					start = i;
					end = j + 1;
					count = curSum; 
				}
			}
		}
		
		//Gebe grenzen und summe des Subarray zurueck
		return new int[] {start, end, count};
	}
	
	/**
	 * @brief Calculates sum of integer array
	 * @return Sum
	 * @param array Array
	 */
	public static int sum(int[] array)
	{
		int sum = 0;
		for(int i : array)
		{
			sum += i;
		}
		return sum;
	}
}