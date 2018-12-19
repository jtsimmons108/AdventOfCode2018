package problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import utilities.OpCodes;
import utilities.OpCodes.Op;
import utilities.Problem;

public class Day19 extends Problem{

	int boundRegister;
	int[] regs;
	int pointer;
	List<Instruction> instructions;
	
	public Day19() {
		List<String> input = getInput();
		boundRegister = Integer.parseInt(input.get(0).split(" ")[1]);
		instructions = new ArrayList<>();
		for(int i = 1; i < input.size(); i++) {
			String[] split = input.get(i).split(" ");
			instructions.add(new Instruction(split[0], 
												Integer.parseInt(split[1]), 
												Integer.parseInt(split[2]), 
												Integer.parseInt(split[3])));
											
		}
		
		System.out.println(instructions);
	}
	@Override
	public String getPart1Solution() {
		regs = new int[] {0,0,0,0,0,0};
		while(pointer < instructions.size()) {
			regs[boundRegister] = pointer;
			instructions.get(pointer).process(regs);
			pointer = regs[boundRegister];
			pointer++;
		}
		
		return String.valueOf(regs[0]);
	}

	@Override
	public String getPart2Solution() {
		regs = new int[] {1,0,0,0,0,0};
		pointer = 0;
		while(pointer < instructions.size()) {
			regs[boundRegister] = pointer;
			instructions.get(pointer).process(regs);
			pointer = regs[boundRegister];
			pointer++;
		}
		
		return String.valueOf(regs[0]);
	}
	
	
	
	
	class Instruction{
		
		private Op op;
		private String opName;
		private int a, b, c;
		
		public Instruction(String opName, int a, int b, int c) {
			this.opName = opName;
			op = OpCodes.namedOps.get(opName);
			this.a = a;
			this.b = b;
			this.c = c;
		}
		
		public void process(int[] regs) {
			regs[c] = op.operate(regs, a, b);
		}
		
		public String toString() {
			return String.format("%s %d %d %d", opName, a, b, c);
		}
	}
}


