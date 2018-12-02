package utilities;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;

public abstract class Problem {

	List<String> input;

	public Problem() {
		String fileName = String.format("Inputs/%s.txt", getClass().getSimpleName());
		File file = new File(fileName);
		try {
			input = FileUtils.readLines(file, "UTF-8");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public abstract String getPart1Solution();

	public abstract String getPart2Solution();

	public double getAverageRunTimePart1(int times) {
		long total = 0;
		for(int i = 0; i < times; i++)
		{
			long start = System.nanoTime();
			getPart1Solution();
			total += System.nanoTime() - start;
		}
		return total / (double)times / 1000000;
	}

	public double getAverageRunTimePart2(int times) {
		long total = 0;
		for(int i = 0; i < times; i++)
		{
			long start = System.nanoTime();
			getPart2Solution();
			total += System.nanoTime() - start;
		}
		return total / (double)times / 1000000;
	}

	public List<String> getInput(){
		return input;
	}
}
