package com.windhorsesoftware.tictactoe;


public class Position {
	private static final int MAX_SIZE = 10;
	private static final Position[][] positions = new Position[MAX_SIZE][MAX_SIZE];
	
	static {
		for (int i = 0; i < MAX_SIZE; i++) {
			for (int j = 0; j < MAX_SIZE; j++) {
				positions[i][j] = new Position(i, j);
			}
		}
	}
	
	public final int row;
	public final int col;
	
	private Position(int row, int col) {
		this.row = row;
		this.col = col;
	}
	
	public static Position getPosition(int row, int col) {
		// Implement a flyweight pattern so we don't create so many positions
		return positions[row][col];
	}
	
	@Override
	public String toString() {
		return "(" + row + "," + col + ")";
	}
	
	public boolean equalTo(int row, int col) {
		return row == this.row && col == this.col;
	}
}
