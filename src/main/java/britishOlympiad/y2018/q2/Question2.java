package britishOlympiad.y2018.q2;

import java.util.LinkedList;
import java.util.List;

public class Question2
{
	private Dial dial1;
	private Dial dial2;
	public Question2(int n, String input)
	{
		this.dial1 = new Dial();
		this.dial2 = new Dial();
		List<Alphabet> alphabet = new LinkedList<Alphabet>();
		int pointer = 0;
		for(Alphabet i : Alphabet.values()) {
			this.dial1.addLetter(i);
			alphabet.add(i);
		}
		while(!this.dial2.isFull()) {
			pointer += n;
			pointer--; //because the list is 0 indexed so everything is shifted back one
			while(pointer >= alphabet.size()) {
				pointer -= alphabet.size();
			}
			this.dial2.addLetter(alphabet.get(pointer));
			alphabet.remove(pointer);
		}
		char[] out = input.toCharArray();
		for(int i = 0; i < out.length; i++) {
			out[i] = this.dial2.getLetter(this.dial1.getIndex(out[i])).toString().toCharArray()[0];
			this.dial1.rotate();
		}
		for(char i : out)
			System.out.print(i);
	}
}
