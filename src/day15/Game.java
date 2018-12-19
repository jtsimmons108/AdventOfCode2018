package day15;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game {

	private char[][] board;
	public List<Piece> pieces;
	public Map<Type, List<Piece>> groups;
	
	public Game(char[][] board) {
		this.board = board;
		pieces = new ArrayList<>();
		groups = new HashMap<>();
		groups.put(Type.ELF, new ArrayList<>());
		groups.put(Type.GOBLIN, new ArrayList<>());
		initializePieces();
	}
	
	
	
	private void initializePieces() {
		for(int r = 0; r < board.length; r++) {
			for(int c = 0; c < board[0].length; c++) {
				Piece piece = null;
				if(board[r][c] == 'G') {
					piece = new Goblin(c, r);
				}else if(board[r][c] == 'E') {
					piece = new Elf(c, r);
				}
				
				if(piece != null) {
					pieces.add(piece);
					groups.get(piece.type).add(piece);
				}
			}
		}
	}
}
