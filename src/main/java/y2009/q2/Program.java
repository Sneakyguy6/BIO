package y2009.q2;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class Program {
	public static void main(String... args) {
		char[][] test = new char[][] {
			{'R', 'R', 'G', 'B'},
			{'G', 'R', 'B', 'G'},
			{'R', 'R', 'G', 'B'},
			{'G', 'B', 'R', 'B'},
		};
		
		Program p = new Program(test);
		System.out.println(p);
		p.doRound();
		System.out.println(p);
		System.out.println("SCORE: " + p.getScore());
	}
	
	private static final Map<Integer, Character> characters = new HashMap<Integer, Character>();
	static {
		characters.put(0, 'R');
		characters.put(1, 'G');
		characters.put(2, 'B');
	}
	private char[][] grid;
	private Random rng;
	private int score;
	
	public Program(char[][] grid) { //for testing
		this.grid = grid;
		this.rng = new Random();
		this.score = 0;
	}
	
	public Program() {
		this.grid = new char[4][4];
		this.rng = new Random();
		this.fillGrid();
		this.score = 0;
	}
	
	public void doRound() {
		int score = 1;
		for(int j = 0; j < this.grid.length; j++) {
			for(int k = 0; k < this.grid[j].length; k++) {
				if(this.grid[j][k] == 0)
					continue;
				Set<int[]> coordsToRemove = new HashSet<int[]>();
				this.removePatterns(new int[] {j,  k}, this.grid[j][k], coordsToRemove);
				for(int[] w : coordsToRemove)
					this.grid[w[0]][w[1]] = 0;
				//System.out.println("Coord: " + coordsToRemove.size());
				if(coordsToRemove.size() != 0)
					score *= coordsToRemove.size();
				//System.out.println(j + ", " + k);
				//System.out.println(this);
			}
		}
		this.score += score;
		System.out.println(this);
		this.fallRemaining();
	}
	
	public void fallRemaining() {
		for(int j = 0; j < this.grid.length; j++) {
			next:for(int i = this.grid[j].length - 1; i >= 0; i--) {
				for(int c = this.grid[j].length - 1; c > i; c--) {
					if(this.grid[c][j] == 0) {
						this.grid[c][j] = this.grid[i][j];
						this.grid[i][j] = 0;
						continue next;
					}
				}
			}
		}
	}
	
	public void removePatterns(int[] coords, char value, Set<int[]> coordsToRemove) {
		if(coords[1] != 3 && this.grid[coords[0]][coords[1] + 1] == value && !this.checkIfCoordsInList(coords[0], coords[1] + 1, coordsToRemove)) {
			coordsToRemove.add(coords);
			int[] newCoords = {coords[0], coords[1] + 1};
			coordsToRemove.add(newCoords);
			this.removePatterns(newCoords, value, coordsToRemove);
		}
		if(coords[0] != 3 && this.grid[coords[0] + 1][coords[1]] == value && !this.checkIfCoordsInList(coords[0] + 1, coords[1], coordsToRemove)) {
			coordsToRemove.add(coords);
			int[] newCoords = {coords[0] + 1, coords[1]};
			coordsToRemove.add(newCoords);
			this.removePatterns(newCoords, value, coordsToRemove);
		}
		if(coords[1] != 0 && this.grid[coords[0]][coords[1] - 1] == value && !this.checkIfCoordsInList(coords[0], coords[1] - 1, coordsToRemove)) {
			coordsToRemove.add(coords);
			int[] newCoords = {coords[0], coords[1] - 1};
			coordsToRemove.add(newCoords);
			this.removePatterns(newCoords, value, coordsToRemove);
		}
		if(coords[0] != 0 && this.grid[coords[0] - 1][coords[1]] == value && !this.checkIfCoordsInList(coords[0] - 1, coords[1], coordsToRemove)) {
			coordsToRemove.add(coords);
			int[] newCoords = {coords[0] - 1, coords[1]};
			coordsToRemove.add(newCoords);
			this.removePatterns(newCoords, value, coordsToRemove);
		}
	}
	
	public boolean checkIfCoordsInList(int i, int j, Set<int[]> coordsToRemove) {
		for(int[] c : coordsToRemove)
			if(c[0] == i && c[1] == j)
				return true;
		return false;
	}
	
	private void fillGrid() {
		for(int i = 0; i < this.grid.length; i++)
			for(int j = 0; j < this.grid[i].length; j++)
				if(this.grid[i][j] == 0)
					this.grid[i][j] = characters.get(this.rng.nextInt(3));
	}
	
	@Override
	public String toString() {
		String out = "";
		for(int i = 0; i < this.grid.length; i++) {
			for(int j = 0; j < this.grid[i].length; j++)
				out += this.grid[i][j] + " ";
			out += "\n";
		}
		return out;
	}
	
	public String print() {
		return this.toString();
	}
	
	public int getScore() {
		return this.score;
	}
}
