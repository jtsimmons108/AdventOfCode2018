package problems;

import java.util.List;

import utilities.Problem;

public class Day2 extends Problem{

	@Override
	public String getPart1Solution() {
		List<String> ids = getInput();
		int doubles = 0;
		int triples = 0;
		int[] counts;

		for (String id : ids) {
			counts = new int[26];
			for(char c : id.toCharArray()) {
				counts[c - 'a']++;
			}
			if(contains(counts, 2))
				doubles++;
			if(contains(counts, 3))
				triples++;
		}


		return String.format("%d", doubles*triples);
	}

	private boolean contains(int[] counts, int target) {
		for(int count : counts) {
			if(count == target) {
				return true;
			}
		}
		return false;
	}


	@Override
	public String getPart2Solution() {
		List<String> ids = getInput();
		for(int i = 0; i < ids.size(); i++) {
			for(int j = i + 1; j < ids.size(); j++) {
				if (getDifferentCharacterCount(ids.get(i), ids.get(j)) == 1) {
					String first = ids.get(i);
					String second = ids.get(j);
					StringBuilder finalId = new StringBuilder(first.length());
					for(int k = 0; k < first.length(); k++) {
						if(first.charAt(k) == second.charAt(k)) {
							finalId.append(first.charAt(k));
						}
					}
					return finalId.toString();
				}
			}
		}

		return null;
	}


	private int getDifferentCharacterCount(String firstId, String secondId) {
		int different = 0;
		for(int i = 0; i < firstId.length(); i++) {
			if(firstId.charAt(i) != secondId.charAt(i)) {
				different++;
			}
		}
		return different;
	}

}
