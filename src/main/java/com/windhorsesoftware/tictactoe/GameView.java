package com.windhorsesoftware.tictactoe;

public interface GameView {

	void initialize();

	void cellIsOccupiedWarning(Position position);

	void gameWasWon(Mark mark);

	void setCellOccupied(Position position, Mark mark);

	void resetView();
}
