package problems;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import utilities.Problem;

public class Day18 extends Problem{

	List<String> input;
	char[][] grid;
	final char OPEN = '.', TREE = '|', LUMBERYARD = '#';
	
	public Day18() {
		input = getInput();
		grid = new char[input.size()][];
		for(int i = 0; i < input.size(); i++) {
			grid[i] = input.get(i).toCharArray();
		}
		
	}
	
	
	
	@Override
	public String getPart1Solution() {
//		for(int i = 0; i < 10; i++) {
//			char[][] nextGrid = new char[grid.length][grid[0].length];
//			for(int r = 0; r < grid.length; r++) {
//				for(int c = 0; c < grid.length; c++) {
//					char current = grid[r][c];
//					char next = grid[r][c];
//					if(current == OPEN && getNeighborCount(r, c, TREE) >= 3) {
//						next = TREE;
//					}else if(current == TREE && getNeighborCount(r, c, LUMBERYARD) >= 3) {
//						next = LUMBERYARD;
//					}else if (current == LUMBERYARD && (getNeighborCount(r, c, LUMBERYARD) == 0 
//															|| getNeighborCount(r, c, TREE) == 0)) {
//						next = OPEN;
//					}
//					nextGrid[r][c] = next;
//				}
//			}
//			grid = nextGrid;
//		}
//		Map<Character, Integer> counts = getPlotCounts();
//		return String.valueOf(counts.get(TREE) * counts.get(LUMBERYARD));
		return null;
	}

	@Override
	public String getPart2Solution() {	
		Set<Integer> seen = new HashSet<>();
		for(int i = 0; i < 100000; i++) {
			char[][] nextGrid = new char[grid.length][grid[0].length];
			for(int r = 0; r < grid.length; r++) {
				for(int c = 0; c < grid.length; c++) {
					char current = grid[r][c];
					char next = grid[r][c];
					if(current == OPEN && getNeighborCount(r, c, TREE) >= 3) {
						next = TREE;
					}else if(current == TREE && getNeighborCount(r, c, LUMBERYARD) >= 3) {
						next = LUMBERYARD;
					}else if (current == LUMBERYARD && (getNeighborCount(r, c, LUMBERYARD) == 0 
															|| getNeighborCount(r, c, TREE) == 0)) {
						next = OPEN;
					}
					nextGrid[r][c] = next;
				}
			}
			grid = nextGrid;
			Map<Character, Integer> counts = getPlotCounts();
			int resourceValue = counts.get(TREE) * counts.get(LUMBERYARD);
			String repeat = "";
			if(!seen.add(resourceValue)) {
				repeat = "repeat";
			}
			System.out.println(i + 1 + " -> " + resourceValue + " " + repeat);
		}
		
		return null;
		
	}
	
	private int getNeighborCount(int r, int c, char neighbor) {
		int total = 0;
		for(int dr = -1; dr <= 1; dr++) {
			for(int dc = -1; dc <= 1; dc++) {
				if(dr != 0 || dc != 0) {
					if(isValidLocation(r + dr, c + dc) && grid[r + dr][c + dc] == neighbor) {
						total++;
					}
				}
			}
		}
		return total;
	}
	
	private boolean isValidLocation(int r, int c) {
		return r >= 0 && r < grid.length && c >= 0 && c < grid[0].length;
	}

	private void viewGrid() {
		StringBuilder builder = new StringBuilder(grid.length * grid[0].length);
		for(char[] row : grid) {
			for (char c : row) {
				builder.append(c);
			}
			builder.append('\n');
		}
		System.out.println(builder);
	}
	
	private Map<Character, Integer> getPlotCounts(){
		Map<Character, Integer> counts = new HashMap<>();
		for(char[] row : grid) {
			for(char c : row) {
				counts.put(c, counts.getOrDefault(c, 0) + 1);
			}
		}
		
		return counts;
	}
}
