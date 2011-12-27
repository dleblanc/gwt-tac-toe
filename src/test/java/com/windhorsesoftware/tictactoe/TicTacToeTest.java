package com.windhorsesoftware.tictactoe;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

class Board {
	
	boolean isFinished() {
		return false;
	}
}

public class TicTacToeTest {

	@Test
	public void empyBoardIsNotFinished() throws Exception {
		Board board = new Board();
		assertFalse(board.isFinished());
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
	public void getWinnerWithFiveInARowOnLargerBoard() throws Exception {
		Board board = BoardBuilder.makeBoardWithWinLengthOf(5,
				"_ X X X X X _ _",
				"_ _ _ _ _ _ _ _",
				"_ _ _ _ _ _ _ _",
				"_ _ _ _ _ _ _ _",
				"_ _ _ _ _ _ _ _",
				"_ _ _ _ _ _ _ _",
				"_ _ _ _ _ _ _ _",
				"_ _ _ _ _ _ _ _");

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
	public void simpleHashcodeTest() throws Exception {
		Board board = BoardBuilder.makeBoard(
				"O _ _",
				"O _ _",
				"O _ _");

		Board secondBoard = BoardBuilder.makeBoard(
				"O _ _",
				"O _ _",
				"O _ _");

		assertEquals(board.hashCode(), secondBoard.hashCode());
		
		Board notEqualBoard = BoardBuilder.makeBoard(
				"O _ _",
				"O _ _",
				"O _ X");
		
		assertFalse(board.hashCode() == notEqualBoard.hashCode());
		
	}	
}
