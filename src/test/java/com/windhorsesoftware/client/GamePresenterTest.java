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
		
		verify(mockView).cellIsOccupiedWarning(position);
	}
	
	@Test
	public void whenUserSelectsEmptyCellItModifiesTheBoardAndInformsTheViewOfSuccess() throws Exception {
		
		GamePresenter presenter = new GamePresenter(mockView, new Board(3, 3));
		Position position = Position.getPosition(0,  0);
		presenter.positionClicked(position, Mark.X);

		assertThat(presenter.getBoard().getCell(position), equalTo(Mark.X));
		verify(mockView).setCellOccupied(position, Mark.X);
	}
	
	@Test
	public void whenUserWinsAGameItInformsTheViewAndResetsTheBoardAndView() throws Exception {
		Board board = BoardBuilder.makeBoard(
				"X X _",
				"_ _ _",
				"_ _ _");

		GamePresenter presenter = new GamePresenter(mockView, board);
		Position position = Position.getPosition(0,  2);
		
		presenter.positionClicked(position, Mark.X);

		verify(mockView).gameWasWon(Mark.X);
		assertThat(presenter.getBoard().getCell(position), equalTo(Mark.EMPTY));
		
		verify(mockView).resetView();
	}
	
	// TODO: add a test for statemate - game is over but no winner (provide reset button?)
}
