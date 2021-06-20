package britishOlympiad;

import java.util.HashSet;
import java.util.Set;

public class DigitalRiver
{
	public DigitalRiver(int riverNumber)
	{
		River river1 = new River(1);
		River river3 = new River(3);
		River river9 = new River(9);
		River river = new River(riverNumber);
		System.out.println("River: " + riverNumber);
		while(true)//for(int i = 0; i < 100; i++)
		{
			//System.out.println(river1.getNumber());
			//System.out.println(river.getNumbers().size() + ": " + river.getNumber() + " " + river1.getNumber() + " " + river3.getNumber() + " " + river9.getNumber());
			//System.out.println();
			//System.out.println(river.getNumbers().size() + ": " + river.getNumber());
			//System.out.println();
			if(river1.contains(river)) {
				this.out(1, river1.getNumber());
				break;
			}
			if(river3.contains(river)) {
				this.out(3, river3.getNumber());
				break;
			}
			if(river9.contains(river)) {
				this.out(9, river9.getNumber());
				break;
			}
			river1.add();
			river3.add();
			river9.add();
			river.add();
		}
	}
	public void out(int riverNumber, int number)
	{
		System.out.println("First meets river " + riverNumber + " at "  + number);
	}
}
class River
{
	int number;
	Set<Integer> numbers;
	public River(int startNumber)
	{
		this.numbers = new HashSet<Integer>();
		this.numbers.add(startNumber);
		this.number = startNumber;
	}
	public void add()
	{
		char[] digits = Integer.toString(this.number).toCharArray();
		//System.out.println(digits[0]);
		int temp = 0;
		for(char digit : digits)
			temp += Character.getNumericValue(digit);
		//System.out.println(temp);
		this.number += temp;
		this.numbers.add(this.number);
	}
	public boolean compare(River value)
	{
		return this.number == value.getNumber();
	}
	public boolean contains(River value)
	{
		for(int i : value.getNumbers())
			if(this.numbers.contains(i))
				return true;
		return false;
		//return this.numbers.contains(value.getNumber());
	}
	public int getNumber()
	{
		return this.number;
	}
	public Set<Integer> getNumbers()
	{
		return this.numbers;
	}
}