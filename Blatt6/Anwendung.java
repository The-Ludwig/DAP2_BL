import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.NoSuchElementException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;


class Anwendung
{
	public static void main(String[] args)
	{
		boolean intervalSchedule = true;
		
		if(args.length != 2) 
		{
			printUsage();
			return;
		}
			
		if(args[0].equals("Lateness"))
			intervalSchedule = false;
		else if(!args[0].equals("Interval"))
		{
			printUsage();
			return;
		}
			
		
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(args[1]));
		}catch(FileNotFoundException e) {
			System.out.println(args[1]+" was not found!");
			return;
		}
		
		System.out.print("Bearbeite Datei \""+args[1]+"\".");
		
		if(intervalSchedule) 
			intervalApp(br);
		else
			latenessApp(br);
		
	}
	
	public static ArrayList<Interval> intervalScheduling(ArrayList<Interval> intervals)
	{
		ArrayList<Interval> returner = new ArrayList<Interval>(intervals.size());
		
		returner.add(intervals.get(0));
		int j = 0;
		for(int i = 1; i < intervals.size(); i++)
		{
			if(intervals.get(i).getStart() >= intervals.get(j).getEnd())
			{
				returner.add(intervals.get(i));
				j = i;
			}

		}
		
		return returner;
	}
	
	public static int[] latenessScheduling(ArrayList<Job> jobs)
	{
		int[] returner = new int[jobs.size()];
		int z = 0;
		for(int i = 0; i < jobs.size(); i++)
		{
			returner[i] = z;
			z += jobs.get(i).getTime();
		}
		return returner; 
	}
	
	public static void latenessApp(BufferedReader br)
	{
		ArrayList<Job> jobs = new ArrayList<Job>();
		String cur;
		try {
			while((cur = br.readLine()) != null)
			{
				StringTokenizer st = new StringTokenizer(cur, ",");
				int time = 0;
				int deadline = 0;
				try {
					time = Integer.parseInt(st.nextToken());
					deadline = Integer.parseInt(st.nextToken());
				}catch(NoSuchElementException e) {
					System.out.println("To few numbers in line "+jobs.size());
					return;
				}catch(NumberFormatException e) {
					System.out.println("Not a number in line "+jobs.size());
					return;
				}
				jobs.add(new Job(time, deadline));
			}
		}catch(IOException e){
			System.out.println("IOERROR!");
		}
		
		System.out.print("Es wurden "+jobs.size()+" Zeilen mit folgendem Inhalt ausgelesen: ");
		System.out.println(jobs.toString());
		
		System.out.print("Sortiert: ");
		Collections.sort(jobs);
		System.out.println(jobs.toString());
		
		System.out.print("Berechnetes Latenesscheduling: ");
		int[] startTimes = latenessScheduling(jobs);
		System.out.println(Arrays.toString(startTimes));
		
		int maxLateness = 0;
		
		for(int i = 0; i < startTimes.length; i++)
		{
			int lateness = jobs.get(i).getTime() + startTimes[i] - jobs.get(i).getDeadline();
			if(maxLateness < lateness)
				maxLateness = lateness;
		}
		
		System.out.println("Maximale Verspätung: "+maxLateness);
	}
	
	public static void intervalApp(BufferedReader br)
	{
		ArrayList<Interval> intervals = new ArrayList<Interval>();
		String cur;
		try {
			while((cur = br.readLine()) != null)
			{
				StringTokenizer st = new StringTokenizer(cur, ",");
				int start = 0;
				int end = 0;
				try {
					start = Integer.parseInt(st.nextToken());
					end = Integer.parseInt(st.nextToken());
				}catch(NoSuchElementException e) {
					System.out.println("To few numbers in line "+intervals.size());
					return;
				}catch(NumberFormatException e) {
					System.out.println("Not a number in line "+intervals.size());
					return;
				}
				intervals.add(new Interval(start, end));
			}
		}catch(IOException e){
			System.out.println("IOERROR!");
		}
		
		System.out.print("Es wurden "+intervals.size()+" Zeilen mit folgendem Inhalt ausgelesen: ");
		System.out.println(intervals.toString());
		
		System.out.print("Sortiert: ");
		Collections.sort(intervals);
		System.out.println(intervals.toString());
		
		System.out.print("Berechnetes Intervallscheduling: ");
		System.out.println(intervalScheduling(intervals));
	}
	
	public static void printUsage()
	{
		System.out.println("Use as follows:\n Anwendung [Lateness|Interval] [Filepath]");
	}
}