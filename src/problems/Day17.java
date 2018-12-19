package problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;

import utilities.ClayWall;
import utilities.Problem;

public class Day17 extends Problem{


	List<ClayWall> walls;
	char[][] grid;
	int minx, maxx, miny, maxy;
	final char DRIPPING = '|', SETTLED = '~', WALL = '#', SAND = '.', SPOUT = '+';
	
	public Day17() {
		walls = new ArrayList<>();
		for(String line : getInput()) {
			walls.add(new ClayWall(line));
		}
		findBounds();
		setUpGrid();
		
	}
	@Override
	public String getPart1Solution() {
		drip(500, 1);
		viewGrid();
		int count = 0;
		for(int r = miny; r <= maxy; r++) {
			for(int c = 0; c < grid[0].length; c++) {
				if(grid[r][c] == SETTLED || grid[r][c] == DRIPPING){
					count++;
				}
			}
		}
		return String.valueOf(count);
	}

	@Override
	public String getPart2Solution() {
		int count = 0;
		for(int r = miny; r <= maxy; r++) {
			for(int c = 0; c < grid[0].length; c++) {
				if(grid[r][c] == SETTLED){
					count++;
				}
			}
		}
		return String.valueOf(count);
	}


	private void findBounds() {
		minx = walls.stream().mapToInt(wall -> wall.startX).min().getAsInt();
		maxx = walls.stream().mapToInt(wall -> wall.endX).max().getAsInt();
		miny = walls.stream().mapToInt(wall -> wall.startY).min().getAsInt();
		maxy = walls.stream().mapToInt(wall -> wall.endY).max().getAsInt();

	}

	private void setUpGrid() {
		grid = new char[maxy + 1][maxx - minx + 11];
		for(int r = 0; r < grid.length; r++) {
			for(int c = 0; c < grid[0].length; c++) {
				grid[r][c] = SAND;
			}
		}
		for(ClayWall w : walls) {
			for(int x = w.startX; x <= w.endX; x++) {
				for(int y = w.startY; y <= w.endY; y++) {
					grid[y][x - minx + 5] = WALL;
				}
			}
		}

		grid[0][500 - minx + 5] = SPOUT;
	}

	private void viewGrid() {
		StringBuilder builder = new StringBuilder(grid.length * grid[0].length);
		for(char[] row : grid) {
			for (char c : row) {
				builder.append(c == SAND ? ' ' : c);
			}
			builder.append('\n');
		}
		System.out.println(builder);
	}

	
	
	private void drip(int x, int y) {
		if (y == grid.length)return;
		setChar(x, y, DRIPPING);
		if( y == grid.length - 1) return;
		
		if(getChar(x, y + 1) == SAND) {
			drip(x, y + 1);
		}
		
		if(isSupported(x, y)) {
			flowHorizontally(x, y);
		}
		
	}
	
	
	private void flowHorizontally(int x, int y) {
		int dxr = 1;
		while(isValidLocation(x + dxr, y) && getChar(x + dxr, y) != WALL && isSupported(x + dxr, y)) {
			dxr++;
		}
		int dxl = -1;
		while(isValidLocation(x + dxl, y) && getChar(x + dxl, y) != WALL && isSupported(x + dxl, y)) {
			dxl--;
		}
		char fill;
		if(getChar(x + dxl, y) == WALL && getChar(x + dxr, y) == WALL) {
			fill = SETTLED;
		}else {
			fill = DRIPPING;
		}
		
		for(int c = x + dxl + 1; c < x + dxr; c++) {
			setChar(c, y, fill);
		}
		if(getChar(x + dxr, y) == SAND) {
			drip(x + dxr, y);
		}
		if(getChar(x + dxl, y) == SAND) {
			drip(x + dxl, y);
		}
	}
	
	private char getChar(int x, int y) {
		return grid[y][x - minx + 5];
	}
	
	private void setChar(int x, int y, char c) {
		grid[y][x - minx + 5] = c;
	}
	
	private boolean isSupported(int x, int y) {
		char c = getChar(x, y + 1);
		return c == WALL || c == SETTLED;
	}
	
	private boolean isValidLocation(int x, int y) {
		return x >= minx - 1 && x <= maxx + 1 && y >= 0 && y <= grid.length;
	}
}