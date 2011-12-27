package com.windhorsesoftware.tictactoe;

public interface GameView {

	void cellIsOccupied(Position position);

	void gameWasWon(Mark mark);

}
