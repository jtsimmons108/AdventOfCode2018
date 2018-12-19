package problems;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

import utilities.MovingPoint;
import utilities.Problem;

public class Day10 extends Problem{

	private Pattern pattern;
	public List<MovingPoint> points;
	
	
	public Day10() {
		pattern = Pattern.compile("position=<\\s*(-?\\d+),\\s+(-?\\d+)>\\s+velocity=<\\s*(-?\\d+),\\s+(-?\\d+)>");
		points = getPointsFromInput();
	}
	
	private List<MovingPoint> getPointsFromInput(){
		List<MovingPoint> points = new ArrayList<>();
		getInput().forEach(line -> {
			Matcher match = pattern.matcher(line);
			if(match.find()) {
				int x = Integer.parseInt(match.group(1));
				int y = Integer.parseInt(match.group(2));
				int vx = Integer.parseInt(match.group(3));
				int vy = Integer.parseInt(match.group(4));
				points.add(new MovingPoint(x, y, vx, vy));
			}
		});
		return points;
	}
	
	@Override
	public String getPart1Solution() {
		int toMove = Integer.parseInt(getPart2Solution());
		points = getPointsFromInput();
		moveAllPoints(toMove);
		int minX = points.stream().mapToInt(p -> p.x).min().getAsInt();
		int maxX = points.stream().mapToInt(p -> p.x).max().getAsInt();
		int minY = points.stream().mapToInt(p -> p.y).min().getAsInt();
		int maxY = points.stream().mapToInt(p -> p.y).max().getAsInt();
		System.out.printf("%d %d %d %d\n", minX, maxX, minY, maxY);
		
		char[][] values = new char[maxY - minY + 1][maxX - minX + 1];
		for(int r = 0; r < values.length; r++) {
			for(int c = 0; c < values.length; c++) {
				values[r][c] = ' ';
			}
		}
		
		for(MovingPoint p : points) {
			values[p.y - minY][p.x - minX] = '#';
		}
		StringBuilder builder = new StringBuilder();
		for(char[] row : values) {
			for (char c : row) {
				builder.append(c);
			}
			builder.append("\n");
		}
		
		return builder.toString();
	}
	
	@Override
	public String getPart2Solution() {
		points = getPointsFromInput();
		int t = 0;
		int variation = getYVariation();
		moveAllPoints();
		int nextVariation = getYVariation();
		while(variation > nextVariation) {
			variation = nextVariation;
			moveAllPoints();
			nextVariation = getYVariation();
			t++;
		}		
		return String.valueOf(t);
	}

	private void moveAllPoints() {
		for(MovingPoint p : points) {
			p.move();
		}
	}
	
	private void moveAllPoints(int times) {
		for(MovingPoint p : points) {
			p.move(times);
		}
	}
	
	
	private int getYVariation() {
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		for(MovingPoint p : points) {
			if (p.y > max) {
				max = p.y;
			}
			if(p.y < min) {
				min = p.y;
			}
		}
		return max - min;
	}
	
	
	

}
