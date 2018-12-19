package utilities;

import problems.Day1;
import problems.Day10;
import problems.Day2;
import problems.Day3;
import problems.Day4;
import problems.Day5;
import problems.Day6;
import problems.Day7;
import problems.Day8;
import problems.Day9;

public class CumulativeProblemInfo {

	
	
	
	public static void main(String[] args) {
		Problem[] days = {new Day1(),
							new Day2(),
							new Day3(),
							new Day4(),
							new Day5(),
							new Day6(),
							new Day7(), 
							new Day8(),
							new Day9()
							
		};
		
		for (Problem day : days) {
			System.out.println(String.format("%s Part 1: %.2f ms", day.getClass().getSimpleName(), day.getAverageRunTimePart1(1)));
			System.out.println(String.format("%s Part 2: %.2f ms", day.getClass().getSimpleName(), day.getAverageRunTimePart2(1)));
		}
		
		
	}
	
	
	
}
