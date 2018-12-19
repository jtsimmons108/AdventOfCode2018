package problems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import utilities.Problem;

public class Day13 extends Problem{

	private char[][] map;
	List<Cart> carts;
	final int NORTH = 0, EAST = 1, SOUTH = 2, WEST = 3;
	public Day13() {
		List<String> input = getInput();
		map = new char[input.size()][input.get(0).length()];
		carts = new ArrayList<>();

		for(int r = 0; r < input.size(); r++) {
			char[] row = input.get(r).toCharArray();
			for(int c = 0; c < row.length; c++) {
				char cur = row[c];
				if(isCart(cur)) {
					carts.add(new Cart(c, r, getDirection(cur)));
				}
				map[r][c] = getTrackPiece(cur);
			}
		}		
	}


	@Override
	public String getPart1Solution() {
		List<Cart> carts = new ArrayList<>();
		for(Cart c : this.carts) {
			carts.add(new Cart(c));
		}
		while(true) {
			for(Cart c : carts) {
				c.move();
				for(Cart o : carts) {
					if(c.isCollidingWith(o)) {
						return c.toString();
					}
				}
			}
			Collections.sort(carts);
		}
	}

	@Override
	public String getPart2Solution() {
		List<Cart> carts = new ArrayList<>();
		for(Cart c : this.carts) {
			carts.add(new Cart(c));
		}
		while(carts.size() > 1) {
			for(Cart c : carts) {
				if(!c.crashed) {
					c.move();
					for(Cart o : carts) {
						if(c.isCollidingWith(o)) {
							c.crashed = true;
							o.crashed = true;
						}
					}
				}
			}
			carts = carts.stream().filter(c -> !c.crashed).collect(Collectors.toList());
			Collections.sort(carts);
		}
		return carts.get(0).toString();
	}


	public boolean isCart(char c) {
		return c == 'v' || c == '^' || c == '<' || c == '>';
	}

	public char getTrackPiece(char c) {
		if(c == 'v' || c == '^') {
			return '|';
		}else if (c == '<' || c == '>') {
			return '-';
		}

		return c;
	}

	public int getDirection(char c) {
		if (c == 'v') return SOUTH;
		if (c == '>') return EAST;
		if (c == '^') return NORTH;
		return WEST;

	}

	class Cart implements Comparable<Cart>{

		int x, y, direction, turns;
		int[] intersections = {-1, 0, 1};
		boolean crashed;

		public Cart(int x, int y, int direction){
			this.x = x;
			this.y = y;
			this.direction = direction;
		}

		public Cart(Cart o) {
			this.x = o.x;
			this.y = o.y;
			this.direction = o.direction;
		}

		public void move() {
			switch(direction) {
			case NORTH: y--; break;
			case EAST: 	x++; break;
			case SOUTH: y++; break;
			case WEST: 	x--; break;
			}
			setDirection();
		}

		private void setDirection() {
			char c = map[y][x];
			if(c == '+') {
				direction = (direction + intersections[turns] + 4
						) % 4;
				turns = (turns + 1) % 3;
			}else if(c == '\\') {
				switch(direction) {
				case NORTH: direction = WEST; break;
				case EAST: 	direction = SOUTH; break;
				case SOUTH: direction = EAST; break;
				case WEST: 	direction = NORTH; break;
				}
			}else if(c == '/') {
				switch(direction) {
				case NORTH: direction = EAST; break;
				case EAST: 	direction = NORTH; break;
				case SOUTH: direction = WEST; break;
				case WEST: 	direction = SOUTH; break;
				}
			}
		}

		public boolean isCollidingWith(Cart o) {
			return this != o &&  !o.crashed && this.x == o.x && this.y == o.y;
		}

		@Override
		public int compareTo(Cart o) {
			if(this.y == o.y) {
				return this.x - o.x;
			}
			return this.y - o.y;
		}

		public String toString() {
			return String.format("(%d, %d) -> %d", x, y, direction);
		}


	}

}
