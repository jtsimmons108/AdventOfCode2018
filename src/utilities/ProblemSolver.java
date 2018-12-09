package utilities;

import problems.Day9;

public class ProblemSolver {

	public static void main(String[] args) {
		
		Problem currentDay = new Day9();
		
		System.out.println(currentDay.getPart1Solution());
		System.out.println(currentDay.getPart2Solution());

		System.out.println(String.format("Average run time: %.2f ms", 
				currentDay.getAverageRunTimePart1(10)));
		System.out.println(String.format("Average run time: %.2f ms", 
				currentDay.getAverageRunTimePart2(10)));
		
		
		
	}
}
