package britishOlympiad;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class CountingCircles
{
	private List<Integer> list;
	private int pointer;
	public CountingCircles()
	{
		this.list = new LinkedList<Integer>();
		this.pointer = 0;
	}
	public void run() throws NumberFormatException, IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int noOfPeople = Integer.parseInt(br.readLine());
		int wordsInRhyme = br.readLine().split(" ").length;
		br.close();
		//System.out.println(noOfPeople);
		for(int i = 0; i < noOfPeople; i++) enqueue(i);
		
		while(list.size() > 1) {
			for(int j = 0; j < wordsInRhyme; j++) this.incrementPointer();
			System.out.println(this.list.get(this.pointer));
			this.remove();
		}
		System.out.println(list.get(0));
	}
	private void enqueue(int number)
	{
		this.list.add(number);
	}
	private void incrementPointer()
	{
		//this.pointer = (this.pointer+1) % this.list.size();
		this.pointer++;
		if(this.pointer == this.list.size()) this.pointer -= this.list.size();
	}
	private void remove()
	{
		this.list.remove(this.pointer);
	}
}
