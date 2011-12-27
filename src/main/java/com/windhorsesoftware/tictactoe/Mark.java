package com.windhorsesoftware.tictactoe;

public enum Mark {
	EMPTY, X, O;
	
	public Mark getOpposingPlayer() {
		if ((this != Mark.X) && (this != Mark.O)) {
			throw new RuntimeException("Cannot get opposite space type for: " + this);
		}
		return this == Mark.X ? Mark.O : Mark.X;
	}
	
	@Override
	public String toString() {
		switch (this) {
		case X:
			return "X";
			
		case O:
			return "O";
			
		default:
			return "_";
		}
	}
}