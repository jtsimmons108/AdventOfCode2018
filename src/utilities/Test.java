package utilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {

	
	public static void main(String[] args) {
		List<Integer> nums = new ArrayList<>(Arrays.asList(1, 0, 2));
		System.out.println(nums);
		nums.remove(0);
		System.out.println(nums);
	}
}
