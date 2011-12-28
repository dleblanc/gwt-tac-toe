package com.windhorsesoftware.tictactoe;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

// TODO: switch the rest to hamcrest matchers 

public class BoardTest {

	@Test
	public void empyBoardIsNotFinished() throws Exception {
		Board board = BoardBuilder.makeBoard(
				"_ _ _",
				"_ _ _",
				"_ _ _");

		assertThat(board.getWinner(), nullValue());
	}
	
	
	@Test
	public void getWinnerOnFullRow() throws Exception {
		Board board = BoardBuilder.makeBoard(
				"X X X",
				"_ _ _",
				"_ _ _");

		assertThat(board.getWinner(), equalTo(Mark.X));
	}
	
	@Test
	public void getWinnerOnFirstDiagonal() throws Exception {
		Board board = BoardBuilder.makeBoard(
				"O _ _",
				"_ O _",
				"_ _ O");

		assertThat(board.getWinner(), equalTo(Mark.O));
	}	
	
	@Test
	public void getWinnerOnSecondDiagonal() throws Exception {
		Board board = BoardBuilder.makeBoard(
				"_ _ X",
				"_ X _",
				"X _ _");

		assertThat(board.getWinner(), equalTo(Mark.X));
	}	
	
	@Test
	public void getWinnerOnVertical() throws Exception {
		Board board = BoardBuilder.makeBoard(
				"O _ _",
				"O _ _",
				"O _ _");

		assertThat(board.getWinner(), equalTo(Mark.O));
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
	
	@Test
	public void isDrawReturnsTrueOnBoardWithStatemate() throws Exception {
		Board board = BoardBuilder.makeBoard(
				"O X O",
				"O X X",
				"X O O");

		assertThat(board.isFinished(), equalTo(true));
		assertThat(board.isDraw(), equalTo(true));
	}

	@Test
	public void isDrawReturnsFalseOnBoardWithWin() throws Exception {
		Board board = BoardBuilder.makeBoard(
				"O X X",
				"X O O",
				"X X X");

		assertThat(board.isDraw(), equalTo(false));
	}
}
