package britishOlympiad.gameOfLife;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GameOfLife {
	private static Grid grid;
	private static int gen;
	public static void main(String... args) throws IOException {
		grid = new Grid();
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			grid.input(br);
			grid.print();
			
			String input;
			while(!(input = br.readLine()).equals("-1")) {
				switch(input.charAt(0))
				{
				case '+':
					for(int i = 0; i < Integer.parseInt(input.substring(1, input.length())); i++)
						simulate();
					break;
				case '#':
					for(int i = gen; i < Integer.parseInt(input.substring(1, input.length())); i++)
						simulate();
					break;
				}
				grid.print();
			}
		}
	}
	
	private static void simulate() {
		boolean[][] currentGrid = grid.getGrid();
		boolean[][] nextGrid = new boolean[11][11];
		for(int i = 0; i < currentGrid.length; i++) {
			for(int j = 0; j < currentGrid[i].length; j++) {
				int ons = getNumberOfAdjacentOns(i, j);
				if((currentGrid[i][j] && ons == 2 || ons == 3) || (!currentGrid[i][j] && ons == 3))
					nextGrid[i][j] = true;
			}
		}
		grid.setGrid(nextGrid);
	}
	
	private static int getNumberOfAdjacentOns(int x, int y) { //11x11 grid
		int out = 0;
		if(x != 10 && grid.getCell(x + 1, y)) out++;
		if(x != 10 && y != 10 && grid.getCell(x + 1, y + 1)) out++;
		if(x != 10 && y != 0 && grid.getCell(x + 1, y - 1)) out++;
		if(y != 10 && grid.getCell(x, y + 1)) out++;
		if(y != 0 && grid.getCell(x, y - 1)) out++;
		if(x != 0 && grid.getCell(x - 1, y)) out++;
		if(x != 0 && y != 10 && grid.getCell(x - 1, y + 1)) out++;
		if(x != 0 && y != 0 && grid.getCell(x - 1, y - 1)) out++;
		return out;
	}
}
