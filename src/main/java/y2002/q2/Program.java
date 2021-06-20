package y2002.q2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Program {
	public static void main(String... args) throws IOException {
		List<Character> instructions;
		int[] cards = {1, 2, 3, 4, 5, 6, 7, 8};
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			System.out.println("Enter instructions");
			instructions = compile2(br.readLine().toCharArray());
		}
		//System.out.println(instructions);
		for(char c : instructions) {
			switch(c)
			{
			case 'b':
				breakShuffle(cards);
				break;
			case 'i':
				cards = inRiffle(cards);
				break;
			case 'o':
				cards = outRiffle(cards);
				break;
			}
		}
		print(cards);
	}
	
	private static List<Character> compile2(char[] instructions) {
		List<Character> out = new ArrayList<Character>();
		int i;
		for(i = 0; i < instructions.length; i++) {
			if(instructions[i] > '0' && instructions[i] < '9')
				i = bracketsVisitor(instructions, i, out);
			else
				out.add(instructions[i]);
		}
		//System.out.println(instructions[i + 1]);bracketsVisitor(instructions, i + 1));
		return out;
	}
	
	private static int bracketsVisitor(char[] instructions, int currentIndex, List<Character> out) { //currentIndex is the index of the number itself
		int j = currentIndex;
		if(instructions[currentIndex + 1] != '(') {
			for(int i = 0; i < Character.getNumericValue(instructions[currentIndex]) - 1; i++)
				out.add(instructions[currentIndex + 1]);
		} else {
			List<Character> repeatPattern = new ArrayList<Character>();
			for(j = currentIndex + 2; instructions[j] != ')'; j++) {
				if(instructions[j] > '0' && instructions[j] < '9')
					j = bracketsVisitor(instructions, j, repeatPattern);
				else
					repeatPattern.add(instructions[j]);
			}
			for(int c = 0; c < Character.getNumericValue(instructions[currentIndex]); c++)
				out.addAll(repeatPattern);
		}
		return j;
	}
	
	private static void breakShuffle(int[] cards) {
		int temp = cards[0];
		for(int i = 1; i < cards.length; i++)
			cards[i - 1] = cards[i];
		cards[cards.length - 1] = temp;
	}
	
	private static int[] inRiffle(int[] cards) {
		int[] out = new int[cards.length];
		for(int i = 0; i < cards.length / 2; i++)
			out[i * 2 + 1] = cards[i];
		for(int i = 0; i < cards.length / 2; i++)
			out[i * 2] = cards[i + cards.length / 2];
		return out;
	}
	
	private static int[] outRiffle(int[] cards) {
		int[] out = new int[cards.length];
		for(int i = 0; i < cards.length / 2; i++)
			out[i * 2] = cards[i];
		for(int i = 0; i < cards.length / 2; i++)
			out[i * 2 + 1] = cards[i + cards.length / 2];
		return out;
	}
	
	private static void print(int[] cards) {
		for(int i : cards)
			System.out.print(i + " ");
	}
}
