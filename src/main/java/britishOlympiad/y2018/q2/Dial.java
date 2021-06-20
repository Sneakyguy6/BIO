package britishOlympiad.y2018.q2;

import java.util.Arrays;

public class Dial
{
	private Alphabet[] letters;
	private int pointer;
	public Dial()
	{
		letters = new Alphabet[26];
		pointer = 0;
	}
	public void addLetter(Alphabet character)
	{
		//System.out.println(character);
		this.letters[this.pointer] = character;
		pointer++;
	}
	public boolean isFull()
	{
		return this.pointer >= this.letters.length;
	}
	public void rotate()
	{
		Alphabet temp = this.letters[25];
		for(int i = this.letters.length-1; i >= 1; i--)
			this.letters[i] = this.letters[i-1];
		this.letters[0] = temp;
	}
	public int getIndex(char character)
	{
		int index = 0;
		while(true)
			if(letters[index].equals(Alphabet.valueOf(character + "")))
				break;
			else
				index++;
		return index;
	}
	public Alphabet getLetter(int index) {
		return this.letters[index];
	}
	@Override
	public String toString()
	{
		return Arrays.toString(this.letters);
	}
}
