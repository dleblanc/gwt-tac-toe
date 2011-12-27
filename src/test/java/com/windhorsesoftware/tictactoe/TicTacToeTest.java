package com.windhorsesoftware.tictactoe;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

// TODO: switch the rest to hamcrest matchers 

public class TicTacToeTest {

	@Test
	public void empyBoardIsNotFinished() throws Exception {
		Board board = BoardBuilder.makeBoard(
				"_ _ _",
				"_ _ _",
				"_ _ _");

		// TODO: change to throw an exception, and provide an isFinished() method.
		assertNull(board.getWinner());
	}
	
	
	@Test
	public void getWinnerOnFullRow() throws Exception {
		Board board = BoardBuilder.makeBoard(
				"X X X",
				"_ _ _",
				"_ _ _");

		assertEquals(Mark.X, board.getWinner());
	}
	
	@Test
	public void getWinnerOnFirstDiagonal() throws Exception {
		Board board = BoardBuilder.makeBoard(
				"O _ _",
				"_ O _",
				"_ _ O");

		assertEquals(Mark.O, board.getWinner());
	}	
	
	@Test
	public void getWinnerOnSecondDiagonal() throws Exception {
		Board board = BoardBuilder.makeBoard(
				"_ _ X",
				"_ X _",
				"X _ _");

		assertEquals(Mark.X, board.getWinner());
	}	
	
	@Test
	public void getWinnerOnVertical() throws Exception {
		Board board = BoardBuilder.makeBoard(
				"O _ _",
				"O _ _",
				"O _ _");

		assertEquals(Mark.O, board.getWinner());
	}

	@Test
	public void isFinishedReturnsTrueOnWinningBoard() throws Exception {
		Board board = BoardBuilder.makeBoard(
				"O _ _",
				"O _ _",
				"O _ _");

		assertThat(board.isFinished(), equalTo(true));
	}

	@Test
	public void isFinishedReturnsFalseOnBoardWithoutWin() throws Exception {
		Board board = BoardBuilder.makeBoard(
				"O _ _",
				"_ _ _",
				"O _ _");

		assertThat(board.isFinished(), equalTo(false));
	}
}
