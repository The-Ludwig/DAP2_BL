/*
 * Intervals are specified by a start and a end field 
 */
class Interval implements Comparable<Interval>
{
	private int start, end;
	
	Interval(int start, int end)
	{
		this.start = start;
		this.end = end;
	}
	
	int getStart()
	{
		return start;
	}
	
	int getEnd()
	{
		return end;
	}
	
	@Override
	public String toString()
	{
		return "["+start+", "+end+"]";
	}
	
	@Override
	public int compareTo(Interval other)
	{
		if(other.getEnd() == end)
			return 0;
		else if(other.getEnd() > end)
			return -1;
		return 1;
	}
}