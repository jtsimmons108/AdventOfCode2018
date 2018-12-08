package problems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import utilities.Problem;

public class Day4 extends Problem{

	private Map<Integer, List<Integer>> guards;
	private Pattern pattern;
	
	public Day4() {
		guards = new HashMap<>();
		pattern = Pattern.compile("\\d+");
		List<String> input = getInput();
		Collections.sort(input);
		
		int i = 0;
		int currentGuard = 0;
		
		while (i < input.size()) {
			List<Integer> ints = getNumsFromString(input.get(i));
			if(ints.size() == 6) {
				currentGuard = ints.get(ints.size() - 1);
				if(!guards.containsKey(currentGuard)) {
					guards.put(currentGuard, new ArrayList<>(Collections.nCopies(60, 0)));
				}
				i++;
			}else {
				List<Integer> nextInts = getNumsFromString(input.get(i + 1));
				for(int j = ints.get(ints.size() - 1); j < nextInts.get(nextInts.size() - 1); j++) {
					guards.get(currentGuard).set(j, guards.get(currentGuard).get(j) + 1);
				}
				i += 2;
			}					
		}
		
	}
	@Override
	public String getPart1Solution() {
		int guard = 0;
		int max = 0;
		for(int g : guards.keySet()) {
			int total = guards.get(g).stream().mapToInt(Integer::intValue).sum();
			if(total > max) {
				max = total;
				guard = g;
			}
		}
		int minute = guards.get(guard)
				.indexOf(guards.get(guard).stream().max(Integer::compare).get().intValue());
		return String.valueOf(guard * minute);
		
	}

	@Override
	public String getPart2Solution() {
		int max = 0;
		int guard = 0;
		
		for(int g : guards.keySet()) {
			int gMax = guards.get(g).stream().max(Integer::compare).get().intValue();
			if(gMax > max) {
				guard = g;
				max = gMax;
			}
		}

		int minute = guards.get(guard)
				.indexOf(guards.get(guard).stream().max(Integer::compare).get().intValue());
		
		return String.valueOf(minute * guard);
	}
	
	
	private List<Integer> getNumsFromString(String line){
		return pattern.matcher(line)
					.results()
					.map(MatchResult::group)
					.map(Integer::parseInt)
					.collect(Collectors.toList());
	}

}
