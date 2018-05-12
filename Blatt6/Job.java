
class Job implements Comparable<Job>
{
	private int time, deadline;
	
	Job(int time, int deadline)
	{
		this.time = time;
		this.deadline = deadline;
	}
	
	int getTime()
	{
		return time;
	}
	
	int getDeadline()
	{
		return deadline;
	}
	
	@Override
	public String toString()
	{
		return "["+time+", "+deadline+"]";
	}
	
	@Override
	public int compareTo(Job other)
	{
		if(other.getDeadline() == deadline)
			return 0;
		else if(other.getDeadline() > deadline)
			return -1;
		return 1;
	}
}