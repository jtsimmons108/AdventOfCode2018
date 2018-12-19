package problems;

import utilities.Problem;

public class Day5 extends Problem{

	private String polymer;
	
	public Day5() {
		polymer = getInput().get(0);
	}
	
	@Override
	public String getPart1Solution() {
		return String.valueOf(react(polymer)
				.length());
	}

	@Override
	public String getPart2Solution() {
		int least = Integer.MAX_VALUE;
		for (int i = 0; i < 26; i++) {
			String testPolymer = polymer.replaceAll("[" + (char)('A' + i) + (char)('a' + i) +"]", "");
			String result = react(testPolymer);
			least = Math.min(result.length(), least);
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
