package britishOlympiad.gameOfLife;

import java.io.BufferedReader;
import java.io.IOException;

public class Grid {
	private boolean[][] grid;
	public Grid() {
		this.grid = new boolean[11][11];
	}
	
	public void input(BufferedReader br) throws IOException {
		for(int i = 3; i < 8; i++) {
			char[] in = br.readLine().toCharArray();
			for(int j = 3; j < 8; j++)
				this.grid[i][j] = (in[j-3] == '.')? false:true;
		}
	}
	
	public void setCell(boolean state, int x, int y) {
		this.grid[x][y] = state;
	}
	
	public boolean getCell(int x, int y) {
		return this.grid[x][y];
	}
	
	public boolean[][] getGrid() {
		return this.grid;
	}
	
	public void setGrid(boolean[][] grid) {
		this.grid = grid;
	}
	
	public void print() {
		String out = "";
		for(boolean[] line : this.grid) {
			for(boolean cell : line)
				out += (cell)? "0":".";
			out += "\n";
		}
		System.out.println(out);
	}
}