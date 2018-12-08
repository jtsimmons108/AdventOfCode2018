package problems;

import utilities.Problem;

public class Day5 extends Problem{

	private String polymer;
	
	public Day5() {
		polymer = getInput().get(0);
	}
	
	@Override
	public String getPart1Solution() {
		return String.valueOf(react(polymer).length());
	}

	@Override
	public String getPart2Solution() {
		String alphabet = "abcdefghijklmopqrstuvwxyz";
		int least = Integer.MAX_VALUE;
		for (char c : alphabet.toCharArray()) {
			String testPolymer = polymer.replaceAll("[" + c + Character.toUpperCase(c)+"]", "");
			String result = react(testPolymer);
			if(result.length() < least) {
				least = result.length();
			}
		}
		return String.valueOf(least);
	}
	
	
	private String react(String polymer) {
		StringBuilder result = new StringBuilder();
		for (char c : polymer.toCharArray()) {
			if (result.length() > 0 && Math.abs(result.charAt(result.length() - 1) - c) == 32) {
				result.deleteCharAt(result.length() - 1);
			}else {
				result.append(c);
			}
		}
		return result.toString();

	}
}
