package utilities;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

public abstract class Problem {

	List<String> input;
	
	public Problem(int day) {
		String fileName = String.format("Inputs/Day%d.txt", day);
		File file = new File(fileName);
		try {
			input = FileUtils.readLines(file, "UTF-8");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public abstract void solvePart1();
	
	public abstract void solvePart2();
	
	
	public List<String> getInput(){
		return input;
	}
}
