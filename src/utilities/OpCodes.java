package utilities;

import java.util.HashMap;
import java.util.Map;

public class OpCodes {

	
	
	public static int processInstruction(int[]regs, int a, int b, Op op) {
		return op.operate(regs, a, b);
	}
	
	public interface Op{
		int operate(int[] regs, int a, int b);
	}
	
	public static final Op addr = (regs, a, b) -> regs[a] + regs[b];
	public static final Op addi = (regs, a, b) -> regs[a] + b;
	public static final Op mulr = (regs, a, b) -> regs[a] * regs[b];
	public static final Op muli = (regs, a, b) -> regs[a] * b;
	public static final Op banr = (regs, a, b) -> regs[a] & regs[b];
	public static final Op bani = (regs, a, b) -> regs[a] & b;
	public static final Op borr = (regs, a, b) -> regs[a] | regs[b];
	public static final Op bori = (regs, a, b) -> regs[a] | b;
	public static final Op setr = (regs, a, b) -> regs[a];
	public static final Op seti = (regs, a, b) -> a;
	public static final Op gtir = (regs, a, b) -> a > regs[b] ? 1 : 0;
	public static final Op gtri = (regs, a, b) -> regs[a] > b ? 1 : 0;
	public static final Op gtrr = (regs, a, b) -> regs[a] > regs[b] ? 1 : 0;
	public static final Op eqir = (regs, a, b) -> a == regs[b] ? 1 : 0;
	public static final Op eqri = (regs, a, b) -> regs[a] == b ? 1 : 0;
	public static final Op eqrr = (regs, a, b) -> regs[a] == regs[b] ? 1 : 0;
	
	
	public static final Op[] ops = new Op[]{addr, addi, mulr, muli, banr, bani, borr, bori, 
			setr, seti, gtir, gtri, gtrr, eqir, eqri, eqrr};
	
	public static final Map<String, Op> namedOps;

	static {
		
		namedOps = new HashMap<>();
		namedOps.put("addr", addr);
		namedOps.put("addi", addi);
		namedOps.put("mulr", mulr);
		namedOps.put("muli", muli);
		namedOps.put("banr", banr);
		namedOps.put("bani", bani);
		namedOps.put("borr", borr);
		namedOps.put("bori", bori);
		namedOps.put("setr", setr);
		namedOps.put("seti", seti);
		namedOps.put("gtir", gtir);
		namedOps.put("gtri", gtri);
		namedOps.put("gtrr", gtrr);
		namedOps.put("eqir", eqir);
		namedOps.put("eqri", eqri);
		namedOps.put("eqrr", eqrr);
		
	}

	
}
