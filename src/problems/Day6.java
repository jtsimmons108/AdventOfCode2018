package problems;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import utilities.Point;
import utilities.Problem;

public class Day6 extends Problem{

	List<Point> originalPoints;
	
	private final int SIZE = 350;
	int[][] grid;
	
	public Day6() {
		originalPoints = getInput().stream()
			.map(line -> getPointFromString(line))
			.collect(Collectors.toList());
		grid = new int[SIZE][SIZE];
	}
	
	public int[][] getGrid() {
		return grid;
	}
	@Override
	public String getPart1Solution() {
		
		for(int x = 0; x < grid.length; x++) {
			for(int y = 0; y < grid[0].length; y++) {
				List<Integer> closest = getClosestPoints(new Point(x, y));
				if(closest.size() > 1) {
					grid[y][x] = -1;
				}else {
					grid[y][x] = closest.get(0);
				}
			}
		}
		
		//Option 1
		int max = IntStream.range(0, originalPoints.size())
					.filter(i -> !isTouchingBorder(i))
					.map(i -> countInGrid(i))
					.max().getAsInt();
		
		
		return String.valueOf(max);
		
	}
	

	@Override
	public String getPart2Solution() {
		int count = 0;
		for(int x = 0; x < grid.length; x++) {
			for(int y = 0; y < grid.length; y++) {
				Point p = new Point(x, y);
				if(originalPoints.stream().mapToInt(point -> p.getTaxiCabDistance(point)).sum() < 10000) {
					count++;
				}
			}
		}
		return String.valueOf(count);
		
	}

	private Point getPointFromString(String line) {
		String[] points = line.split(", ");
		return new Point(Integer.parseInt(points[0]), Integer.parseInt(points[1]));
	}
	
	
	private List<Integer> getClosestPoints(Point p){
		List<Integer> closest = new ArrayList<>();
		int least = p.getTaxiCabDistance(originalPoints.get(0));
		closest.add(0);
		
		for(int i = 1; i < originalPoints.size(); i++) {
			Point other = originalPoints.get(i);
			if(p.getTaxiCabDistance(other) < least) {
				least = p.getTaxiCabDistance(other);
				closest.clear();
				closest.add(i);
			}else if(p.getTaxiCabDistance(other) == least) {
				closest.add(i);
			}
		}
		return closest;
	}
	
	private boolean isTouchingBorder(int selection) {
		for(int i = 0; i < grid.length; i++) {
			if(grid[0][i] == selection || grid[grid.length - 1][i] == selection 
					|| grid[i][0] == selection || grid[i][grid.length - 1] == selection) {
				return true;
			}
		}
		return false;
	}
	
	private int countInGrid(int selection) {
		int total = 0;
		for(int[] row : grid) {
			for (int val : row) {
				if (val == selection) 
					total++;
			}
		}
		return total;
	}
	
}
