package problems;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import utilities.OpCodes;
import utilities.OpCodes.Op;
import utilities.Problem;

public class Day16 extends Problem{

	private Pattern pattern;
	List<int[]> testNums;
	List<int[]> instructionNums;;
	List<Op> codes;
	
	public Day16() {
		
		pattern = Pattern.compile("\\d+");
		codes = new ArrayList<>();
		
		
		List<String> input = getInput();
		List<String> tests = new ArrayList<>();
		List<String> instructions = new ArrayList<>();
		int i = 0;
		while(i < input.size()) {
			if (input.get(i).startsWith("Before")) {
				tests.addAll(input.subList(i, i + 3));
				i += 4;
			}else {
				if(input.get(i).length() > 0) {
					instructions.add(input.get(i));
				}
				i++;
			}
		}
		testNums = tests.stream()
						.map(line -> pattern.matcher(line)
								.results()
								.map(MatchResult::group)
								.mapToInt(Integer::parseInt)
								.toArray())
								.collect(Collectors.toList());
		instructionNums = instructions.stream()
						.map(line -> pattern.matcher(line)
						.results()
						.map(MatchResult::group)
						.mapToInt(Integer::parseInt)
						.toArray())
						.collect(Collectors.toList());
	}
	
	@Override
	public String getPart1Solution() {
		int total = 0;
		List<Set<Op>> possCodes = new ArrayList<>();
		for(int i = 0; i < 16; i++) {
			possCodes.add(new HashSet<Op>());
		}
		for(int i = 0; i < testNums.size(); i += 3) {
			int count = 0;
			int[] before = testNums.get(i);
			int[] opcodes = testNums.get(i + 1);
			int[] after = testNums.get(i + 2);
			for(Op op : OpCodes.ops) {
				if(OpCodes.processInstruction(before, opcodes[1], opcodes[2], op) == after[opcodes[3]]) {
					count++;
					possCodes.get(opcodes[0]).add(op);
				}
			}
			if(count >= 3) {
				total++;
			}
		}
		boolean removing = true;
		while(removing) {
			removing = false;
			for(int i = 0; i < possCodes.size(); i++) {
				if(possCodes.get(i).size() == 1) {
					Op op = possCodes.get(i).iterator().next();
					for(int j = 0; j < possCodes.size(); j++) {
						if (i != j && possCodes.get(j).remove(op)) {
							removing = true;
						}
					}
				}
			}
		}
		for(int i = 0; i < possCodes.size(); i++) {
			codes.add(possCodes.get(i).iterator().next());
		}
		return String.valueOf(total);
	}

	@Override
	public String getPart2Solution() {
		int[] regs = {0,0,0,0};
		for(int[] ins : instructionNums) {
			regs[ins[3]] = OpCodes.processInstruction(regs, ins[1], ins[2], codes.get(ins[0]));
		}
		return String.valueOf(regs[0]);
	}
	
}
