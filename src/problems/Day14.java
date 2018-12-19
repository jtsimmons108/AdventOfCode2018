package problems;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import utilities.Problem;

public class Day14 extends Problem {

	private List<Integer> recipes;
	private int e1 = 0, e2 = 1;
	int target = 170641;
	@Override
	public String getPart1Solution() {
		recipes = new ArrayList<>();
		recipes.add(3);
		recipes.add(7);
		while(recipes.size() < target + 10) {
			int score1 = recipes.get(e1);
			int score2 = recipes.get(e2);
			if((score1 + score2) >= 10) {
				recipes.add((score1 + score2) / 10);
			}
			recipes.add((score1 + score2) % 10);
			e1 = (e1 + score1 + 1) % recipes.size();
			e2 = (e2 + score2 + 1) % recipes.size();
		}
		StringBuilder builder = new StringBuilder(10);
		for(int i = 0; i < 10; i++) {
			builder.append(recipes.get(target + i));
		}
		return builder.toString();
	}

	@Override
	public String getPart2Solution() {
		recipes = new ArrayList<>();
		recipes.add(3);
		recipes.add(7);
		int target = 30000000;
		while(recipes.size() < target + 10) {
			int score1 = recipes.get(e1);
			int score2 = recipes.get(e2);
			if((score1 + score2) >= 10) {
				recipes.add((score1 + score2) / 10);
			}
			recipes.add((score1 + score2) % 10);
			e1 = (e1 + score1 + 1) % recipes.size();
			e2 = (e2 + score2 + 1) % recipes.size();
		}
		for(int i = 0; i < recipes.size() - 6; i++) {
			if(recipes.get(i) == 1 && recipes.get(i + 1) == 7 
					&& recipes.get(i + 2) == 0 && recipes.get(i + 3) == 6
					&& recipes.get(i + 4) == 4 && recipes.get(i + 5) == 1) {
				return String.valueOf(i);
			}
		}
		return null;
	}




}
