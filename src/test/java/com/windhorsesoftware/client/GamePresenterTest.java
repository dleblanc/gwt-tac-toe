package com.windhorsesoftware.client;

import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.windhorsesoftware.tictactoe.*;

import com.windhorsesoftware.tictactoe.GameView;

public class GamePresenterTest {
	GameView mockView = mock(GameView.class);

	@Test
	public void whenUserSelectsOccupiedCellViewInformsOfError() throws Exception {
		Board board = new Board(3, 3);
		Position position = Position.getPosition(0, 0);
		board.setCell(position, Mark.O);
		
		GamePresenter presenter = new GamePresenter(mockView, board);
		presenter.positionClicked(position, Mark.X);
		
		verify(mockView).cellIsOccupied(position);
	}
	
	@Test
	public void whenUserSelectsEmptyCellItModifiesTheBoard() throws Exception {
		
		GamePresenter presenter = new GamePresenter(mockView, new Board(3, 3));
		Position position = Position.getPosition(0,  0);
		presenter.positionClicked(position, Mark.X);

		assertThat(presenter.getBoard().getCell(position), equalTo(Mark.X));
	}
	
	@Test
	public void whenUserWinsAGameItInformsTheViewAndResetsTheGame() throws Exception {
		Board board = BoardBuilder.makeBoard(
				"X X _",
				"_ _ _",
				"_ _ _");

		GamePresenter presenter = new GamePresenter(mockView, board);
		Position position = Position.getPosition(0,  2);
		
		presenter.positionClicked(position, Mark.X);

		verify(mockView).gameWasWon(Mark.X);
		assertThat(presenter.getBoard().getCell(position), equalTo(Mark.EMPTY));
	}
}
