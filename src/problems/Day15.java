package problems;

import java.util.List;

import day15.Game;
import utilities.Problem;

public class Day15 extends Problem {

	
	@Override
	public String getPart1Solution() {
		List<String> input = getInput();
		char[][] board = new char[input.size()][];
		for(int i = 0; i < board.length; i++) {
			board[i] = input.get(i).toCharArray();
		}
		Game game = new Game(board);
		System.out.println(game.pieces);
		return null;
	}

	@Override
	public String getPart2Solution() {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
