package problems;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import utilities.Problem;

public class Day1 extends Problem{

	@Override
	public String getPart1Solution() {
		int result = getInput().stream()
				.mapToInt(Integer::parseInt)
				.sum();
		return String.valueOf(result);		
	}

	@Override
	public String getPart2Solution() {
		List<Integer> sigs = getInput().stream()
				.map(Integer::parseInt)
				.collect(Collectors.toList());
		
		Set<Integer> freqs = new HashSet<>();
		int total = 0;
		int index = 0;
		
		while(freqs.add(total)){
			total += sigs.get(index);
			index = (index + 1) % sigs.size();
		}
		return String.valueOf(total);
	}

	
	
	
}
