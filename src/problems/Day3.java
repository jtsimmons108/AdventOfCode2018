package problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import utilities.Problem;

public class Day3 extends Problem{

	private int[][] fabric;
	private List<int[]> tickets;
	public Day3(){
		List<String> cuts = getInput();
		fabric = new int[1000][1000];
		tickets = new ArrayList<>();
		Pattern ticketPattern = Pattern
				.compile("#(\\d+) @ (\\d+),(\\d+): (\\d+)x(\\d+)");
		for(String cut : cuts) {
			Matcher match = ticketPattern.matcher(cut);
			if(match.find()) {
				int[] guides = {Integer.parseInt(match.group(2)),
						Integer.parseInt(match.group(3)),
						Integer.parseInt(match.group(4)),
						Integer.parseInt(match.group(5))};
				tickets.add(guides);
			}
		}

	}

	@Override
	public String getPart1Solution() {
		for(int[] guide : tickets) {
			for(int x = guide[0]; x < guide[0] + guide[2]; x++) {
				for(int y = guide[1]; y < guide[1] + guide[3]; y++) {
					fabric[y][x]++;
				}
			}
		}
		int squares = 0;
		for(int x = 0; x < fabric.length; x++) {
			for(int y = 0; y < fabric[0].length; y++) {
				squares += fabric[y][x] > 1 ? 1 : 0;
			}
		}
		return String.valueOf(squares);
	}

	@Override
	public String getPart2Solution() {
		for(int i = 0; i < tickets.size(); i++) {
			int[] guide = tickets.get(i);
			boolean isUnique = true;
			for(int x = guide[0]; x < guide[0] + guide[2]; x++) {
				for(int y = guide[1]; y < guide[1] + guide[3]; y++) {
					if (fabric[y][x] > 1) {
						isUnique = false;
					}
				}
			}
			if (isUnique) {
				return String.valueOf(i + 1);
			}
		}
		return null;
	}

}
