package britishOlympiad;

public class RomanNumerals
{
	private int number;
	private String out;
	
	private String oneth;
	private String fifth;
	private String tenth;
	public RomanNumerals(int in)
	{
		if(in > 3999) {
			System.out.println("Too big!");
			return;
		}
		this.out = "";
		this.number = in;
		for(int i = Integer.toString(in).length(); i >= 1; i--)
			this.calc(i);
		System.out.println(this.out);
	}
	private void calc(int where)
	{
		int index = Integer.toString(this.number).length() - where;
		//System.out.println(where);
		switch(where)
		{
		case 1:
			this.oneth = "I";
			this.fifth = "V";
			this.tenth = "X";
			//this.out += this.first(index);
			break;
		case 2:
			this.oneth = "X";
			this.fifth = "L";
			this.tenth = "C";
			//this.out += this.second(index);
			break;
		case 3:
			this.oneth = "C";
			this.fifth = "D";
			this.tenth = "M";
			break;
		case 4:
			this.oneth = "M";
			break;
		}
		this.out += this.getString(index);
	}
	private String getString(int where)
	{
		int in = Character.getNumericValue(Integer.toString(this.number).toCharArray()[where]);
		//System.out.println("First: " + in);
		String temp = "";
		if(in == 0) temp += "";
		if(in >= 9) {
			for(int i = 10; i > in; i--) temp += this.oneth;
			temp += this.tenth;
		}else if(in > 5 && in <= 8) {
			temp += this.fifth;
			for(int i = 5; i < in; i++) temp += this.oneth;
		}else if(in <= 3) {
			for(int i = 0; i < in; i++) temp += this.oneth;
		}else {
			for(int i = 5; i > in; i--) temp += this.oneth;
			temp += this.fifth;
		}
		return temp;
	}
}
