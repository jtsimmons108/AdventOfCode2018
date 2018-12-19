package problems;

import java.util.Arrays;

import utilities.Point;
import utilities.Problem;

public class Day11 extends Problem{


	private final int SERIAL_NUMBER = 5153;
	private final int SIZE = 300;
	private int[][] grid;

	public Day11() {
		grid = new int[SIZE][SIZE];
		for(int x = 1; x <= 300; x++) {
			for(int y = 1; y <= 300; y++) {
				grid[y-1][x-1] = getPowerLevel(x, y, SERIAL_NUMBER);
			}
		}
	}


	@Override
	public String getPart1Solution() {
		int max = Integer.MIN_VALUE;
		Point maxPoint = new Point(0,0);

		for(int x = 1; x < SIZE - 3; x++) {
			for(int y = 1; y < SIZE - 3; y++) {
				int powerLevel = findGridPowerLevel(x, y);
				if (powerLevel > max) {
					max = powerLevel;
					maxPoint = new Point(x, y);
				}
			}
		}
		return maxPoint.toString();
	}

	@Override
	public String getPart2Solution() {
		int[][] summedAreaTable = new int[SIZE][SIZE];
		for(int r = 0; r < SIZE; r++) {
			for(int c = 0; c < SIZE; c++) {
				int sumh = c > 0 ? summedAreaTable[r][c - 1] : 0;
				int sumv = r > 0 ? summedAreaTable[r - 1][c] : 0;
				int sumvh = r > 0 && c > 0 ? summedAreaTable[r - 1][c - 1] : 0;
				summedAreaTable[r][c] = grid[r][c] + sumh + sumv - sumvh;
			}
		}

		Point maxPoint = null;
		int max = Integer.MIN_VALUE;
		for(int size = 1; size <= 300; size++) {
			for(int r = 0; r < SIZE - size; r++) {
				for(int c = 0; c < SIZE - size; c++) {
					int sum = summedAreaTable[r][c] + summedAreaTable[r + size][c + size] - 
								summedAreaTable[r][c + size] - summedAreaTable[r + size][c];
					if(sum > max) {
						max = sum;
						maxPoint = new Point(c, r);
						System.out.println("New max at size : " + size + " at point (" + c + ", " + r + ")");
					}
				}
			}
		}
		
		for(int[] row : grid) {
			System.out.println(Arrays.toString(row));
		}
		System.out.println("***********************************************");
		for(int[] row : summedAreaTable) {
			System.out.println(Arrays.toString(row));
		}
		return maxPoint.toString();
	}


	private int getPowerLevel(int x, int y, int serialNumber) {
		int powerLevel = (x + 10) * y;
		powerLevel += serialNumber;
		powerLevel *= (x + 10);
		powerLevel = (powerLevel / 100) % 10;
		return powerLevel - 5;
	}

	private final int findGridPowerLevel(int x, int y) {
		int total = 0;
		for(int r = y; r < y + 3; r++) {
			for(int c = x; c < x + 3; c++) {
				total += grid[r - 1][c - 1];
			}
		}

		return total;
	}


}
