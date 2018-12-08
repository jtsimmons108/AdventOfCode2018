package problems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import utilities.Problem;

public class Day7 extends Problem{

	private Map<String, AssemblyNode> nodes;
	
	
	public Day7() {
		nodes = new HashMap<>();
		for(String line : getInput()) {
			String[] info = line.split(" ");
			String parent = info[1];
			String child = info[info.length - 3];
			if(!nodes.containsKey(parent)) {
				nodes.put(parent, new AssemblyNode(parent));
			}
			if(!nodes.containsKey(child)) {
				nodes.put(child, new AssemblyNode(child));
			}
			nodes.get(parent).addDependency(nodes.get(child));
			nodes.get(child).addRequirement(nodes.get(parent));
		}
	}

	@Override
	public String getPart1Solution() {
		List<AssemblyNode> working = new ArrayList<>();
		StringBuilder result = new StringBuilder(nodes.keySet().size());
		for(String s : nodes.keySet()) {
			if (nodes.get(s).getNumRequirements() == 0) {
				working.add(nodes.get(s));
			}
		}
		Collections.sort(working);
		
		while(working.size() > 0) {
			AssemblyNode node = working.remove(0);
			node.complete();
			result.append(node.getValue());
			for(AssemblyNode other : node.getDepencies()) {
				if(other.canWorkOn()) {
					working.add(other);
				}
			}
			Collections.sort(working);
		}
		
		return result.toString();
	}

	@Override
	public String getPart2Solution() {
		for(AssemblyNode node : nodes.values()) {
			node.reset();
		}
		List<AssemblyNode> working = new ArrayList<>();
		List<AssemblyNode> available = new ArrayList<>();
		final int LIMIT = 5;
		
		for(String s : nodes.keySet()) {
			if (nodes.get(s).getNumRequirements() == 0) {
				available.add(nodes.get(s));
			}
		}
		Collections.sort(available);
		int time = 0;
		while(working.size() > 0 || available.size() > 0) {
			int toAdd = Math.min(LIMIT - working.size(), available.size());
			for(int i = 0; i < toAdd; i++) {
				working.add(available.remove(0));
			}
			boolean added = false;
			for(int i = working.size() - 1; i >= 0; i--) {
				working.get(i).work();
				if(working.get(i).isComplete()) {
					for(AssemblyNode node : working.get(i).getDepencies()) {
						if(node.canWorkOn()) {
							available.add(node);
							added = true;
						}
					}
					working.remove(i);
				}
			}
			if(added) {
				Collections.sort(available);
			}
			time++;
		}
		
		return String.valueOf(time);
	}

}

class AssemblyNode implements Comparable<AssemblyNode>
{
	private String value;
	private List<AssemblyNode> dependencies;
	private List<AssemblyNode> requirements;
	private boolean complete;
	private int timeToComplete;


	public AssemblyNode(String value) {
		this.value = value;
		timeToComplete = value.charAt(0) - 'A' + 61;
		dependencies = new ArrayList<>();
		requirements = new ArrayList<>();
	}

	public boolean canWorkOn() {
		for(AssemblyNode node : requirements) {
			if(!node.complete) {
				return false;
			}
		}
		return true;
	}
	
	public void work() {
		timeToComplete--;
		if(timeToComplete == 0) {
			complete = true;
		}
	}

	public String getValue() {
		return value;
	}
	public int getNumRequirements() {
		return requirements.size();
	}

	public void addDependency(AssemblyNode node) {
		dependencies.add(node);
	}
	
	public void addRequirement(AssemblyNode node) {
		requirements.add(node);
	}
	public List<AssemblyNode> getDepencies(){
		return dependencies;
	}

	public boolean isComplete() {
		return complete;
	}

	public void complete() {
		complete = true;
	}

	public void reset() {
		complete = false;
	}

	@Override
	public int compareTo(AssemblyNode o) {
		return value.compareTo(o.value);
	}

	@Override
	public String toString() {
		return "AssemblyNode [value=" + value + ", complete=" + complete + ", timeToComplete=" + timeToComplete + "]";
	}
}
