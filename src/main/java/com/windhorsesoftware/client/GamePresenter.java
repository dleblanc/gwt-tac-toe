package com.windhorsesoftware.client;

import com.windhorsesoftware.tictactoe.*;

// FIXME: inject with Gin here
public class GamePresenter {
	private static final int BOARD_SIZE = 3;
	private static final int BOARD_WIN_COUNT = 3;
	
	private Board board; // Note that the lifetime of the board is shorter than that of the presenter - a new one will be created after
	private final GameView gameView;

	public GamePresenter(GameView gameView, Board initialBoard) {
		this.gameView = gameView;
		this.board = initialBoard; 
	}

	public void positionClicked(Position position, Mark mark) {
		if (!board.isEmpty(position)) {
			gameView.cellIsOccupied(position);
			return;
		}
		
		board.setCell(position, mark);
		
		if (board.isFinished()) {
			gameView.gameWasWon(board.getWinner());
			
			resetGame();
		}
	}
	
	private void resetGame() {
		this.board = new Board(BOARD_SIZE, BOARD_WIN_COUNT);
	}

	public Board getBoard() {
		return this.board;
	}
}
