package utilities;

import problems.Day1;

public class ProblemSolver {

	public static void main(String[] args) {
		
		Problem currentDay = new Day1();
		
		for(int i = 0; i < 10; i++) {
			long start = System.currentTimeMillis();
			currentDay.solvePart1();
			System.out.printf("Time: %d\n", System.currentTimeMillis() - start);
		}
		
		for(int i = 0; i < 10; i++) {
			long start = System.currentTimeMillis();
			currentDay.solvePart2();
			System.out.printf("Time: %d\n", System.currentTimeMillis() - start);
		}
		
		
	}
}
