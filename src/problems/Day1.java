package problems;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import utilities.Problem;

public class Day1 extends Problem{

	public Day1() {
		super(1);
	}

	@Override
	public void solvePart1() {
		int result = getInput().stream()
				.mapToInt(num -> Integer.parseInt(num))
				.sum();
		System.out.println(result);			
	}

	@Override
	public void solvePart2() {
		List<Integer> sigs = getInput().stream()
				.map(num -> Integer.parseInt(num))
				.collect(Collectors.toList());
		
		Set<Integer> freqs = new HashSet<>();
		int total = 0;
		int index = 0;
		
		while(freqs.add(total)){
			total += sigs.get(index);
			index = (index + 1) % sigs.size();
		}
		System.out.println(total);
	}

	
	
	
}
