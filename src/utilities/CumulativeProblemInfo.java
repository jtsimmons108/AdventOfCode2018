package utilities;

import problems.Day1;
import problems.Day2;

public class CumulativeProblemInfo {

	
	
	
	public static void main(String[] args) {
		Problem[] days = {new Day1(),
							new Day2()
							
		};
		
		for (Problem day : days) {
			System.out.println(String.format("%s Part 1: %.2f ms", day.getClass().getSimpleName(), day.getAverageRunTimePart1(1)));
			System.out.println(String.format("%s Part 2: %.2f ms", day.getClass().getSimpleName(), day.getAverageRunTimePart2(1)));
		}
		
		
		long start = System.nanoTime();
		for(Problem day : days) {
			day.getPart1Solution();
			day.getPart2Solution();
		}
		System.out.println(String.format("Total Run Time: %.2f ms", (System.nanoTime() - start) / 1000000.));
		
	}
	
	
	
}
