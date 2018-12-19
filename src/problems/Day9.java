package problems;

import java.util.Arrays;

import utilities.Problem;

public class Day9 extends Problem{

	private final int PLAYERS = 418;
	private final int LAST_MARBLE = 71339;
	
	@Override
	public String getPart1Solution() {
		return String.valueOf(playGame(PLAYERS, LAST_MARBLE));
	}

	@Override
	public String getPart2Solution() {
		return String.valueOf(playGame(PLAYERS, LAST_MARBLE * 100));
	}
	
	private long playGame(int players, int times) {
		Marble current = new Marble(0);
		long[] scores = new long[players];
		
		for(int m = 1; m <= times; m++) {
			if(m % 23 == 0) {
				for(int i = 0; i < 7; i++) {
					current = current.previous;
				}
				scores[m % players] += m + current.removeMarble();
				current = current.next;
			}else {
				current = current.next.insertAfter(m);
			}
		}
		return Arrays.stream(scores).max().getAsLong();
	}
}

	

class Marble
{
	Marble previous;
	Marble next;
	private int value;
	
	public Marble(int value) {
		this.value = value;
		if(value == 0) {
			previous = this;
			next = this;
		}
	}
	
	public Marble insertAfter(int value) {
		Marble marble = new Marble(value);
		marble.previous = this;
		marble.next = this.next;
		this.next.previous = marble;
		this.next = marble;
		return marble;
	}
	
	public int removeMarble() {
		this.previous.next = next;
		this.next.previous = previous;
		return value;
	}
}



