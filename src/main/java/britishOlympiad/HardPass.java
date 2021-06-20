package britishOlympiad;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HardPass
{
	public static void run() throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String in = br.readLine();
		boolean isRepeated = false;
		while(in.length() > 0) {
			for(int i = 1; i < (in.length()/2)+1; i++)
				if(in.substring(i, i+i).equals(in.substring(0,i)))
					isRepeated = true;
			StringBuilder temp = new StringBuilder(in);
			temp.deleteCharAt(0);
			in = temp.toString();
		}
		System.out.println((isRepeated)? "Rejected":"Accepted");
	}
	/*public static void run() throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String in = br.readLine();
		br.close();
		boolean isRepeated = false;
		while(in.length() > 0)
		{
			for(int i = 1; i < (in.length()/2)+1; i++)
			{
				String str = in.substring(0,i);
				String str2 = in.substring(i, i+i);
				//System.out.println(str + " " + str2);
				if(str2.equals(str)) {
					isRepeated = true;
					//System.out.println("true");
				}
			}
			StringBuilder temp = new StringBuilder(in);
			temp.deleteCharAt(0);
			in = temp.toString();
		}
		
		System.out.println((isRepeated)? "Rejected":"Accepted");
	}*/
	
	
	
	/*public static void run() throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String in = br.readLine();
		br.close();
		boolean isRepeated = false;
		
		for(int i = 0; i < in.length()/2; i++)
		{
			String str = in.substring(0, i+1);
			for(int j = i; j < in.length()-str.length(); j++)
			{
				System.out.println(str + " " + in.substring(j, j+str.length()));
				//System.out.println(isRepeated);
				if(str.equals(in.substring(j, j+str.length()))) {
					isRepeated = true;
					System.out.println("true");
				}
			}
		}
		System.out.println((isRepeated)? "Not Acceptable":"Acceptable");
	}*/
	/*for(int a = 1; a < in.length(); a++)
	{
		int counter = 1;
		for(int i = a; i < (in.length()/2)+1; i++)
		{
			String str = in.substring(0,i);
			String str2 = in.substring(i, i+counter);
			System.out.println(str + " " + str2);
			if(str2.equals(str)) {
				isRepeated = true;
				//System.out.println("true");
			}
		}
		counter++;
	}*/
}
