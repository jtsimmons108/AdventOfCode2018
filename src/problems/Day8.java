package problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import utilities.Problem;

public class Day8 extends Problem{
	
	Iterator<Integer> values;
	Node node;
	public Day8() {
		String[] nums = getInput().get(0).split(" ");
		values = Arrays.stream(nums)
					.mapToInt(Integer::parseInt)
					.iterator();
		node = getNode();
	}
	
	
	private Node getNode() {
		Node node = new Node();
		int c = values.next();
		int m = values.next();
		for(int i = 0; i < c; i++) {
			node.children.add(getNode());
		}
		for(int i = 0; i < m; i++) {
			node.meta.add(values.next());
		}
		return node;
		
	}
	
	@Override
	public String getPart1Solution() {
		return String.valueOf(node.getMetaSum());
	}

	
	@Override
	public String getPart2Solution() {
		return String.valueOf(node.getValue());
	}

}

class Node{
	
	protected List<Integer> meta;
	protected List<Node> children;
	
	public Node() {
		meta = new ArrayList<>();
		children = new ArrayList<>();
	}
	
	public int getMetaSum() {
		int sum = 0;
		for(int m : meta) {
			sum += m;
		}
		for(Node c : children) {
			sum += c.getMetaSum();
		}
		
		return sum;
	}
	
	public int getValue() {
		if(children.size() == 0) {
			return getMetaSum();
		}
		int value = 0;
		for(int m : meta) {
			if ( m > 0 && m <= children.size()) {
				value += children.get(m - 1).getValue();
			}
		}
		return value;
	}
}