package com.windhorsesoftware.tictactoe;

import java.util.*;

import com.google.inject.Inject;

public class Board {
	private static final int DEFAULT_BOARD_SIZE = 3;
	private static final int DEFAULT_BOARD_WIN_COUNT = 3;

	public final int size;
	private final int winLength;
	
	private Mark[][] cells;
	private List<Position[]> sequenceIndexes;
	
	@Inject
	public Board() {
		this(DEFAULT_BOARD_SIZE, DEFAULT_BOARD_WIN_COUNT);
	}
	
	public Board(int size, int winLength) {
		this.size = size;
		this.winLength = winLength;
		cells = new Mark[size][size];
		
		for (int row = 0; row < size; row++) {
			for (int col = 0; col < size; col++) {
				cells[row][col] = Mark.EMPTY;
			}
		}
	
		sequenceIndexes = new ArrayList<Position[]>(2 * size + 2);
		populateSearchCoordinates();
	}
	
	private void populateSearchCoordinates() {
		Position[][] horizontalCoords = new Position[size][size];
		Position[][] verticalCoords = new Position[size][size];
		Position[][] diagonalCoords = new Position[2][size];
		for (int outerIndex = 0; outerIndex < size; outerIndex++) {
			for (int innerIndex = 0; innerIndex < size; innerIndex++) {
				// Horizontal rows
				horizontalCoords[outerIndex][innerIndex] = Position.getPosition(outerIndex, innerIndex);
				
				// Vertical rows
				verticalCoords[outerIndex][innerIndex] = Position.getPosition(innerIndex, outerIndex);
			}
			
			// Top left to bottom right
			diagonalCoords[0][outerIndex] = Position.getPosition(outerIndex, outerIndex);
			
			// Top right to bottom left
			diagonalCoords[1][size - outerIndex - 1] = Position.getPosition(size - outerIndex - 1, outerIndex);
		}
		
		for (Position[] positionList: horizontalCoords) {
				sequenceIndexes.add(positionList);
		}
		for (Position[] positionList: verticalCoords) {
			sequenceIndexes.add(positionList);
		}
		for (Position[] positionList: diagonalCoords) {
			sequenceIndexes.add(positionList);
		}
		
	}
	
	public Mark getCell(Position position) {
		return cells[position.row][position.col];
	}

	public void setCell(Position position, Mark mark) {
		cells[position.row][position.col] = mark;
	}
	
	public Mark getWinner() {
		for (Position[] positionList: sequenceIndexes) {
			Mark winner = getWinnerForPositions(positionList);
			if (winner != null && winner != Mark.EMPTY) { 
				return winner;
			}
		}
		return null; // Could also return Mark.EMPTY here to indicate no winner yet
	}

	public boolean isEmpty(Position position) {
		return getCell(position) == Mark.EMPTY;
	}
	
	public void reset() {
		for (int row = 0; row < DEFAULT_BOARD_SIZE; row++) {
			for (int col = 0; col < DEFAULT_BOARD_SIZE; col++) {
				setCell(Position.getPosition(row, col), Mark.EMPTY);
			}
		}
	}

	public boolean isFinished() {
		return getWinner() != null || isDraw();
	}

	public boolean isDraw() {
		if (getWinner() != null) {
			return false;
		}
		
		int emptyCount = 0;
		for (Mark[] row : cells) {
			for (Mark mark : row) {
				if (mark == Mark.EMPTY) {
					emptyCount += 1;
					break;
				}
			}
		}
		return emptyCount == 0;
	}
	
	/**
	 * Return the MarkType of the winner for the given positions (all the same mark), or NULL.
	 */
	private Mark getWinnerForPositions(Position[] positionList) {
		int contiguousCount = 1;
		Mark markToMatch = getMarkForPosition(positionList[0]);
		
		for (int i = 1; i < positionList.length; i++) {
			Mark currentMark = getMarkForPosition(positionList[i]);
			
			if (currentMark != markToMatch) {
				contiguousCount = 1;
				markToMatch = currentMark;
				continue;
			}
			
			contiguousCount++;
			if (contiguousCount >= winLength) {
				return markToMatch;
			}
		}
		return null;
	}

	private Mark getMarkForPosition(Position position) {
		return cells[position.row][position.col];
	}
}
