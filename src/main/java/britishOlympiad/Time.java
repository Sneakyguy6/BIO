package britishOlympiad;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Time
{
	public static void run() throws NumberFormatException, IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int hour = Integer.parseInt(br.readLine()),
			minute = Integer.parseInt(br.readLine());
		br.close();
		String out = hour + ":";
		if(minute < 10)
			out += "0";
		out += minute + " ";
		if(minute == 0)
			out += numberNames.values()[hour-1] + " o'clock";
		else if((minute % 15) == 0)
			out += fifteens(hour, minute);
		else
			out += tens(hour, minute);
		char[] caps = out.toCharArray();
		int temp;
		if(hour < 10) temp = 5;
		else temp = 6;
		caps[temp] = Character.toUpperCase(caps[temp]);
		out = "";
		for(char i : caps) out += i;
		System.out.println(out);
	}
	private static String tens(int hour, int minute)
	{
		String out = "";
		boolean useReverse = true; //use 'to' instead of 'past'
		if(minute < 20) {
			out += numberNames.values()[minute-1];
			useReverse = false;
		}
		else if(minute < 40) {
			out += "twenty";
			useReverse = !(minute < 30); //if minute less than 30 then set to false and use 'past'
			if(useReverse)
				minute = 60 - minute;
			if(minute != 20)
				out +=  " " + numberNames.values()[Character.getNumericValue(Integer.toString(minute).toCharArray()[1])-1];
		}
		else if(minute >= 40)
			out += numberNames.values()[(60-minute)-1];
		out += " minute" + ((minute == 1 || minute == 59)? "":"s") + " " + ((useReverse)? "to " + toHour(hour, minute):"past " + numberNames.values()[hour-1]);
		return out;
	}
	private static String toHour(int hour, int minute)
	{
		String out = "";
		if(hour == 12)
			out += "one";
		else
			out += numberNames.values()[hour];
		return out;
	}
	private static String fifteens(int hour, int minute)
	{
		String out = "";
		if(minute == 15)
			out += "quarter past " + numberNames.values()[hour-1];
		else if(minute == 30)
			out += "half past " + numberNames.values()[hour-1];
		else if(minute == 45) {
			out += "quarter to " + toHour(hour, minute);
		}
		return out;
	}
}
enum numberNames
{
	one,
	two,
	three,
	four,
	five,
	six,
	seven,
	eight,
	nine,
	ten,
	eleven,
	twelve,
	thirteen,
	fourteen,
	fifteen,
	sixteen,
	seventeen,
	eighteen,
	nineteen,
}