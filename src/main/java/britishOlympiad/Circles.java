package britishOlympiad;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class Circles {
	public Circles() throws NumberFormatException, IOException {
		System.out.println("circles");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		List<Integer> people = new LinkedList<Integer>();
		int numberOfPeople = Integer.parseInt(br.readLine());
		for(int i = 0; i < numberOfPeople; i++)
			people.add(i+1);
		int numberOfWordsInRhyme = Integer.parseInt(br.readLine());
		
		int pointer = 0;
		while(people.size() > 1) {
			pointer = (pointer + numberOfWordsInRhyme-1) % people.size();
			people.remove(pointer);
		}
		
		System.out.println(people.get(0));
	}
}
//System.out.println("i");
//pointer += numberOfWordsInRhyme-1;
//while(pointer >= people.size())
//	pointer -= people.size();
//System.out.println(pointer + " | " +  people.get(pointer));