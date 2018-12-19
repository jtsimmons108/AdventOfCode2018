package utilities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClayWall {

	public static final Pattern pattern = Pattern.compile("([xy])=(\\d+),\\s([xy])=(\\d+)..(\\d+)");
	public int startX, endX, startY, endY;
	
	public ClayWall(String line) {
		Matcher match = pattern.matcher(line);
		match.find();
		int val1 = Integer.parseInt(match.group(2));
		int val2 = Integer.parseInt(match.group(4));
		int val3 = Integer.parseInt(match.group(5));
		
		if(match.group(1).equals("x")) {
			startX = val1;
			endX = val1;
			startY = val2;
			endY = val3;
		}else {
			startY = val1;
			endY = val1;
			startX = val2;
			endX = val3;
		}			
	}
	
	
	public String toString() {
		
		return String.format("x: %d..%d, y: %d..%d", startX, endX, startY, endY);
	}
}
