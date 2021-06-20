package britishOlympiad.playfairCipher;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class PlayfairCipher implements AutoCloseable
{
	private final BufferedReader br;
	private static final char[] alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
	private char[][] firstGrid;
	private char[][] secondGrid;
	
	public PlayfairCipher() throws Exception {
		this.br = new BufferedReader(new InputStreamReader(System.in));
		this.createTables(new StringBuilder(this.getTableOrder(br.readLine().toUpperCase())), new StringBuilder(this.getTableOrder(br.readLine().toUpperCase())));
		this.printTable();
		loop:while(true) {
			System.out.print("\n");
			switch(br.readLine().toUpperCase())
			{
			case "E":
				System.out.println(this.encrypt(this.br.readLine().toUpperCase()));
				break;
			case "D":
				System.out.println(this.decrypt(this.br.readLine().toUpperCase()));
				break;
			case "Q":
				break loop;
			default:
				break loop;
			}
		}
	}
	
	private String encrypt(String in) {
		String out = "";
		if((in.length() % 2) == 1) in += "X";
		
		for(int i = 0; i < in.length(); i += 2) {
			char[] pair = {in.charAt(i), in.charAt(i+1)};
			int[] firstLetterCoords = this.getCoordinates(pair[0], this.firstGrid);
			int[] secondLetterCoords = this.getCoordinates(pair[1], this.secondGrid);
			
			if(firstLetterCoords[0] == secondLetterCoords[0]) {
				int j = firstLetterCoords[1]+1;
				if(j == 5) j -= 5;
				pair[0] = this.firstGrid[firstLetterCoords[0]][j];
				
				j = secondLetterCoords[1]+1;
				if(j == 5) j -= 5;
				pair[1] = this.secondGrid[secondLetterCoords[0]][j];
			} else {
				pair[0] = this.firstGrid[secondLetterCoords[0]][firstLetterCoords[1]];
				pair[1] = this.secondGrid[firstLetterCoords[0]][secondLetterCoords[1]];
			}
			out += pair[0] + "" + pair[1];
		}
		
		return out;
	}
	
	private String decrypt(String in) {
		String out = "";
		
		for(int i = 0; i < in.length(); i += 2) {
			char[] pair = {in.charAt(i), in.charAt(i+1)};
			int[] firstLetterCoords = this.getCoordinates(pair[0], this.firstGrid);
			int[] secondLetterCoords = this.getCoordinates(pair[1], this.secondGrid);
			
			if(firstLetterCoords[0] == secondLetterCoords[0]) {
				int j = firstLetterCoords[0]-1;
				if(j == -1) j += 5;
				pair[0] = this.firstGrid[firstLetterCoords[0]][j];
				
				j = secondLetterCoords[1]-1;
				if(j == -1) j += 5;
				pair[1] = this.secondGrid[secondLetterCoords[0]][j];
			} else {
				pair[0] = this.firstGrid[secondLetterCoords[0]][firstLetterCoords[1]];
				pair[1] = this.secondGrid[firstLetterCoords[0]][secondLetterCoords[1]];
			}
			out += pair[0] + "" + pair[1];
		}
		
		if(out.charAt(out.length()-1) == 'X')
			out = new StringBuilder(out).deleteCharAt(out.length()-1).toString();
		
		return out;
	}
	
	private int[] getCoordinates(char letter, char[][] table) {
		for(int i = 0; i < table.length; i++)
			for(int j = 0; j < table[i].length; j++)
				if(table[i][j] == letter)
					return new int[] {i, j};
		return new int[] {};
	}
	
	private String getTableOrder(String in) {
		char[] word = in.toCharArray();
		Set<Character> charsInUse = new HashSet<Character>();
		charsInUse.add('Q');
		String out = "";
		for(char character : word) {
			if(!charsInUse.contains(character)) {
				out += character;
				charsInUse.add(character);
			}
		}
		for(char i : alphabet)
			if(!charsInUse.contains(i))
				out += i;
		return out;
	}
	
	private void createTables(StringBuilder firstGridOrder, StringBuilder secondGridOrder) {
		this.firstGrid = new char[5][5];
		this.secondGrid = new char[5][5];
		for(int i = 0; i < this.firstGrid.length; i++) {
			for(int j = 0; j < this.firstGrid[i].length; j++) {
				this.firstGrid[i][j] = firstGridOrder.charAt(0);
				firstGridOrder.deleteCharAt(0);
			}
		}
		for(int i = this.secondGrid.length-1; i >= 0; i--) {
			for(int j = this.secondGrid.length-1; j >= 0; j--) {
				this.secondGrid[i][j] = secondGridOrder.charAt(0);
				secondGridOrder.deleteCharAt(0);
			}
		}		
	}
	
	private void printTable() {
		System.out.print("\n");
		for(int i = 0; i < this.firstGrid.length; i++) {
			for(int j = 0; j < this.firstGrid[i].length; j++)
				System.out.print(this.firstGrid[i][j]);
			System.out.print("\t");
			for(int j = 0; j < this.secondGrid[i].length; j++)
				System.out.print(this.secondGrid[i][j]);
			System.out.print("\n");
		}
	}

	@Override
	public void close() throws Exception {
		this.br.close();
	}
}
