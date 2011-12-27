package com.windhorsesoftware.tictactoe;

import java.util.regex.Pattern;

public class BoardBuilder {
	private static final Pattern whiteSpaceSplitPattern = Pattern.compile(" ");

	public static Board makeBoardWithWinLengthOf(int winLength, String... boardLayout) {
		
		Board board = new Board(whiteSpaceSplitPattern.split(boardLayout[0]).length, winLength);
		int row = 0;
		for (String rowOfSpaces : boardLayout) {
			int col = 0;
			for (String elementType: whiteSpaceSplitPattern.split(rowOfSpaces)) {
				Mark spaces;
				if (elementType.equals("_")) {
					spaces = Mark.EMPTY;
				}
				else if (elementType.equals("X")) {
					spaces = Mark.X;
				}
				else if (elementType.equals("O")) {
					spaces = Mark.O;
				}
				else {
					throw new RuntimeException("unknown element type: " + elementType);
				}
				board.setCell(Position.getPosition(row, col), spaces);
				col++;
			}
			row++;
		}
		return board;
	}
	
	public static Board makeBoard(String... boardLayout) {
		return makeBoardWithWinLengthOf(boardLayout.length, boardLayout);
	}
}
