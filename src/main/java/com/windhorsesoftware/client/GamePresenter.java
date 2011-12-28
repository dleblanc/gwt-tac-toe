package com.windhorsesoftware.client;

import com.google.inject.*;
import com.windhorsesoftware.tictactoe.*;

@Singleton
public class GamePresenter {
	private Board board;
	private final GameView gameView;

	@Inject
	public GamePresenter(GameView gameView, Board board) {
		this.gameView = gameView;
		this.board = board; 
	}

	public void positionClicked(Position position, Mark mark) {
		if (!board.isEmpty(position)) {
			gameView.cellIsOccupiedWarning(position);
			return;
		}
		
		board.setCell(position, mark);
		gameView.setCellOccupied(position, mark);
		
		if (board.isFinished()) {
			if (board.isDraw()) {
				gameView.gameWasADraw();
			}
			else {
				gameView.gameWasWon(board.getWinner());
			}
			
			this.board.reset();
			gameView.resetView();
		}
	}
	
	public Board getBoard() {
		return this.board;
	}
}
