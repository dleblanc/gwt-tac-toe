package com.windhorsesoftware.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.*;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import com.google.inject.*;
import com.windhorsesoftware.tictactoe.*;

@Singleton
public class GwtGameView extends Composite implements GameView {
	interface MyUiBinder extends UiBinder<Widget, GwtGameView> {
	}

	interface MyStyle extends CssResource {
		String cellStateX();

		String cellStateO();

		String cellStateEmpty();
	}

	private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

	@UiField
	Grid boardGrid;

	@UiField
	MyStyle style;

	private FocusWidget[][] cellWidgets;

	// Workaround for circular references - see
	// http://code.google.com/p/google-gin/issues/detail?id=43 (lazy load the
	// presenter via a guice Provider)
	private final Provider<GamePresenter> presenterProvider;
	
	private Mark playerMark = Mark.X;

	@Inject
	public GwtGameView(Provider<GamePresenter> presenterProvider) {
		this.presenterProvider = presenterProvider;
	}

	public void initialize() {
		initWidget(uiBinder.createAndBindUi(this));

		storeCellWidgets();

		addClickHandlersForCells();

		resetView();
	}

	public void cellIsOccupiedWarning(Position position) {
		Window.alert("Cell is occupied!");
	}

	public void gameWasWon(Mark mark) {
		Window.alert("Game won by " + mark.toString());
	}
	
	public void gameWasADraw() {
		Window.alert("Game was a draw");
	}

	public void setCellOccupied(Position position, Mark mark) {
		FocusWidget cell = cellWidgets[position.row][position.col];
		setCellStyleForMark(cell, mark);
	}

	public void resetView() {
		for (FocusWidget[] row : cellWidgets) {
			for (FocusWidget focusWidget : row) {
				setCellStyleForMark(focusWidget, Mark.EMPTY);
			}
		}
	}

	private void setCellStyleForMark(FocusWidget widget, Mark mark) {
		widget.removeStyleName(style.cellStateX());
		widget.removeStyleName(style.cellStateO());
		widget.removeStyleName(style.cellStateEmpty());
		
		switch (mark) {
		case X:
			widget.addStyleName(style.cellStateX());
			break;
			
		case O:
			widget.addStyleName(style.cellStateO());
			break;

		case EMPTY:
			widget.addStyleName(style.cellStateEmpty());
			break;
		}
		
		widget.setEnabled(mark == Mark.EMPTY);
	}

	private void addClickHandlersForCells() {

		for (int row = 0; row < cellWidgets.length; row++) {
			for (int col = 0; col < cellWidgets[row].length; col++) {
				final int thisRow = row;
				final int thisCol = col;
				ClickHandler clickHandler = new ClickHandler() {
					public void onClick(ClickEvent event) {
						presenterProvider.get().positionClicked(
								Position.getPosition(thisRow, thisCol), getAndSwitchPlayer());
					}

				};

				cellWidgets[row][col].addClickHandler(clickHandler);
			}
		}
	}

	private void storeCellWidgets() {
		cellWidgets = new FocusWidget[boardGrid.getRowCount()][boardGrid
				.getCellCount(0)];

		for (int row = 0; row < boardGrid.getRowCount(); row++) {
			int cellCount = boardGrid.getCellCount(row);
			for (int col = 0; col < cellCount; col++) {

				cellWidgets[row][col] = (FocusWidget) boardGrid.getWidget(row,
						col);
			}
		}
	}

	private Mark getAndSwitchPlayer() {
		// FIXME: this isn't good here - have the controller responsible for player selection
		Mark mark = playerMark;
		playerMark = playerMark.getOpposingPlayer();
		return mark;
	}
}
